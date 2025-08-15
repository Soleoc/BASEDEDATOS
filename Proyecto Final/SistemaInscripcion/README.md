# Sistema de Inscripción de Estudiantes a Cursos

Este proyecto implementa un sistema de gestión para una institución educativa que permite administrar estudiantes y cursos, así como las inscripciones entre ellos (relación muchos a muchos).

## Características

- CRUD completo para Estudiantes
- CRUD completo para Cursos
- Gestión de inscripciones (relación muchos a muchos)
- Búsquedas y filtros
- Manejo de errores

## Tecnologías

- JavaFX para la interfaz de usuario
- MySQL para la persistencia de datos
- Java como lenguaje de programación

## Estructura del Proyecto

- `src/main/java/com/inscripcion/` - Código fuente
  - `model/` - Entidades y modelos de datos
  - `dao/` - Capa de acceso a datos
  - `controller/` - Controladores JavaFX
  - `view/` - Vistas FXML
  - `util/` - Clases utilitarias
- `src/main/resources/` - Recursos (FXML, CSS, etc.)
- `database/` - Scripts SQL para la creación de la base de datos