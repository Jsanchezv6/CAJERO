-- DEPOSITO

USE [CAJERO]
GO

ALTER PROCEDURE [dbo].[sp_Deposito]
(
    @cuenta VARCHAR(25),
    @monto DECIMAL(12, 2)
)
AS
BEGIN
 
    DECLARE @Intentos INT = 0;
    DECLARE @FechaHora DATETIME = GETDATE();
    DECLARE @Dead INT = 1205;
    WHILE @Intentos < 3
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Validar cuenta
            IF NOT EXISTS (SELECT 1 FROM Cuenta WHERE cuenta = @cuenta)
            BEGIN
                THROW 50001, 'La cuenta no existe.', 1;
            END

            -- Actualizar saldo
            UPDATE Cuenta
            SET saldo = saldo + @monto
            WHERE cuenta = @cuenta;

            -- Registrar transacción
            INSERT INTO Transaccion
                (TipoTransaccion, monto, fechahora, NumeroCuentaOrigen, NumeroCuentaDestino)
            VALUES
                ('D', @monto, @FechaHora, 'SISTEMA', @cuenta); -- 'SISTEMA' como valor de cuenta origen
            COMMIT;
            BREAK; 
        END TRY
        BEGIN CATCH
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END; -- Fin del bucle
END;
GO



--RETIRO


ALTER PROCEDURE [dbo].[sp_Retiro]
(
    @cuenta VARCHAR(25),
    @pin VARCHAR(4), 
    @monto DECIMAL(12, 2)
)
AS
BEGIN
    DECLARE @Intentos INT = 0;
    DECLARE @FechaHora DATETIME = GETDATE();
    DECLARE @Dead INT = 1205;

    WHILE @Intentos < 3
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Validar cuenta
            IF NOT EXISTS (SELECT 1 FROM Cuenta WHERE cuenta = @cuenta)
            BEGIN
                THROW 50001, 'La cuenta no existe.', 1;
            END

            -- Validar PIN
            IF NOT EXISTS (SELECT 1 FROM Usuario u INNER JOIN Cuenta c ON u.idUsuario = c.idUsuario WHERE c.cuenta = @cuenta AND u.pin = @pin)
            BEGIN
                THROW 50006, 'PIN incorrecto.', 1;
            END

            -- Validar saldo
            IF @monto > (SELECT saldo FROM Cuenta WHERE cuenta = @cuenta)
            BEGIN
                THROW 50002, 'Saldo insuficiente.', 1;
            END
            
            -- Validar que el monto sea mayor a 50 y múltiplo de 50
            IF @monto < 50 OR @monto % 50 != 0
            BEGIN
                THROW 50005, 'El monto debe ser mayor a 50 y un múltiplo de 50.', 1;
            END

            -- Validar disponibilidad de billetes
            DECLARE @billetes100 INT, @billetes50 INT
            SET @billetes100 = @monto / 100
            SET @billetes50 = (@monto % 100) / 50

            IF @billetes100 > (SELECT cantidad FROM Cajero WHERE id = 1)
            BEGIN
                THROW 50003, 'No hay suficientes billetes de Q100.', 1;
            END

            IF @billetes50 > (SELECT cantidad FROM Cajero WHERE id = 2)
            BEGIN
                THROW 50004, 'No hay suficientes billetes de Q50.', 1;
            END

            -- Actualizar saldo
            UPDATE Cuenta
            SET saldo = saldo - @monto
            WHERE cuenta = @cuenta;

            -- Actualizar cantidad de billetes
            UPDATE Cajero
            SET cantidad = cantidad - @billetes100
            WHERE id = 1;

            UPDATE Cajero
            SET cantidad = cantidad - @billetes50
            WHERE id = 2;

            -- Registrar transacción
            INSERT INTO Transaccion
                (TipoTransaccion, monto, fechahora, NumeroCuentaOrigen, NumeroCuentaDestino)
            VALUES
                ('R', @monto, @FechaHora, @cuenta, NULL); 
            COMMIT;
            BREAK;
        END TRY
        BEGIN CATCH
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END; -- Fin del bucle
END;







