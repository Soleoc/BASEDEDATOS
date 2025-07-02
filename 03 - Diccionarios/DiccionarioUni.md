### 🎓 Tabla: Estudiante

| Campo        | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK     | Referencia a | Descripción                     |
|--------------|--------------|--------|-----|-----|------|--------|----------------------------|--------------|---------------------------------|
| Matricula    | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                        | -            | Matrícula del estudiante        |
| nombre       | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3              | -            | Nombre completo del estudiante  |
| carrera      | NVARCHAR     | 30     | ❌  | ❌  | ❌   | ❌     | -                          | -            | Carrera que cursa               |
| semestre     | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (semestre BETWEEN 1 AND 12) | -      | Semestre actual del estudiante  |

### 📘 Tabla: Curso

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK     | Referencia a | Descripción                    |
|------------|--------------|--------|-----|-----|------|--------|----------------------------|--------------|--------------------------------|
| IDcurso    | INT          | -      | ✅  | ❌  | ❌   | ✅     | > 0                        | -            | Identificador del curso        |
| nombre     | NVARCHAR     | 40     | ❌  | ❌  | ❌   | ❌     | Longitud >= 3              | -            | Nombre del curso               |
| creditos   | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (creditos > 0)       | -            | Créditos asignados al curso    |


### 📝 Tabla: Inscripcion

| Campo      | Tipo de Dato | Tamaño | PK  | FK  | Nulo | Único | Restricciones / CHECK | Referencia a | Descripción                          |
|------------|--------------|--------|-----|-----|------|--------|------------------------|--------------|--------------------------------------|
| Matricula  | INT          | -      | ✅  | ✅  | ❌   | ❌     | > 0                    | Estudiante   | Matrícula del estudiante inscrito    |
| IDcurso    | INT          | -      | ✅  | ✅  | ❌   | ❌     | > 0                    | Curso        | Identificador del curso inscrito     |
| semestre   | INT          | -      | ❌  | ❌  | ❌   | ❌     | CHECK (semestre >= 1)  | -            | Semestre en el que se inscribió      |

