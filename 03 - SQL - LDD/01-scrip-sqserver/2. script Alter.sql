CREATE DATABASE empresacucha;
GO
--utilizar la base de datos 
USE empresacucha;
GO
--crear tabla empleado 
CREATE TABLE empleados(
IdEmpleado int not null IDENTITY (1,1),
Nombre VARCHAR (100) NOT NULL,
Puesto VARCHAR (20) NOT NULL,
FechaIngreso date,
Salario DECIMAL (10,2),
CONSTRAINT pk_empleado
PRIMARY KEY (IdEmpleado)
);
--crear la tabla departamento 
CREATE TABLE departamentos(
IdDepto int not null primary key,
NombreDepto varchar (20),
);
GO
--Agregar una columna a la tabla empleados 
ALTER TABLE Empleados--Altera una tabla existente 
ADD CorreoElectronico VARCHAR(20) null;
GO
SELECT* FROM empleados;
--modificar 
ALTER TABLE empleados
ALTER COLUMN Salario money not null;
GO
--renombrar columna correo electronico de la tavbal empleados
EXECUTE sp_rename 
				'Empleados.CorreoElectronico',
				'Email','COLUMN';
GO
SELECT*FROM empleados;
GO
--agregar columna a empleados para foreign key 
ALTER TABLE Empleados 
ADD IdDepto int;
GO
--Agregar un constraint de foreign key 
ALTER TABLE Empleados
ADD CONSTRAINT fk_empleado_depto
FOREIGN KEY (IdDepto)
REFERENCES Departamentos(IdDepto);
GO
ALTER TABLE empleados
ADD CONSTRAINT chk_salario
CHECK (Salario>=100 and salario<=100000)
GO
--Agregar un constraint unique al nombre del
--departamento
ALTER TABLE departamentos
ADD CONSTRAINT unique_nombredepto
UNIQUE(NombreDepto);
GO


--Eliminar un constraint
ALTER TABLE empleados
DROP CONSTRAINT fk_empleado_depto
--
SELECT *FROM empleados;
SELECT*FROM departamentos;
GO

--
ALTER TABLE Departamentos 
DROP CONSTRAINT [PK__dedepart__7AEC424EED590DD8]