-- control de billetes
ALTER PROCEDURE [dbo].[sp_ControlBilletes]
(
    @denominacion INT,
    @cantidad INT
)
AS
BEGIN
    DECLARE @Intentos INT = 0;
    DECLARE @FechaHora DATETIME = GETDATE();
    DECLARE @Dead INT = 1205;

    WHILE @Intentos < 3
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Validar denominación
            IF @denominacion NOT IN (100, 50)
            BEGIN
                THROW 50005, 'Denominación no válida.', 1;
            END

            -- Actualizar cantidad de billetes
            UPDATE Cajero
            SET cantidad = cantidad + @cantidad
            WHERE id = CASE @denominacion WHEN 100 THEN 1 ELSE 2 END;

            COMMIT;
            BREAK;
        END TRY
        BEGIN CATCH
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END;
END;
GO



--TRANSFERENCIA
ALTER PROCEDURE [dbo].[sp_TransferenciaCuenta]
(
    @cuentaOrigen VARCHAR(25),
    @pin VARCHAR(4), -- Agregar parámetro para el PIN de la cuenta origen
    @cuentaDestino VARCHAR(25),
    @monto DECIMAL(12, 2)
)
AS
BEGIN
    DECLARE @Intentos INT = 0;
    DECLARE @FechaHora DATETIME = GETDATE();
    DECLARE @Dead INT = 1205;

    WHILE @Intentos < 3
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Validar cuenta origen
            IF NOT EXISTS (SELECT 1 FROM Cuenta WHERE cuenta = @cuentaOrigen)
            BEGIN
                THROW 50001, 'La cuenta origen no existe.', 1;
            END

            -- Validar PIN de la cuenta origen
            IF NOT EXISTS (SELECT 1 FROM Usuario u INNER JOIN Cuenta c ON u.idUsuario = c.idUsuario WHERE c.cuenta = @cuentaOrigen AND u.pin = @pin)
            BEGIN
                THROW 50007, 'PIN incorrecto para la cuenta origen.', 1;
            END

            -- Validar cuenta destino
            IF NOT EXISTS (SELECT 1 FROM Cuenta WHERE cuenta = @cuentaDestino)
            BEGIN
                THROW 50006, 'La cuenta destino no existe.', 1;
            END

            -- Validar saldo en cuenta de origen
            IF @monto > (SELECT saldo FROM Cuenta WHERE cuenta = @cuentaOrigen)
            BEGIN
                THROW 50002, 'Saldo insuficiente en cuenta de origen.', 1;
            END

            -- Retirar saldo de la cuenta origen
            UPDATE Cuenta
            SET saldo = saldo - @monto
            WHERE cuenta = @cuentaOrigen;

            -- Aumentar saldo en la cuenta destino
            UPDATE Cuenta
            SET saldo = saldo + @monto
            WHERE cuenta = @cuentaDestino;

            -- Registrar transacciones
            INSERT INTO Transaccion
                (TipoTransaccion, monto, fechahora, NumeroCuentaOrigen, NumeroCuentaDestino)
            VALUES
                ('T', @monto, @FechaHora, @cuentaOrigen, @cuentaDestino);

            -- Confirmar transacción
            COMMIT;
            BREAK; -- Salir del bucle si la transacción es exitosa
        END TRY
        BEGIN CATCH
            -- Manejar errores
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END; -- Fin del bucle
END;







