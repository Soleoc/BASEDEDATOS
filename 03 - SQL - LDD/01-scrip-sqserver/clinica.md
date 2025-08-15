```sql 
CREATE DATABASE Clinica;
USE Clinica;

-- Tabla Doctor
CREATE TABLE Doctor (
    NumCed INT PRIMARY KEY,
    Nombre VARCHAR(100),
    ApellidoP VARCHAR(100),
    ApellidoM VARCHAR(100),
    Edad INT,
    Sexo VARCHAR(10)
);

-- Tabla Paciente
CREATE TABLE Paciente (
    NumPac INT PRIMARY KEY,
    Nombre VARCHAR(100),
    ApellidoP VARCHAR(100),
    ApellidoM VARCHAR(100),
    Edad INT,
    Sexo VARCHAR(10)
);

-- Relación TratamientoDiagnostico (N:M sin atributos)
CREATE TABLE TratamientoDiagnostico (
    NumCed INT,
    NumPac INT,
    PRIMARY KEY (NumCed, NumPac),
    FOREIGN KEY (NumCed) REFERENCES Doctor(NumCed),
    FOREIGN KEY (NumPac) REFERENCES Paciente(NumPac)
);
