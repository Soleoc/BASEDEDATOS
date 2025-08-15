--Lenguaje sql-ldd (create, alter,drop)

--crear una base de datos empresag2
CREATE DATABASE empresag2;
GO 
--utilizar la base de datos
USE empresag2;
--crear la tabla categorias 
CREATE TABLE Categorias(
Categroriaid int primary key,
nombrecategoria nvarchar (30) not null unique
);
GO
--crear tabla empleado
CREATE TABLE Empleado(
EmpleadoId int not null identity(1,1),
Nombre nvarchar(20) not null,
Apellidopaterno nvarchar(15) not null,
curp char(18) not null,
telefono char(15),
sexo char(1) not null,
activo bit not null,
CONSTRAINT pk_empleado
PRIMARY KEY (EmpleadoId),
CONSTRAINT unique_curp
UNIQUE(curp),
CONSTRAINT chk_sexo
CHECK (sexo in('M','F'))
);
GO
INSERT INTO Categorias (Categroriaid,nombrecategoria)
VALUES(1,'CARNES FRIAS');
GO
INSERT INTO Categorias (Categroriaid,nombrecategoria)
VALUES(2,'VINOS Y LICORES');
GO
SELECT*FROM Categorias;

--Insertar en empelado 
INSERT INTO Empleado(Nombre,Apellidopaterno,curp,telefono,sexo,activo)
VALUES('Monico','Panfilo', 'sdsddssdf','M',1);
GO
SELECT*FROM Empleado;
GO
--creacion de primary keys compuesta
CREATE TABLE Tabla1(
tabla1id1 int not null,
tablaid2 int not null,
Nombre nvarchar(20) not null,
CONSTRAINT pk_tabla1
PRIMARY KEY(tabla1id1,tablaid2)
);
GO
--Razon de cardinalidad 1-n
CREATE TABLE Tabla2(
Tabla2id int not null identity(1,1),
Nombre varchar (20),
Tabla1id1 int,
Tabla1id2 int,
CONSTRAINT pk_tabla2
PRIMARY KEY (Tabla2id),
CONSTRAINT fk_tabla2_tabla1
FOREIGN KEY (Tabla1id1,Tabla1id2)
REFERENCES Tabla1(Tabla1id1,Tablaid2)
);
--Razon de cardinalidad 1-1
CREATE TABLE Employee(
id int not null identity (1,1),
Nombre varchar(20) not null,
Ape1 varchar(15),
Ape2 varchar(15),
sexo char(1) not null,
Salario money not null,
CONSTRAINT pk_employee
PRIMARY KEY(id),
CONSTRAINT chk_sexo2
CHECK (sexo in ('M','F')),
CONSTRAINT chk_Salario
CHECK (Salario>0.0)
);
GO

CREATE TABLE Department(
Id int not null identity(1,1),
NombreProyecto varchar(20) not null,
Ubicacion varchar(15) not null,
FechaInicio Date not null,
IdEmployee int not null,
CONSTRAINT pk_department
PRIMARY KEY(Id),
CONSTRAINT unique_nombreproyecto
UNIQUE(NombreProyecto),
CONSTRAINT unique_idemployee
UNIQUE (IdEmployee),
CONSTRAINT fk_department_employee
FOREIGN KEY(IdEmployee)
REFERENCES Employee(Id) 
);
CREATE TABLE Project(
idProject int not null identity (1,1),
Nombrepro varchar(20) not null
CONSTRAINT pk_project
PRIMARY KEY(idProject),
CONSTRAINT unique_nombrepro
UNIQUE(NombrePro)
);
CREATE TABLE Work_on(
IdEmployee1 int not null,
IdProject int not null,
horas int not null,
CONSTRAINT unique_idemployee
UNIQUE (IdEmployee1, IdProject),
CONSTRAINT fk_work_on_employee
FOREIGN KEY(IdEmployee1)
REFERENCES Employee(Id),
CONSTRAINT unique_idproject
UNIQUE (IdProject),
CONSTRAINT fk_work_on_project
FOREIGN KEY(IdProject)
REFERENCES Project(idProject) 
);
