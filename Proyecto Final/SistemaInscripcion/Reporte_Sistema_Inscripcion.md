# Reporte Técnico: Sistema de Inscripción de Estudiantes

## 1. Descripción General

El Sistema de Inscripción de Estudiantes es una aplicación de escritorio desarrollada con JavaFX y MySQL que permite gestionar estudiantes, cursos y el proceso de inscripción entre ambos. La aplicación implementa un sistema completo de CRUD (Crear, Leer, Actualizar, Eliminar) para las entidades principales y maneja la relación muchos a muchos entre estudiantes y cursos.

## 2. Arquitectura del Sistema

El sistema está estructurado siguiendo el patrón de diseño Modelo-Vista-Controlador (MVC):

### 2.1 Modelo
- **Entidades principales**: `Estudiante`, `Curso` e `Inscripcion`
- **Capa de acceso a datos (DAO)**: Interfaces e implementaciones para cada entidad
- **Utilidades**: Conexión a base de datos, validación y gestión de alertas

### 2.2 Vista
- Interfaces FXML para la gestión de estudiantes, cursos e inscripciones
- Sistema de pestañas para navegación entre módulos
- Formularios de entrada con validación
- Tablas para visualización de datos

### 2.3 Controlador
- Controladores específicos para cada vista
- Lógica de negocio para validación y procesamiento
- Gestión de eventos de la interfaz de usuario

## 3. Base de Datos

### 3.1 Modelo Entidad-Relación
- **Estudiante**: Almacena información personal de los estudiantes
- **Curso**: Contiene datos sobre los cursos disponibles
- **Inscripcion**: Tabla de relación muchos a muchos entre estudiantes y cursos

### 3.2 Características
- Restricciones de integridad referencial
- Validaciones a nivel de base de datos
- Índices para optimización de consultas

## 4. Funcionalidades Principales

### 4.1 Gestión de Estudiantes
- Registro de nuevos estudiantes
- Actualización de datos personales
- Eliminación de estudiantes
- Búsqueda por nombre o correo electrónico

### 4.2 Gestión de Cursos
- Creación de nuevos cursos
- Modificación de información de cursos
- Eliminación de cursos
- Búsqueda por nombre o créditos

### 4.3 Gestión de Inscripciones
- Inscripción de estudiantes en cursos
- Visualización de cursos por estudiante
- Visualización de estudiantes por curso
- Eliminación de inscripciones

### 4.4 Validaciones y Control de Errores
- Validación de campos obligatorios
- Validación de formato de correo electrónico
- Control de cupo máximo en cursos
- Prevención de inscripciones duplicadas

## 5. Tecnologías Utilizadas

### 5.1 Frontend
- **JavaFX 17**: Framework para interfaces gráficas
- **FXML**: Lenguaje de marcado para definir interfaces
- **CSS**: Estilizado de componentes visuales

### 5.2 Backend
- **Java**: Lenguaje de programación principal
- **JDBC**: API para conexión con bases de datos

### 5.3 Base de Datos
- **MySQL**: Sistema de gestión de bases de datos relacional

### 5.4 Herramientas de Desarrollo
- **Maven**: Gestión de dependencias y construcción

## 6. Estructura del Proyecto

```
SistemaInscripcion/
├── database/                  # Scripts SQL y modelo ER
├── src/
│   └── main/
│       ├── java/com/inscripcion/
│       │   ├── controller/    # Controladores JavaFX
│       │   ├── dao/           # Interfaces DAO
│       │   │   └── impl/      # Implementaciones DAO
│       │   ├── model/         # Clases de entidad
│       │   └── util/          # Clases utilitarias
│       └── resources/
│           └── com/inscripcion/view/  # Archivos FXML
└── pom.xml                    # Configuración Maven
```

## 7. Conclusiones y Recomendaciones

### 7.1 Fortalezas
- Arquitectura modular y bien estructurada
- Separación clara de responsabilidades (MVC)
- Validación robusta de datos
- Interfaz intuitiva y fácil de usar

### 7.2 Posibles Mejoras
- Implementación de reportes exportables (PDF, Excel)
- Autenticación de usuarios y control de acceso
- Funcionalidades estadísticas
- Respaldo y restauración de datos

Este sistema proporciona una solución completa para la gestión de inscripciones de estudiantes a cursos, con una arquitectura sólida y extensible que permite futuras ampliaciones de funcionalidad.