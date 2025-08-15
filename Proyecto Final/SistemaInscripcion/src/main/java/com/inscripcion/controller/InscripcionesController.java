package com.inscripcion.controller;

import com.inscripcion.dao.CursoDAO;
import com.inscripcion.dao.EstudianteDAO;
import com.inscripcion.dao.InscripcionDAO;
import com.inscripcion.dao.impl.CursoDAOImpl;
import com.inscripcion.dao.impl.EstudianteDAOImpl;
import com.inscripcion.dao.impl.InscripcionDAOImpl;
import com.inscripcion.model.Curso;
import com.inscripcion.model.Estudiante;
import com.inscripcion.model.Inscripcion;
import com.inscripcion.util.AlertUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de inscripciones.
 */
public class InscripcionesController implements Initializable {

    @FXML
    private ComboBox<Estudiante> comboEstudiantes;
    
    @FXML
    private ComboBox<Curso> comboCursos;
    
    @FXML
    private TableView<Curso> tablaCursosEstudiante;
    
    @FXML
    private TableColumn<Curso, String> colCursoNombre;
    
    @FXML
    private TableColumn<Curso, Integer> colCursoCreditos;
    
    @FXML
    private TableView<Estudiante> tablaEstudiantesCurso;
    
    @FXML
    private TableColumn<Estudiante, String> colEstudianteNombre;
    
    @FXML
    private TableColumn<Estudiante, String> colEstudianteCorreo;
    
    @FXML
    private TableView<Inscripcion> tablaInscripciones;
    
    @FXML
    private TableColumn<Inscripcion, String> colInscripcionEstudiante;
    
    @FXML
    private TableColumn<Inscripcion, String> colInscripcionCurso;
    
    @FXML
    private TableColumn<Inscripcion, String> colInscripcionFecha;
    
    private EstudianteDAO estudianteDAO;
    private CursoDAO cursoDAO;
    private InscripcionDAO inscripcionDAO;
    
    private ObservableList<Estudiante> listaEstudiantes;
    private ObservableList<Curso> listaCursos;
    private ObservableList<Curso> listaCursosEstudiante;
    private ObservableList<Estudiante> listaEstudiantesCurso;
    private ObservableList<Inscripcion> listaInscripciones;

    /**
     * Inicializa el controlador.
     * 
     * @param location Ubicación del FXML
     * @param resources Recursos
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        estudianteDAO = new EstudianteDAOImpl();
        cursoDAO = new CursoDAOImpl();
        inscripcionDAO = new InscripcionDAOImpl();
        
        listaEstudiantes = FXCollections.observableArrayList();
        listaCursos = FXCollections.observableArrayList();
        listaCursosEstudiante = FXCollections.observableArrayList();
        listaEstudiantesCurso = FXCollections.observableArrayList();
        listaInscripciones = FXCollections.observableArrayList();
        
        // Configurar las columnas de las tablas
        colCursoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCursoCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        
        colEstudianteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstudianteCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        
        colInscripcionEstudiante.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstudiante().getNombre()));
        colInscripcionCurso.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCurso().getNombre()));
        colInscripcionFecha.setCellValueFactory(cellData -> {
            if (cellData.getValue().getFechaInscripcion() != null) {
                return new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFechaInscripcion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        
        // Configurar los eventos de selección
        comboEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarCursosDeEstudiante(newSelection.getIdEstudiante());
            } else {
                listaCursosEstudiante.clear();
            }
        });
        
        comboCursos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarEstudiantesDeCurso(newSelection.getIdCurso());
            } else {
                listaEstudiantesCurso.clear();
            }
        });
        
        // Cargar datos iniciales
        cargarEstudiantes();
        cargarCursos();
        cargarTodasLasInscripciones();
    }
    
    /**
     * Carga todos los estudiantes en el combo.
     */
    private void cargarEstudiantes() {
        try {
            listaEstudiantes.clear();
            listaEstudiantes.addAll(estudianteDAO.obtenerTodos());
            comboEstudiantes.setItems(listaEstudiantes);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los estudiantes: " + e.getMessage());
        }
    }
    
    /**
     * Carga todos los cursos en el combo.
     */
    private void cargarCursos() {
        try {
            listaCursos.clear();
            listaCursos.addAll(cursoDAO.obtenerTodos());
            comboCursos.setItems(listaCursos);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los cursos: " + e.getMessage());
        }
    }
    
    /**
     * Carga los cursos en los que está inscrito un estudiante.
     * 
     * @param idEstudiante ID del estudiante
     */
    private void cargarCursosDeEstudiante(int idEstudiante) {
        try {
            listaCursosEstudiante.clear();
            listaCursosEstudiante.addAll(inscripcionDAO.obtenerCursosDeEstudiante(idEstudiante));
            tablaCursosEstudiante.setItems(listaCursosEstudiante);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los cursos del estudiante: " + e.getMessage());
        }
    }
    
    /**
     * Carga los estudiantes inscritos en un curso.
     * 
     * @param idCurso ID del curso
     */
    private void cargarEstudiantesDeCurso(int idCurso) {
        try {
            listaEstudiantesCurso.clear();
            listaEstudiantesCurso.addAll(inscripcionDAO.obtenerEstudiantesDeCurso(idCurso));
            tablaEstudiantesCurso.setItems(listaEstudiantesCurso);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los estudiantes del curso: " + e.getMessage());
        }
    }
    
