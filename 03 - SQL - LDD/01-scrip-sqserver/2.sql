--CREAR BD
CREATE DATABASE bdmorgan;
GO
--USAR LA BD
USE bdmorgan;

CREATE TABLE empleado
(
IdEmpleado int not null identity(1,1),
Nombre varchar(50) not null,
Apellido1 varchar(20) not null,
Apellido2 varchar(20) ,
edad int not null,
estatus char(1) not null default 'A'
);