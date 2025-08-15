```sql 
CREATE DATABASE Escuela;
USE Escuela;

-- Tabla Alumno
CREATE TABLE Alumno (
    Numero INT PRIMARY KEY,
    Nombre VARCHAR(100),
    ApellidoPaterno VARCHAR(100),
    ApellidoMaterno VARCHAR(100)
);

-- Tabla Curso
CREATE TABLE Curso (
    Codigo INT PRIMARY KEY,
    Nombre VARCHAR(100)
);

-- Tabla Inscripcion (relación muchos a muchos sin atributos)
CREATE TABLE Inscripcion (
    Numero INT,
    Codigo INT,
    PRIMARY KEY (Numero, Codigo),
    FOREIGN KEY (Numero) REFERENCES Alumno(Numero),
    FOREIGN KEY (Codigo) REFERENCES Curso(Codigo)
);
