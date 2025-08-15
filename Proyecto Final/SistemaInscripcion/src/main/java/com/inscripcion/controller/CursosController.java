package com.inscripcion.controller;

import com.inscripcion.dao.CursoDAO;
import com.inscripcion.dao.impl.CursoDAOImpl;
import com.inscripcion.model.Curso;
import com.inscripcion.util.AlertUtils;
import com.inscripcion.util.Validador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de cursos.
 */
public class CursosController implements Initializable {

    @FXML
    private TableView<Curso> tablaCursos;
    
    @FXML
    private TableColumn<Curso, Integer> colId;
    
    @FXML
    private TableColumn<Curso, String> colNombre;
    
    @FXML
    private TableColumn<Curso, Integer> colCreditos;
    
    @FXML
    private TableColumn<Curso, Integer> colCupoMaximo;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextArea txtDescripcion;
    
    @FXML
    private TextField txtCreditos;
    
    @FXML
    private TextField txtCupoMaximo;
    
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnEliminar;
    
    private CursoDAO cursoDAO;
    private ObservableList<Curso> listaCursos;
    private Curso cursoSeleccionado;
    private boolean modoEdicion = false;

    /**
     * Inicializa el controlador.
     * 
     * @param location Ubicación del FXML
     * @param resources Recursos
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cursoDAO = new CursoDAOImpl();
        listaCursos = FXCollections.observableArrayList();
        
        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        colCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
        // Configurar la selección de la tabla
        tablaCursos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cursoSeleccionado = newSelection;
                mostrarDatosCurso(cursoSeleccionado);
                activarModoEdicion(true);
            }
        });
        
        // Configurar el campo de búsqueda
        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                cargarCursos();
            } else {
                buscarCursos(newValue);
            }
        });
        
        // Cargar los cursos iniciales
        cargarCursos();
        limpiarFormulario();
    }
    
    /**
     * Carga todos los cursos en la tabla.
     */
    private void cargarCursos() {
        try {
            listaCursos.clear();
            listaCursos.addAll(cursoDAO.obtenerTodos());
            tablaCursos.setItems(listaCursos);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los cursos: " + e.getMessage());
        }
    }
    
    /**
     * Busca cursos por nombre o créditos.
     * 
     * @param termino Término de búsqueda
     */
    private void buscarCursos(String termino) {
        try {
            listaCursos.clear();
            listaCursos.addAll(cursoDAO.buscarPorNombreOCreditos(termino));
            tablaCursos.setItems(listaCursos);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al buscar cursos: " + e.getMessage());
        }
    }
    
    /**
     * Muestra los datos de un curso en el formulario.
     * 
     * @param curso Curso a mostrar
     */
    private void mostrarDatosCurso(Curso curso) {
        txtNombre.setText(curso.getNombre());
        txtDescripcion.setText(curso.getDescripcion());
        txtCreditos.setText(String.valueOf(curso.getCreditos()));
        txtCupoMaximo.setText(String.valueOf(curso.getCupoMaximo()));
    }
    
    /**
     * Limpia el formulario y desactiva el modo edición.
     */
    private void limpiarFormulario() {
        txtNombre.clear();
        txtDescripcion.clear();
        txtCreditos.clear();
        txtCupoMaximo.clear();
        cursoSeleccionado = null;
        activarModoEdicion(false);
    }
    
    /**
     * Activa o desactiva el modo edición.
     * 
     * @param activo true para activar, false para desactivar
     */
    private void activarModoEdicion(boolean activo) {
        modoEdicion = activo;
        btnEliminar.setDisable(!activo);
    }
    
