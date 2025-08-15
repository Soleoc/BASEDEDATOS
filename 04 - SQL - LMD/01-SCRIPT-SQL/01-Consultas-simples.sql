--SQL-LMD(INSERT,DELETE,UPDATE,SELECT)
use NORTHWND;
GO

SELECT *
FROM Categories;
SELECT*FROM	
Products;
SELECT*
FROM Suppliers;
SELECT*
FROM Customers;
SELECT*
FROM Employees;
SELECT*
FROM Shippers;
SELECT*
FROM Orders;
SELECT*
FROM [Order Details];
SELECT customerid,CompanyName,City
FROM Customers;
--Alias de columna 
SELECT CustomerID AS 'Numero Empleado',
		CompanyName Empresa,
		city AS Ciudad,
		ContactName AS [Nombre del Contacto]
FROM Customers;
--Alias de tabla
SELECT Customers.CustomerID AS [Numero Cliente],
	   Customers.CompanyName AS [Empresa],
	   Customers.ContactName AS [Nombre del Contacto]
FROM Customers;
SELECT c.CustomerID AS [Numero Cliente],
	   c.CompanyName AS [Empresa],
	   c.ContactName AS [Nombre del Contacto]
FROM Customers AS c;
SELECT c.CustomerID AS [Numero Cliente],
	   c.CompanyName AS [Empresa],
	   c.ContactName AS [Nombre del Contacto]
FROM Customers c;
--Campo calculado 
SELECT*,(UnitPrice*Quantity) AS [Total]
FROM [Order Details];
SELECT od.OrderID AS[Numero de orden],
od.ProductID AS[Numero de producto],
od.UnitPrice AS [Precio],
od.Quantity AS [Cantidad],
(UnitPrice*Quantity) AS [Total]
FROM [Order Details] AS od;
--Selecciona todos los productos quje pertenezcan a 
--la categoria 1
select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.CategoryID AS [Categoria],
	   p.UnitPrice AS [Precio],
	   p.UnitsInStock AS [Cantidad],
	   (p.UnitPrice*p.UnitsInStock) AS [Costo]
from
Products AS p
WHERE CategoryID=1;
--Seleccionar todos los productos de la categoria condimens
select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.CategoryID AS [Categoria],
	   p.UnitPrice AS [Precio],
	   p.UnitsInStock AS [Cantidad],
	   (p.UnitPrice*p.UnitsInStock) AS [Costo]
from
Products AS p
WHERE CategoryID=2;
--Seleccionar que todos lols productos 
--que su precio sea mayor a
select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.UnitPrice AS [Precio]
from
Products AS p
WHERE p.UnitPrice>40.3;
select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.UnitPrice AS [Precio]
from
Products AS p
WHERE p.UnitPrice>=40;
--numero de categoria sea diferente a 3
select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.CategoryID AS [Categoria],
	   p.UnitPrice AS [Precio]
from
Products AS p
WHERE CategoryID!=3;
--Seleccionar todas las ordenes que sean de brasil -Rio de janeiro mostrando solo el no.de orden
--la fecga de orden, pais dde envio y la ciudad y su transportista 
SELECT o.OrderID AS [Numero],
       o.OrderDate AS [Fecha de orden],
	   o.ShipCountry AS[Pais de envio],
	   o.ShipCity AS [Ciudad],
	   o.ShipVia AS [Transportista]
FROM Orders AS o
WHERE o.ShipCity='Rio de Janeiro';

SELECT o.OrderID AS [Numero],
       o.OrderDate AS [Fecha de orden],
	   o.ShipCountry AS[Pais de envio],
	   o.ShipCity AS [Ciudad],
	   o.ShipVia AS [Transportista]
FROM Orders AS o
WHERE o.ShipRegion is null;


SELECT o.OrderID AS [Numero],
       o.OrderDate AS [Fecha de orden],
	   o.ShipCountry AS[Pais de envio],
	   o.ShipCity AS [Ciudad],
	   o.ShipVia AS [Transportista]
FROM Orders AS o
WHERE o.ShipRegion is not null;

