```sql 
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