    /**
     * Valida los datos del formulario.
     * 
     * @return true si los datos son válidos, false en caso contrario
     */
    private boolean validarDatos() {
        StringBuilder errores = new StringBuilder();
        
        if (!Validador.esTextoValido(txtNombre.getText())) {
            errores.append("- El nombre es obligatorio\n");
        }
        
        Integer creditos = null;
        try {
            creditos = Integer.parseInt(txtCreditos.getText());
            if (!Validador.esNumeroPositivo(creditos)) {
                errores.append("- Los créditos deben ser un número positivo\n");
            }
        } catch (NumberFormatException e) {
            errores.append("- Los créditos deben ser un número válido\n");
        }
        
        Integer cupoMaximo = null;
        try {
            cupoMaximo = Integer.parseInt(txtCupoMaximo.getText());
            if (!Validador.esNumeroPositivo(cupoMaximo)) {
                errores.append("- El cupo máximo debe ser un número positivo\n");
            }
        } catch (NumberFormatException e) {
            errores.append("- El cupo máximo debe ser un número válido\n");
        }
        
        if (errores.length() > 0) {
            AlertUtils.mostrarAdvertencia("Datos inválidos", errores.toString());
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtiene los datos del formulario y crea un objeto Curso.
     * 
     * @return Objeto Curso con los datos del formulario
     */
    private Curso obtenerDatosFormulario() {
        Curso curso = new Curso();
        
        if (modoEdicion && cursoSeleccionado != null) {
            curso.setIdCurso(cursoSeleccionado.getIdCurso());
        }
        
        curso.setNombre(txtNombre.getText().trim());
        curso.setDescripcion(txtDescripcion.getText().trim());
        curso.setCreditos(Integer.parseInt(txtCreditos.getText().trim()));
        curso.setCupoMaximo(Integer.parseInt(txtCupoMaximo.getText().trim()));
        
        return curso;
    }
    
    /**
     * Maneja el evento de clic en el botón Nuevo.
     */
    @FXML
    private void handleNuevo() {
        limpiarFormulario();
    }
    
    /**
     * Maneja el evento de clic en el botón Guardar.
     */
    @FXML
    private void handleGuardar() {
        if (!validarDatos()) {
            return;
        }
        
        try {
            Curso curso = obtenerDatosFormulario();
            
            if (modoEdicion) {
                // Actualizar curso existente
                if (cursoDAO.actualizar(curso)) {
                    AlertUtils.mostrarInformacion("Éxito", "Curso actualizado correctamente.");
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo actualizar el curso.");
                }
            } else {
                // Insertar nuevo curso
                int id = cursoDAO.insertar(curso);
                if (id > 0) {
                    curso.setIdCurso(id);
                    AlertUtils.mostrarInformacion("Éxito", "Curso registrado correctamente.");
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo registrar el curso.");
                }
            }
            
            cargarCursos();
            limpiarFormulario();
            
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al guardar el curso: " + e.getMessage());
        }
    }
    
    /**
     * Maneja el evento de clic en el botón Cancelar.
     */
    @FXML
    private void handleCancelar() {
        limpiarFormulario();
    }
    
    /**
     * Maneja el evento de clic en el botón Eliminar.
     */
    @FXML
    private void handleEliminar() {
        if (cursoSeleccionado == null) {
            AlertUtils.mostrarAdvertencia("Selección", "Debe seleccionar un curso para eliminar.");
            return;
        }
        
        try {
            // Verificar si hay estudiantes inscritos en el curso
            int estudiantesInscritos = cursoDAO.obtenerNumeroEstudiantesInscritos(cursoSeleccionado.getIdCurso());
            if (estudiantesInscritos > 0) {
                boolean confirmarEliminacion = AlertUtils.mostrarConfirmacion("Advertencia", 
                        "El curso tiene " + estudiantesInscritos + " estudiante(s) inscrito(s). " +
                        "Si elimina el curso, también se eliminarán todas las inscripciones asociadas. " +
                        "¿Desea continuar?");
                
                if (!confirmarEliminacion) {
                    return;
                }
            } else {
                boolean confirmar = AlertUtils.mostrarConfirmacion("Confirmar eliminación", 
                        "¿Está seguro de eliminar el curso " + cursoSeleccionado.getNombre() + "?");
                
                if (!confirmar) {
                    return;
                }
            }
            
            if (cursoDAO.eliminar(cursoSeleccionado.getIdCurso())) {
                AlertUtils.mostrarInformacion("Éxito", "Curso eliminado correctamente.");
                cargarCursos();
                limpiarFormulario();
            } else {
                AlertUtils.mostrarError("Error", "No se pudo eliminar el curso.");
            }
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al eliminar el curso: " + e.getMessage());
        }
    }
}