--TRANSFERENCIA A CELULAR
ALTER PROCEDURE [dbo].[sp_TransferenciaCelular]
(
    @cuentaOrigen VARCHAR(25),
    @pin VARCHAR(4), -- Agregar parámetro para el PIN de la cuenta origen
    @telefonoDestino VARCHAR(25),
    @monto DECIMAL(12, 2)
)
AS
BEGIN
    DECLARE @Intentos INT = 0;
    DECLARE @FechaHora DATETIME = GETDATE();
    DECLARE @Dead INT = 1205;
    DECLARE @cuentaDestino VARCHAR(25)

    WHILE @Intentos < 3
    BEGIN

        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Obtener el número de cuenta asociado al número de teléfono de destino
            SELECT @cuentaDestino = cuenta 
            FROM Cuenta 
            WHERE telefono = @telefonoDestino;

            IF @cuentaDestino IS NULL
            BEGIN
                THROW 50006, 'El número de teléfono destino no está asociado a ninguna cuenta.', 1;
            END

            -- Validar cuenta origen
            IF NOT EXISTS (SELECT 1 FROM Cuenta WHERE cuenta = @cuentaOrigen)
            BEGIN
                THROW 50001, 'La cuenta origen no existe.', 1;
            END

            -- Validar PIN de la cuenta origen
            IF NOT EXISTS (SELECT 1 FROM Usuario u INNER JOIN Cuenta c ON u.idUsuario = c.idUsuario WHERE c.cuenta = @cuentaOrigen AND u.pin = @pin)
            BEGIN
                THROW 50007, 'PIN incorrecto para la cuenta origen.', 1;
            END

            -- Validar saldo en cuenta de origen
            IF @monto > (SELECT saldo FROM Cuenta WHERE cuenta = @cuentaOrigen)
            BEGIN
                THROW 50002, 'Saldo insuficiente en cuenta de origen.', 1;
            END

            -- Retirar saldo de la cuenta origen
            UPDATE Cuenta
            SET saldo = saldo - @monto
            WHERE cuenta = @cuentaOrigen;

            -- Aumentar saldo en la cuenta destino
            UPDATE Cuenta
            SET saldo = saldo + @monto
            WHERE cuenta = @cuentaDestino;

            -- Registrar transacciones
            INSERT INTO Transaccion
                (TipoTransaccion, monto, fechahora, NumeroCuentaOrigen, NumeroCuentaDestino, NumeroTelefonoDestino)
            VALUES
                ('T', @monto, @FechaHora, @cuentaOrigen, @cuentaDestino, @telefonoDestino);

            -- Confirmar transacción
            COMMIT;
            BREAK; -- Salir del bucle si la transacción es exitosa
        END TRY
        BEGIN CATCH
            -- Manejar errores
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END; 
END;




--COMPROBACION DE SALDOS

ALTER PROCEDURE [dbo].[sp_ReporteComprobacionSaldos]
(
    @fecha DATE
)
AS
BEGIN

    DECLARE @Intentos INT = 0;
    DECLARE @Dead INT = 1205;

    WHILE @Intentos < 3
    BEGIN
        SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
        BEGIN TRANSACTION;
        BEGIN TRY
            -- Consulta del reporte
            SELECT
                c.cuenta,
                c.saldo, -- Saldo actual
                SUM(CASE WHEN TipoTransaccion IN ('D', 'T+') THEN monto ELSE -monto END) OVER (PARTITION BY c.cuenta) 
                    AS saldoCalculado 
            FROM 
                Cuenta c
            JOIN Transaccion t ON c.cuenta = t.NumeroCuentaOrigen OR c.cuenta = t.NumeroCuentaDestino
            WHERE
                CAST(t.fechahora AS DATE) = @fecha 

            -- Confirmar transacción
            COMMIT;
            BREAK; -- Salir del bucle si la transacción es exitosa
        END TRY
        BEGIN CATCH
            -- Manejar errores
            IF ERROR_NUMBER() = @Dead
            BEGIN
                SET @Intentos = @Intentos + 1;
                WAITFOR DELAY '00:00:01';
                SELECT 'Intento numero: ', @Intentos;
                ROLLBACK;
            END
            ELSE
            BEGIN
                DECLARE @NumeroError INT = 50000 + ERROR_NUMBER();
                DECLARE @MensajeError NVARCHAR(4000) = ERROR_MESSAGE();
                ROLLBACK;
                THROW @NumeroError, @MensajeError, 1;
            END
        END CATCH
    END; 
END;
GO