--Seleccio9nar todas las ordenes enviadas a brazil, alemania y mexico y region
SELECT o.OrderID AS [Numero],
       o.OrderDate AS [Fecha de orden],
	   o.ShipCountry AS[Pais de envio],
	   o.ShipCity AS [Ciudad],
	   o.ShipVia AS [Transportista],
	   o.ShipRegion AS [Region]
FROM Orders AS o
WHERE (o.ShipCountry='Brazil' or o.ShipCountry='Germany' or o.ShipCountry='Mexico')and o.ShipRegion is null 
	  ORDER BY [Pais de envio] ASC, o.ShipCity DESC;

--Seleccionar las odernes de francia, alemania, argentina 

SELECT o.OrderID AS [Numero],
       o.OrderDate AS [Fecha de orden],
	   o.ShipCountry AS[Pais de envio],
	   o.ShipCity AS [Ciudad],
	   o.ShipVia AS [Transportista],
	   o.ShipRegion AS [Region]
FROM Orders AS o
WHERE o.ShipCountry in('France','Germany','Argentina') ;
--seleccionar todos lod productos descontiinuados, mayor a 50 y ordenados por el precio

select p.ProductID AS [Numero],
       p.ProductName AS [Nombre],
	   p.CategoryID AS [Categoria],
	   p.UnitPrice AS [Precio]
from
Products AS p
WHERE p.Discontinued=1 or p.UnitPrice>50
ORDER BY [Precio] DESC;

select *from Employees;

select p.FirstName AS [Nombre],
       p.HireDate AS [Fecha de contratacion]
from
Employees AS p
WHERE p.HireDate>'1994';

select p.ProductName AS [Nombre],
	   p.Discontinued AS [Estado]
from
Products AS p
WHERE p.Discontinued=0;

SELECT CONCAT (FirstName, '', LastName) AS [Nombre Completo],
HireDate AS [Fecha de contratacion ], YEAR (HireDate) AS [Año de contratacion],
MONTH (HireDate) AS [Mes de contratacion], DAY (HireDate) AS [Dia de contratacion]
FROM Employees
WHERE YEAR (HireDate) >1993;
--Mes de contratacion

SELECT CONCAT (FirstName, '', LastName) AS [Nombre Completo],
HireDate AS [Fecha de contratacion ], YEAR (HireDate) AS [Año de contratacion],
MONTH (HireDate) AS [Mes de contratacion], DAY (HireDate) AS [Dia de contratacion]
FROM Employees
WHERE MONTH (HireDate)=04;

SELECT CONCAT (FirstName, '', LastName) AS [Nombre Completo],
HireDate AS [Fecha de contratacion ], YEAR (HireDate) AS [Año de contratacion],
MONTH (HireDate) AS [Mes de contratacion], DAY (HireDate) AS [Dia de contratacion],
DATEPART(QUARTER, HireDate) AS [Trimestre],DATENAME(MONTH,HireDate) AS [Mes],
DATEPART(WEEKDAY,HireDate) AS [Día]
FROM Employees
WHERE YEAR (HireDate) >2;

select *from Products;

select p.ProductName AS [Nombre],
	   p.SupplierID AS [Proveedor],
	   p.UnitsInStock AS [Existencia],
	   p.UnitPrice AS [Precio unitario]
from
Products AS p
WHERE p.UnitPrice>50 and p.UnitsInStock<20;
--unir dos tablas 

select p.ProductName AS [Nombre],
	   p.SupplierID AS [Proveedor],
	   p.UnitsInStock AS [Existencia],
	   p.UnitPrice AS [Precio unitario]
from
Products AS p, Suppliers
WHERE (p.SupplierID= Suppliers.SupplierID)
and (UnitPrice>50 and UnitsInStock<20);
 
SELECT p.ProductName AS [Nombre del producto],
s.SupplierID AS [Proveedor],
s.CompanyName AS [Nombre del proveedor],
p.UnitsInStock AS [Existencia],
p.UnitPrice AS [Precio Unitario]
FROM Products AS p
INNER JOIN Suppliers AS s
ON p.SupplierID = s.SupplierID
WHERE (p.SupplierID = s.SupplierID)
AND (UnitPrice > 50 and UnitsInStock < 20);

