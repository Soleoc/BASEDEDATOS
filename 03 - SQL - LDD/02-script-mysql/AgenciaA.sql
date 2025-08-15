CREATE DATABASE AgenciaAutos;
USE AgenciaAutos;

-- Tabla Cliente
CREATE TABLE Cliente (
    numcliente INT PRIMARY KEY,
    nombre NVARCHAR(20),
    apellidop1 NVARCHAR(20),
    apellidop2 NVARCHAR(20),
    curp NCHAR(18),
    telefono NCHAR(12),
    calle NVARCHAR(50),
    numero INT,
    ciudad NVARCHAR(20)
);

-- Tabla Sucursal
CREATE TABLE Sucursal (
    numerosucursal INT PRIMARY KEY,
    nombre NVARCHAR(20),
    ubicacion NVARCHAR(20)
);

-- Tabla Vehiculo
CREATE TABLE Vehiculo (
    numvehiculo INT PRIMARY KEY,
    placa NCHAR(7),
    marca NVARCHAR(15),
    modelo NVARCHAR(20),
    anio INT,
    numcliente INT,
    numerosucursal INT,
    FOREIGN KEY (numcliente) REFERENCES Cliente(numcliente),
    FOREIGN KEY (numerosucursal) REFERENCES Sucursal(numerosucursal)
);
