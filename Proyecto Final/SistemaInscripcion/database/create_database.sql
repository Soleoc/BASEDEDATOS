-- Script para la creación de la base de datos del Sistema de Inscripción de Estudiantes a Cursos

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS sistema_inscripcion;

-- Usar la base de datos
USE sistema_inscripcion;

-- Crear tabla Estudiante
CREATE TABLE IF NOT EXISTS estudiante (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    fecha_nacimiento DATE,
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_correo_formato CHECK (correo LIKE '%@%.%')
);

-- Crear tabla Curso
CREATE TABLE IF NOT EXISTS curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    creditos INT NOT NULL,
    cupo_maximo INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_creditos_positivos CHECK (creditos > 0),
    CONSTRAINT chk_cupo_positivo CHECK (cupo_maximo > 0)
);

-- Crear tabla Inscripcion (relación muchos a muchos)
CREATE TABLE IF NOT EXISTS inscripcion (
    id_estudiante INT,
    id_curso INT,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_estudiante, id_curso),
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE
);

-- Crear índices para mejorar el rendimiento de las búsquedas
CREATE INDEX idx_estudiante_nombre ON estudiante(nombre);
CREATE INDEX idx_estudiante_correo ON estudiante(correo);
CREATE INDEX idx_curso_nombre ON curso(nombre);
CREATE INDEX idx_curso_creditos ON curso(creditos);

-- Insertar algunos datos de ejemplo

-- Estudiantes de ejemplo
INSERT INTO estudiante (nombre, correo, telefono, fecha_nacimiento, direccion) VALUES
('Juan Pérez', 'juan.perez@ejemplo.com', '555-123-4567', '2000-05-15', 'Calle Principal 123'),
('María García', 'maria.garcia@ejemplo.com', '555-987-6543', '2001-08-22', 'Avenida Central 456'),
('Carlos López', 'carlos.lopez@ejemplo.com', '555-456-7890', '1999-11-10', 'Boulevard Norte 789'),
('Ana Martínez', 'ana.martinez@ejemplo.com', '555-234-5678', '2002-03-30', 'Calle Sur 321'),
('Pedro Rodríguez', 'pedro.rodriguez@ejemplo.com', '555-876-5432', '2000-12-05', 'Avenida Este 654');

-- Cursos de ejemplo
INSERT INTO curso (nombre, descripcion, creditos, cupo_maximo) VALUES
('Programación Java', 'Curso introductorio a la programación en Java', 4, 30),
('Bases de Datos', 'Fundamentos de bases de datos relacionales', 3, 25),
('Matemáticas Discretas', 'Conceptos fundamentales de matemáticas para computación', 5, 35),
('Desarrollo Web', 'Creación de aplicaciones web con HTML, CSS y JavaScript', 4, 30),
('Inteligencia Artificial', 'Introducción a los conceptos de IA y aprendizaje automático', 5, 20);

-- Inscripciones de ejemplo
INSERT INTO inscripcion (id_estudiante, id_curso) VALUES
(1, 1), -- Juan en Programación Java
(1, 2), -- Juan en Bases de Datos
(2, 1), -- María en Programación Java
(2, 3), -- María en Matemáticas Discretas
(3, 2), -- Carlos en Bases de Datos
(3, 4), -- Carlos en Desarrollo Web
(4, 3), -- Ana en Matemáticas Discretas
(4, 5), -- Ana en Inteligencia Artificial
(5, 1), -- Pedro en Programación Java
(5, 5); -- Pedro en Inteligencia Artificial