select*from Customers;




select p.CompanyName AS [Nombre],
	   p.Country AS [Pais]
from
Suppliers AS p
WHERE p.Country!='USA';

select *from Suppliers;
select p.CompanyName AS [Cliente],
	   p.City AS [ciudad],
	   p.Country AS [Pais],
	   p.Region AS [Region]
from
Customers AS p
WHERE (p.Country='Mexico' or p.Country='Spain') and p.Region is not null;

select p.ProductName AS [Nombre],
	   p.SupplierID AS [Proveedor],
	   p.UnitsInStock AS [Existencia],
	   p.UnitPrice AS [Precio unitario],
	   (p.UnitPrice*p.UnitsInStock) as [Precio inventario]
from
Products AS p
WHERE p.Discontinued=1 and p.UnitsInStock>0;

Select*from Products;

select p.CompanyName AS [Nombre],
	   p.City AS [Ciudad],
	   p.Country AS [Pais]
from
Suppliers AS p
WHERE p.Country!='USA' and p.Country!='UK';

--order by 
SELECT s.CompanyName AS [Empresa], s.Country AS [Pais],
s.City AS [Ciudad]
FROM Suppliers AS s
ORDER BY Country DESC, City ASC;

DECLARE @fecha date=GETDATE();

SELECT DATEPART (YEAR,'2025-07-23');

SELECT DATEPART (yy,'2025-07-23');

SELECT DATEPART (yyyy,'2025-07-23');

SELECT DATEPART (q,getdate());
--seleccionar todas aquellas ordenes que se realizaron 
SELECT max(year (OrderDate))
FROM Orders;

select p.OrderDate AS [Fecha],
	   p.OrderID AS [numero]
from
Orders AS p
WHERE DATEPART(yy,OrderDate)=1998;

--trimestre 
select p.OrderDate AS [Fecha],
	   p.OrderID AS [numero]
from
Orders AS p
WHERE DATEPART(q,OrderDate)=3;

select p.OrderDate AS [Fecha],
	   p.OrderID AS [numero],
	   DateName(month,OrderDate) AS 'Nombre del mes',
	   DATEPART(month,OrderDate) AS 'Numero del mes',
	   DateName(WEEKDAY,OrderDate) AS 'Nombre del dia'

from
Orders AS p
WHERE month(OrderDate)=7 or month(OrderDate)=8 or month(OrderDate)=9 ;

select p.OrderDate AS [Fecha],
	   p.OrderID AS [numero],
	   DateName(month,OrderDate) AS 'Nombre del mes',
	   DATEPART(month,OrderDate) AS 'Numero del mes',
	   DateName(WEEKDAY,OrderDate) AS 'Nombre del dia'

from
Orders AS p
WHERE DATEPART(q, OrderDate)=3;
--seleccionar los paises de nuestros clientes 
select DISTINCT Country, City
from Customers
ORDER BY Country;
--seleccionar todos los paises y ciudades unicas de todos los proveedores 
select DISTINCT Country, City
from Suppliers;

--Mostrar las fechas distintas de la compra 
select DISTINCT OrderDate
from Orders;

--Mostrar las categorias distintas de los productos 
select DISTINCT CategoryID
from Products;
--seleccionar todas las ordenews con mas de 30 dias de la fecha requerida hasta la fecha de pedido 
SELECT OrderDate, RequiredDate, 
datediff (day, OrderDate, RequiredDate) AS 'Dias'
FROM Orders;

SELECT OrderDate, RequiredDate,(OrderDate- RequiredDate) AS 'Dias'
FROM Orders;

--SELECT OrderDate, RequiredDate, 
--(CAST(OrderDate as date )-CAST(RequiredDate as date))
--FROM Orders;

SELECT OrderDate, RequiredDate, 
datediff (day, OrderDate, RequiredDate) AS 'Dias'
FROM Orders
WHERE datediff (day, OrderDate, RequiredDate)>30;

--SEleccionar todos los productos cuyo precio unitario con un impuesto del 20% son mayores a 30
Select *
From Products
WHERE UnitPrice*1.20>30;

