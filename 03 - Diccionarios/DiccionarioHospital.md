### 🧾 Tabla: Doc

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK     | Referencia a | Descripción                         |
|------------|--------------|--------|-----|-----|------|--------|----------------------------|--------------|-------------------------------------|
| IDdoc      | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                        | -            | Identificador del doctor            |
| Especialidad | NVARCHAR   | 20     | ❌  | ❌  | ❌   | ❌     | -                          | -            | Especialidad médica                 |
| Edad       | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (Edad >= 25)         | -            | Edad del doctor                     |
| nombre     | NVARCHAR     | -      | ❌  | ❌  | ❌   | ❌     | Longitud >= 3              | -            | Nombre del doctor                   |
| apellido1  | NVARCHAR     | 30     | ❌  | ❌  | ❌   | ❌     | -                          | -            | Primer apellido del doctor          |
| apellido2  | NVARCHAR     | 20     | ❌  | ❌  | ✅   | ❌     | -                          | -            | Segundo apellido del doctor         |

### 🧾 Tabla: Paciente

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK     | Referencia a | Descripción                          |
|------------|--------------|--------|-----|-----|------|--------|----------------------------|--------------|--------------------------------------|
| IDpaciente | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                        | -            | Identificador del paciente           |
| nombre     | NVARCHAR     | 30     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3              | -            | Nombre del paciente                  |
| Edad       | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (Edad >= 0)          | -            | Edad del paciente                    |
| telefono   | NVARCHAR     | 10     | ❌  | ❌  | ❌   | ❌     | Formato numérico 10 dígitos| -            | Teléfono del paciente                |


### 🧾 Tabla: Atencion

| Campo       | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK     | Referencia a | Descripción                             |
|-------------|--------------|--------|-----|-----|------|--------|----------------------------|--------------|-----------------------------------------|
| IDatencion  | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                        | -            | Identificador de la atención médica     |
| IDdoc       | INT          | -      | ❌  | ✅  | ❌   | ❌     | > 0                        | Doc          | Doctor que brindó la atención           |
| IDpaciente  | INT          | -      | ❌  | ✅  | ❌   | ❌     | > 0                        | Paciente     | Paciente que recibió la atención        |
| Fecha       | DATE         | -      | ❌  | ❌  | ❌   | ❌     | Fecha válida               | -            | Fecha en que se dio la atención         |
