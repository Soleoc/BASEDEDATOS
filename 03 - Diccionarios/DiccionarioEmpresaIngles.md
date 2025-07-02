### 🧑‍💼 Tabla: Employer

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK      | Referencia a | Descripción                           |
|------------|--------------|--------|-----|-----|------|--------|-----------------------------|--------------|---------------------------------------|
| SSN        | CHAR         | 9      | ✅  | ❌  | ❌   | ✅     | Formato numérico de 9 dígitos| -           | Número de Seguro Social del empleado  |
| Fname      | NVARCHAR     | 30     | ❌  | ❌  | ❌   | ❌     | Longitud >= 2               | -            | Nombre del empleado                   |
| Lname      | NVARCHAR     | 30     | ❌  | ❌  | ❌   | ❌     | -                           | -            | Apellido del empleado                 |
| Address    | NVARCHAR     | 60     | ❌  | ❌  | ❌   | ❌     | -                           | -            | Dirección del empleado                |
| Salary     | FLOAT        | -      | ❌  | ❌  | ❌   | ❌     | CHECK (Salary > 0)          | -            | Sueldo del empleado                   |
| Sex        | CHAR         | 1      | ❌  | ❌  | ❌   | ❌     | CHECK (Sex IN ('M','F'))    | -            | Sexo del empleado                     |
| Dno        | INT          | -      | ❌  | ✅  | ❌   | ❌     | > 0                         | Department   | Departamento al que pertenece         |

### 🏢 Tabla: Department

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK | Referencia a | Descripción                          |
|------------|--------------|--------|-----|-----|------|--------|------------------------|--------------|--------------------------------------|
| Dno        | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                    | -            | Identificador del departamento       |
| Dname      | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ✅     | Longitud >= 3          | -            | Nombre del departamento              |
| Location   | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ❌     | -                      | -            | Ubicación del departamento           |

