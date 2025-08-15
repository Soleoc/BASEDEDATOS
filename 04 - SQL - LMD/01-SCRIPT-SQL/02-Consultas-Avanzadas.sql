  use NORTHWND;
  --listar todos los pedidos moestrando el numero de pedido, 
  --importe, nombre y limite de credito del cliente 
  SELECT p.Num_Pedido, p.Importe, c.Limite_Credito, c.Empresa
  FROM Pedidos AS p
  inner join
  Clientes AS c
  on c.Num_Cli=p.Cliente
  order by Importe desc;

  SELECT p.Num_Pedido, p.Importe, c.Limite_Credito, c.Empresa
  FROM Pedidos AS p
  inner join
  Clientes AS c
  on c.Num_Cli=p.Cliente
  where Limite_Credito between 25000 and 50000
        and Empresa like 'A%'
  order by Importe desc;

  select * from Representantes;
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
  --Seleccionar los productos incluyendo la categoria y el proveedor que lo surte
  SELECT ProductID,ProductName,UnitPrice,UnitsInStock,CategoryID,SupplierID
  FROM Products;

   SELECT ProductID AS [Número producto],
          ProductName AS [Nombre producto],
		  UnitPrice AS [Precio],
		  UnitsInStock AS [Existencia],
		  CategoryID AS [Categoria],
		  SupplierID AS [Proveedor]
  FROM Products;

  SELECT
  *FROM Products
  INNER JOIN  Categories
  ON Products.CategoryID=Categories.CategoryID;

  SELECT
  *FROM Products AS [Pr]
  INNER JOIN  Categories AS ca
  ON Pr.CategoryID=ca.CategoryID;

  SELECT pr.ProductID AS [Número producto],
         pr.ProductName AS [Nombre producto],
		 pr.UnitPrice AS [Precio],
		 pr.UnitsInStock AS [Existencia],
		 ca.CategoryName AS [Categoria],
		 su.CompanyName AS  [Proveedor]
  FROM Products AS [pr]
  INNER JOIN  Categories AS ca
  ON pr.CategoryID=ca.CategoryID
  INNER JOIN Suppliers AS [su]
  ON pr.SupplierID=su.SupplierID;--se debe de colocar la fk de la tabla y pk de la table que deriva (recuerda de 1:N)

   SELECT *
  FROM Products AS [pr]
  INNER JOIN  Categories AS ca
  ON pr.CategoryID=ca.CategoryID
  INNER JOIN Suppliers AS [su]
  ON pr.SupplierID=su.SupplierID;
  --Nota los left te muestra los datos de la tabla que esta al inicio aunque sus campos esten nulos 
  use pruebajoins;
  SELECT *FROM Categorias;
  SELECT *FROM Productos;
  SELECT*
  FROM Categorias AS c
       INNER JOIN Productos AS p
	   ON c.Idcategoria= p.Idcategoria;

  SELECT*
  FROM Categorias AS c
       LEFT JOIN  Productos AS p
	   ON c.Idcategoria= p.Idcategoria;

  SELECT c.idCategoria,c.Nombe, p.Nombre, p.precio, p.idProductp
  FROM Categorias AS c
       LEFT OUTER JOIN Productos AS p
	   ON c.Idcategoria= p.Idcategoria
	   WHERE p.idProductp is null;


 SELECT c.idCategoria,c.Nombe, p.Nombre, p.precio, p.idProductp
  FROM Categorias AS c
       RIGHT JOIN Productos AS p
	   ON c.Idcategoria= p.Idcategoria
	   WHERE c.idCategoria is null;
-------------------
use BDEJEMPLO2;
--listar las oficinas y los nombres y puestos de sus jefes 
 SELECT o.Oficina,o.Jef,r.Nombre
  FROM Representantes AS r
       JOIN Oficinas AS o
	   ON r.Oficina_Rep=o.Oficina;

--listar las oficinas y los nombres y puestos de sus jefes con un objeto superior a 600000
 SELECT o.Oficina,o.Jef,r.Nombre, o.Objetivo
  FROM Oficinas AS o
       JOIN Representantes AS r
	   ON o.Jef= r.Num_Empl
	   WHERE O.Objetivo>600000;

--listar todos los pedidos, mostrando el numero de pedido, el importe y la descripcionde los productos 
 SELECT p.Num_Pedido, pr.Descripcion, p.Importe
  FROM Pedidos AS p
       JOIN Productos AS pr
	   ON (pr.Id_fab=p.Fab) and (pr.Id_producto=p.Producto);

