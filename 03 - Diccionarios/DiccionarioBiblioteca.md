### 📚 Tabla: Libro

| Campo     | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK      | Referencia a | Descripción                    |
|-----------|--------------|--------|-----|-----|------|--------|-----------------------------|--------------|--------------------------------|
| IDlibro   | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                         | -            | Identificador del libro        |
| Titulo    | NVARCHAR     | 60     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3               | -            | Título del libro               |
| Autor     | NVARCHAR     | 50     | ❌  | ❌  | ❌   | ❌     | -                           | -            | Autor del libro                |
| Editorial | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ❌     | -                           | -            | Editorial del libro            |
| Ejemplares| INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (Ejemplares >= 1)     | -            | Número de ejemplares disponibles |

### 👤 Tabla: Lector

| Campo     | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK      | Referencia a | Descripción                         |
|-----------|--------------|--------|-----|-----|------|--------|-----------------------------|--------------|-------------------------------------|
| IDlector  | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                         | -            | Identificador del lector            |
| nombre    | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3               | -            | Nombre del lector                   |
| direccion | NVARCHAR     | 60     | ❌  | ❌  | ❌   | ❌     | -                           | -            | Dirección del lector                |
| telefono  | NVARCHAR     | 10     | ❌  | ❌  | ❌   | ❌     | Formato numérico 10 dígitos | -            | Número de teléfono del lector       |


### 📖 Tabla: Presta

| Campo     | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK | Referencia a | Descripción                          |
|-----------|--------------|--------|-----|-----|------|--------|------------------------|--------------|--------------------------------------|
| IDlibro   | INT          | -      | ✅  | ✅  | ❌   | ❌     | > 0                    | Libro        | Identificador del libro prestado     |
| IDlector  | INT          | -      | ✅  | ✅  | ❌   | ❌     | > 0                    | Lector       | Identificador del lector             |
| fecha     | DATE         | -      | ❌  | ❌  | ❌   | ❌     | Fecha válida           | -            | Fecha en la que se realizó el préstamo |