    /**
     * Carga todas las inscripciones en la tabla.
     */
    private void cargarTodasLasInscripciones() {
        try {
            listaInscripciones.clear();
            listaInscripciones.addAll(inscripcionDAO.obtenerTodasLasInscripciones());
            tablaInscripciones.setItems(listaInscripciones);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar las inscripciones: " + e.getMessage());
        }
    }
    
    /**
     * Maneja el evento de clic en el botón Inscribir.
     */
    @FXML
    private void handleInscribir() {
        Estudiante estudiante = comboEstudiantes.getValue();
        Curso curso = comboCursos.getValue();
        
        if (estudiante == null || curso == null) {
            AlertUtils.mostrarAdvertencia("Selección", "Debe seleccionar un estudiante y un curso para inscribir.");
            return;
        }
        
        try {
            // Verificar si ya está inscrito
            if (inscripcionDAO.estaInscrito(estudiante.getIdEstudiante(), curso.getIdCurso())) {
                AlertUtils.mostrarAdvertencia("Inscripción duplicada", 
                        "El estudiante ya está inscrito en este curso.");
                return;
            }
            
            // Verificar si hay cupo disponible
            int estudiantesInscritos = cursoDAO.obtenerNumeroEstudiantesInscritos(curso.getIdCurso());
            if (estudiantesInscritos >= curso.getCupoMaximo()) {
                AlertUtils.mostrarAdvertencia("Cupo lleno", 
                        "El curso ha alcanzado su cupo máximo de estudiantes.");
                return;
            }
            
            // Realizar la inscripción
            if (inscripcionDAO.inscribir(estudiante.getIdEstudiante(), curso.getIdCurso())) {
                AlertUtils.mostrarInformacion("Éxito", "Inscripción realizada correctamente.");
                
                // Actualizar las tablas
                cargarCursosDeEstudiante(estudiante.getIdEstudiante());
                cargarEstudiantesDeCurso(curso.getIdCurso());
                cargarTodasLasInscripciones();
            } else {
                AlertUtils.mostrarError("Error", "No se pudo realizar la inscripción.");
            }
            
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al realizar la inscripción: " + e.getMessage());
        }
    }
    
    /**
     * Maneja el evento de clic en el botón Eliminar Inscripción (desde la tabla de cursos del estudiante).
     */
    @FXML
    private void handleEliminarInscripcionCurso() {
        Estudiante estudiante = comboEstudiantes.getValue();
        Curso curso = tablaCursosEstudiante.getSelectionModel().getSelectedItem();
        
        if (estudiante == null || curso == null) {
            AlertUtils.mostrarAdvertencia("Selección", 
                    "Debe seleccionar un estudiante y un curso de la tabla para eliminar la inscripción.");
            return;
        }
        
        boolean confirmar = AlertUtils.mostrarConfirmacion("Confirmar eliminación", 
                "¿Está seguro de eliminar la inscripción del estudiante " + estudiante.getNombre() + 
                " en el curso " + curso.getNombre() + "?");
        
        if (confirmar) {
            try {
                if (inscripcionDAO.eliminarInscripcion(estudiante.getIdEstudiante(), curso.getIdCurso())) {
                    AlertUtils.mostrarInformacion("Éxito", "Inscripción eliminada correctamente.");
                    
                    // Actualizar las tablas
                    cargarCursosDeEstudiante(estudiante.getIdEstudiante());
                    if (comboCursos.getValue() != null) {
                        cargarEstudiantesDeCurso(comboCursos.getValue().getIdCurso());
                    }
                    cargarTodasLasInscripciones();
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo eliminar la inscripción.");
                }
            } catch (SQLException e) {
                AlertUtils.mostrarError("Error", "Error al eliminar la inscripción: " + e.getMessage());
            }
        }
    }
    
    /**
     * Maneja el evento de clic en el botón Eliminar Inscripción (desde la tabla de estudiantes del curso).
     */
    @FXML
    private void handleEliminarInscripcionEstudiante() {
        Curso curso = comboCursos.getValue();
        Estudiante estudiante = tablaEstudiantesCurso.getSelectionModel().getSelectedItem();
        
        if (curso == null || estudiante == null) {
            AlertUtils.mostrarAdvertencia("Selección", 
                    "Debe seleccionar un curso y un estudiante de la tabla para eliminar la inscripción.");
            return;
        }
        
        boolean confirmar = AlertUtils.mostrarConfirmacion("Confirmar eliminación", 
                "¿Está seguro de eliminar la inscripción del estudiante " + estudiante.getNombre() + 
                " en el curso " + curso.getNombre() + "?");
        
        if (confirmar) {
            try {
                if (inscripcionDAO.eliminarInscripcion(estudiante.getIdEstudiante(), curso.getIdCurso())) {
                    AlertUtils.mostrarInformacion("Éxito", "Inscripción eliminada correctamente.");
                    
                    // Actualizar las tablas
                    cargarEstudiantesDeCurso(curso.getIdCurso());
                    if (comboEstudiantes.getValue() != null) {
                        cargarCursosDeEstudiante(comboEstudiantes.getValue().getIdEstudiante());
                    }
                    cargarTodasLasInscripciones();
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo eliminar la inscripción.");
                }
            } catch (SQLException e) {
                AlertUtils.mostrarError("Error", "Error al eliminar la inscripción: " + e.getMessage());
            }
        }
    }
}