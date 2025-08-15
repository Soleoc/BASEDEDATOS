# Modelo Entidad-Relación: Sistema de Inscripción de Estudiantes a Cursos

## Entidades

### Estudiante
- **id_estudiante**: INT (PK) - Identificador único del estudiante
- **nombre**: VARCHAR(100) - Nombre completo del estudiante
- **correo**: VARCHAR(100) - Correo electrónico (único)
- **telefono**: VARCHAR(15) - Número de teléfono
- **fecha_nacimiento**: DATE - Fecha de nacimiento
- **direccion**: VARCHAR(200) - Dirección del estudiante

### Curso
- **id_curso**: INT (PK) - Identificador único del curso
- **nombre**: VARCHAR(100) - Nombre del curso
- **descripcion**: TEXT - Descripción detallada del curso
- **creditos**: INT - Número de créditos del curso
- **cupo_maximo**: INT - Cupo máximo de estudiantes

## Relaciones

### Inscripcion (Relación muchos a muchos entre Estudiante y Curso)
- **id_estudiante**: INT (FK) - Referencia a Estudiante
- **id_curso**: INT (FK) - Referencia a Curso
- **fecha_inscripcion**: DATETIME - Fecha y hora de la inscripción

## Restricciones

- La combinación de **id_estudiante** e **id_curso** en la tabla Inscripcion debe ser única (no puede haber inscripciones duplicadas)
- El correo del estudiante debe ser único
- Los créditos de un curso deben ser un número positivo
- El cupo máximo de un curso debe ser un número positivo