--listar los pedidos superiores a 25000, incluyendo el numero de pedido, importe, el nombre del representante quee tomo la 
--nota del pedido y el nombre del cliente
SELECT pe.Importe, pe.Num_Pedido,re.Nombre,cl.Empresa
  FROM Pedidos AS [pe]
  INNER JOIN  Representantes AS re
  ON re.Num_Empl=pe.Rep
  INNER JOIN Clientes AS [cl]
  ON cl.Num_Cli=pe.Cliente
where pe.Importe>25000;
--listar los pedidos superirores a 25000, mostrando el numero de pedido, el noombre del cliemte que lo encargó y 
--el nombre del representante asignado al cliente
SELECT pe.Importe, pe.Num_Pedido,re.Nombre,cl.Empresa, re.Nombre
  FROM Pedidos AS [pe]
  INNER JOIN  Representantes AS re
  ON re.Num_Empl=pe.Rep
  INNER JOIN Clientes AS [cl]
  ON cl.Num_Cli=pe.Cliente
where pe.Importe>25000;
--listar los pedidos superiores de 25000, mostrando el numero de pedido el nombre del cliente que lo encargo,
--el representante del cliente y la oficina en la que trabaja el reepresentamnte 


--listar los nombres de los empleados y sus jefes 
SELECT su.Nombre as [Empleado],
       jefe.Nombre AS [Jefe]
FROM Representantes as jefe
inner join Representantes su
on jefe.Num_Empl=su.Jefe;

--Consultas de agregado (Max, min, avg, count(*),count(campo))
--¿Cuál es el rendimiento medio de la cuota de los representantes?
SELECT AVG (Cuota) AS [Rendimiento medio de las cuotas]
FROM Representantes;
--cual es la cuota mayor 
SELECT MAX (Cuota) AS [Cuota mayor]
FROM Representantes;
--cual es la cuota menor 
SELECT MIN (Cuota) AS [Cuota menor]
FROM Representantes;
--cual es la cuota mayor y menor 
SELECT MIN (Cuota) AS [Cuota menor],
MAX (Cuota) AS [Cuota mayor]
FROM Representantes;
--seleccionar es la fecha de pedido mas antigua
SELECT MIN (Fecha_Pedido) AS [Pedido más antiguo]
FROM Pedidos;
--calcular el rendimiento de los representantes 
SELECT 100*(Ventas/Cuota) AS [Rendimiento de ventas]
FROM Representantes
WHERE 100*(Ventas/Cuota) is null;
--Calcular el rendimientoo medio en ventas
SELECT AVG(100*(Ventas/Cuota)) AS [Rendimiento medio]--PROMEDIO=AVG
FROM Representantes;
--cuales son las cuotas y ventas totales de todos los representantes 
SELECT SUM (Cuota),SUM(Ventas)
FROM Representantes;
--cual es el importe total de bruno arteaga 
SELECT r.nombre, sum (p.Importe)
FROM Representantes AS r
INNER JOIN Pedidos AS p
ON r.Num_Empl=p.Rep
WHERE r.Nombre='Bruno Arteaga'
GROUP BY r.Nombre;
--cuantos representantes exceden su cuota (count)--cuenta los valores del campo 
select count(*), count (cuota) from 
Representantes
WHERE Ventas>Cuota;
--mayor a 25000
SELECT count(*), count (pe.Importe) 
  FROM Pedidos AS [pe]
where pe.Importe>25000;
--cuantos puestos tienen los representantes 
select*from Representantes;
SELECT  count (distinct Puesto) --distinc quita valores repetidos
  FROM Representantes;
--cual es el importe d¿medio de cada uno de los representantes 
SELECT Nombre, AVG (DISTINCT IMPORTE) AS [Importe]
FROM Pedidos AS p
INNER JOIN 
Representantes AS r
ON p.Rep=r.Num_Empl
GROUP BY r.Nombre
ORDER BY Nombre;

SELECT Nombre, AVG (DISTINCT IMPORTE) AS [Importe]
FROM Pedidos AS p
INNER JOIN 
Representantes AS r
ON p.Rep=r.Num_Empl
WHERE r.Nombre IN ('Tomás Saz','María Jiménez','Pablo Cruz')
GROUP BY r.Nombre
ORDER BY Nombre;
CREATE OR ALTER PROCEDURE sp_pruebaconsulta
@fechaInicial date,
@fechaFinal date
AS 
BEGIN 
SELECT Nombre, AVG (DISTINCT IMPORTE) AS [Importe]
FROM Pedidos AS p
INNER JOIN 
Representantes AS r
ON p.Rep=r.Num_Empl
where Fecha_Pedido between @fechaInicial and @fechaFinal
GROUP BY r.Nombre
ORDER BY Nombre
end;
exec  sp_pruebaconsulta '1989-01-01', '1989-04-06';
select * from Pedidos;
--cual es el rango de las cuotas asignadas de cada oficina (ciudad)
