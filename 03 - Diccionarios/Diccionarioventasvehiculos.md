### 🧾 Tabla: Clientes

| Campo        | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK        | Referencia a | Descripción              |
|--------------|--------------|--------|-----|-----|------|--------|-------------------------------|--------------|--------------------------|
| Numcliente   | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                           | -            | Identificador del cliente |
| nombre       | NVARCHAR     | 50     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3                 | -            | Nombre del cliente       |
| apellido     | NVARCHAR     | 50     | ❌  | ❌  | ❌   | ❌     | -                             | -            | Apellido paterno         |
| apellido2    | NVARCHAR     | 50     | ❌  | ❌  | ✅   | ❌     | -                             | -            | Apellido materno         |
| curp         | NVARCHAR     | 18     | ❌  | ❌  | ❌   | ✅     | Formato CURP                  | -            | CURP del cliente         |
| telefono     | NCHAR        | 12     | ❌  | ❌  | ❌   | ❌     | Formato teléfono              | -            | Teléfono del cliente     |
| calle        | NVARCHAR     | 50     | ❌  | ❌  | ❌   | ❌     | -                             | -            | Calle del domicilio      |
| ciudad       | NVARCHAR     | 20     | ❌  | ❌  | ❌   | ❌     | -                             | -            | Ciudad del domicilio     |
| numero       | INT          | -      | ❌  | ❌  | ✅   | ❌     | > 0                           | -            | Número del domicilio     |

### 🧾 Tabla: Sucursal

| Campo       | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK | Referencia a | Descripción                  |
|-------------|--------------|--------|-----|-----|------|--------|------------------------|--------------|------------------------------|
| numsucursal | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                    | -            | Identificador de la sucursal |
| nombre      | NVARCHAR     | 20     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3          | -            | Nombre de la sucursal        |
| ubicacion   | NVARCHAR     | 25     | ❌  | ❌  | ❌   | ❌     | -                      | -            | Ubicación de la sucursal     |


### 🧾 Tabla: Vehiculo

| Campo        | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK | Referencia a   | Descripción                          |
|--------------|--------------|--------|-----|-----|------|--------|------------------------|----------------|--------------------------------------|
| numvehiculo  | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                    | -              | Identificador del vehículo           |
| placa        | NVARCHAR     | 8      | ❌  | ❌  | ❌   | ✅     | Formato de placa       | -              | Placa del vehículo                   |
| marca        | NVARCHAR     | 15     | ❌  | ❌  | ❌   | ❌     | -                      | -              | Marca del vehículo                   |
| modelo       | NVARCHAR     | 20     | ❌  | ❌  | ❌   | ❌     | -                      | -              | Modelo del vehículo                  |
| anio         | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (anio >= 1950)   | -              | Año de fabricación                   |
| numcliente   | INT          | -      | ❌  | ✅  | ❌   | ❌     | > 0                    | Clientes       | Cliente al que pertenece el vehículo |
| numsucursal  | INT          | -      | ❌  | ✅  | ❌   | ❌     | > 0                    | Sucursal       | Sucursal donde se registra el auto   |
