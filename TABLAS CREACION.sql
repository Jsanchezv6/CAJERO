USE CAJERO
CREATE TABLE Cuenta (
    cuenta VARCHAR(25) UNIQUE NOT NULL ,
    saldo DECIMAL(12, 2) NOT NULL,
	idUsuario int NOT NULl
	FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);




CREATE TABLE Transaccion (
  idTransaccion INT IDENTITY(1, 1) PRIMARY KEY,
	TipoTransaccion VARCHAR(1) NOT NULL,
  monto DECIMAL(12, 2) NOT NULL,
  fechahora DATETIME NOT NULL,
  NumeroCuentaOrigen VARCHAR(10) NOT NULL,
	NumeroCuentaDestino VARCHAR(50),
	NumeroTelefonoDestino int
);



CREATE TABLE Cajero (
    id INT PRIMARY KEY,
    cantidad INT NOT NULL,
	fecha DATE,
	Descripcion VARCHAR(20)
);

CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY,
    nombreUsuario VARCHAR(50) UNIQUE NOT NULL,
    pin VARCHAR(10) NOT NULL,
    rol VARCHAR(10) NOT NULL
);

CREATE TABLE ReposicionEfectivo (
    id INT PRIMARY KEY,
    fechahora DATETIME, 
	billetesDe100 int,
	billetesDe50 int,
    total DECIMAL(12, 2) NOT NULL,
    administradorId INT,
    FOREIGN KEY (administradorId) REFERENCES Usuario(idUsuario)
);