--Seleccionar todas las ordenes cuyo total es mayor a 100 
SELECT (UnitPrice * Quantity) AS [Total]
FROM [Order Details]
WHERE (UnitPrice * Quantity) > 100;

--test de rango (between)
--entre 20 y 50
SELECT*
FROM Products
WHERE UnitPrice>=20 and UnitPrice<=30;

SELECT*
FROM Products
WHERE UnitPrice between 20 and 30;

--utilizar la baase de datos bdejemplo
use BDEJEMPLO2;
 --listar los diferentes puestos de losrepresentantes (Distinc=eliminacion de duplicados, coloco antes del campo)
 SELECT DISTINCT Puesto
 FROM Representantes;
 --SELECCIONAR LOS PAISES EN DONDE TENEMOS CLIENTES 
 SELECT DISTINCT country
 FROM Customers;

 --distinct afecta ambos campos o más
  SELECT DISTINCT country, city
  FROM Customers
  order by country;
  --mysql
  --listar los  pedidos con importe más alto 
  --SELECT TOP 5 Num_Pedido, Fecha_Pedido, Fab, Producto,Cantidad, Importe--esto es con sql
  --FROM Pedidos
  --order by pedido desc
  --LIMIT 5;--esto solo es en mysql 

  SELECT TOP 5 Num_Pedido, Fecha_Pedido, Fab, Producto,Cantidad, Importe--esto es con sql
  FROM Pedidos 
  ORDER BY 6 DESC;

  SELECT TOP 5 Num_Pedido, Fecha_Pedido, Fab, Producto,Cantidad, Importe--esto es con sql
  FROM Pedidos 
  ORDER BY Importe DESC;

  SELECT TOP 5 Num_Pedido, Fecha_Pedido, Fab, Producto,Cantidad, Importe AS [total]--esto es con sql
  FROM Pedidos 
  ORDER BY [Total] DESC;

  --test de encaje de patrones 
  --seleccionar todos los clientes que comiencen con a
  --para hacer esto debo de colocar el porcentaje 
  SELECT*
  FROM Clientes
  WHERE Empresa like '%l';--final

  SELECT*
  FROM Clientes
  WHERE Empresa like '%er%';--en cualquier lugar

  SELECT*
  FROM Clientes
  WHERE Empresa like '_A%';

    SELECT*
  FROM Clientes
  WHERE Empresa like '[A-D]%';--inicien 

    SELECT*
  FROM Clientes
  WHERE Empresa like '[abcx]%';

    SELECT*
  FROM Clientes
  WHERE Empresa like '[^àbcx]';--que no 

    SELECT*
  FROM Clientes
  WHERE Empresa like '_organ';

  insert into Clientes
  values
  (2126,'Morgan',108,678),
  (2127,'Porgan',108,778);

  --hallar todos los representantes que:
  --a)trabajan en daimiel, navarra o catellón, o bien 
  --b)no tienen jefe y estan contratados desde junio de 1988; o 
  --c)superan su cuota pero tiene ventas de 600000 o menos 
  SELECT Nombre,Jefe, Cuota,Ventas,Fecha_Contrato
  FROM Representantes
  WHERE Oficina_Rep=11 or Oficina_Rep=12 or Oficina_Rep=22 or Jefe is null and Fecha_Contrato>1988
  or Cuota<=600000;

    SELECT Nombre,Jefe, Cuota,Ventas,Fecha_Contrato
  FROM Representantes
  WHERE (Oficina_Rep in(22,11,12))
  or 
  (Jefe is null and Fecha_Contrato>='198806-01')
  or 
  (Ventas>Cuota and not Ventas>600000)
  order by Ventas desc;

  SELECT r.Nombre,r.Jefe, r.Cuota,r.Ventas,r.Fecha_Contrato,
		 o.Oficina
  FROM Representantes AS r
  inner join 
  Oficinas as o
  on o.Oficina= r.Oficina_Rep
  WHERE (o.Ciudad in('Navarra','Castellón','Daimiel'))
  or 
  (r.Jefe is null and r.Fecha_Contrato>='198806-01')
  or 
  (r.Ventas>Cuota and not r.Ventas>600000)
  order by r.Ventas desc
  ;

