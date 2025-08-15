```sql 
CREATE DATABASE Empresa;
USE Empresa;

-- Tabla Employee
CREATE TABLE Employee (
    SSN CHAR(9) PRIMARY KEY,
    Name VARCHAR(100),
    Sex CHAR(1),
    Address VARCHAR(100),
    Salary DECIMAL(10,2),
    Birthdate DATE,
    SuperSSN CHAR(9), -- Supervisor
    DNumber INT, -- Departamento al que pertenece
    FOREIGN KEY (SuperSSN) REFERENCES Employee(SSN)
);

-- Tabla Department
CREATE TABLE Department (
    DNumber INT PRIMARY KEY,
    Name VARCHAR(100),
    Location VARCHAR(100),
    ManagerSSN CHAR(9),
    FOREIGN KEY (ManagerSSN) REFERENCES Employee(SSN)
);

-- Tabla Project
CREATE TABLE Project (
    PNumber INT PRIMARY KEY,
    Name VARCHAR(100),
    Location VARCHAR(100),
    DNumber INT,
    FOREIGN KEY (DNumber) REFERENCES Department(DNumber)
);

-- Relación muchos a muchos entre Employee y Project
CREATE TABLE Works_On (
    SSN CHAR(9),
    PNumber INT,
    Hours DECIMAL(5,2),
    PRIMARY KEY (SSN, PNumber),
    FOREIGN KEY (SSN) REFERENCES Employee(SSN),
    FOREIGN KEY (PNumber) REFERENCES Project(PNumber)
);

-- Tabla Dependent
CREATE TABLE Dependent (
    ID INT iidentity (1,1) PRIMARY KEY,
    SSN CHAR(9),
    Name VARCHAR(100),
    Sex CHAR(1),
    Birthdate DATE,
    Relationship VARCHAR(50),
    FOREIGN KEY (SSN) REFERENCES Employee(SSN)
);