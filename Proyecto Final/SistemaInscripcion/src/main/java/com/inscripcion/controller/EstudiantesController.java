package com.inscripcion.controller;

import com.inscripcion.dao.EstudianteDAO;
import com.inscripcion.dao.impl.EstudianteDAOImpl;
import com.inscripcion.model.Estudiante;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de estudiantes.
 */
public class EstudiantesController implements Initializable {

    @FXML
    private TableView<Estudiante> tablaEstudiantes;
    
    @FXML
    private TableColumn<Estudiante, Integer> colId;
    
    @FXML
    private TableColumn<Estudiante, String> colNombre;
    
    @FXML
    private TableColumn<Estudiante, String> colCorreo;
    
    @FXML
    private TableColumn<Estudiante, String> colTelefono;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtCorreo;
    
    @FXML
    private TextField txtTelefono;
    
    @FXML
    private DatePicker dateFechaNacimiento;
    
    @FXML
    private TextArea txtDireccion;
    
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnEliminar;
    
    private EstudianteDAO estudianteDAO;
    private ObservableList<Estudiante> listaEstudiantes;
    private Estudiante estudianteSeleccionado;
    private boolean modoEdicion = false;

    /**
     * Inicializa el controlador.
     * 
     * @param location Ubicación del FXML
     * @param resources Recursos
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        estudianteDAO = new EstudianteDAOImpl();
        listaEstudiantes = FXCollections.observableArrayList();
        
        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        
        // Configurar la selección de la tabla
        tablaEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                estudianteSeleccionado = newSelection;
                mostrarDatosEstudiante(estudianteSeleccionado);
                activarModoEdicion(true);
            }
        });
        
        // Configurar el campo de búsqueda
        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                cargarEstudiantes();
            } else {
                buscarEstudiantes(newValue);
            }
        });
        
        // Cargar los estudiantes iniciales
        cargarEstudiantes();
        limpiarFormulario();
    }
    
    /**
     * Carga todos los estudiantes en la tabla.
     */
    private void cargarEstudiantes() {
        try {
            listaEstudiantes.clear();
            listaEstudiantes.addAll(estudianteDAO.obtenerTodos());
            tablaEstudiantes.setItems(listaEstudiantes);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al cargar los estudiantes: " + e.getMessage());
        }
    }
    
    /**
     * Busca estudiantes por nombre o correo.
     * 
     * @param termino Término de búsqueda
     */
    private void buscarEstudiantes(String termino) {
        try {
            listaEstudiantes.clear();
            listaEstudiantes.addAll(estudianteDAO.buscarPorNombreOCorreo(termino));
            tablaEstudiantes.setItems(listaEstudiantes);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al buscar estudiantes: " + e.getMessage());
        }
    }
    
    /**
     * Muestra los datos de un estudiante en el formulario.
     * 
     * @param estudiante Estudiante a mostrar
     */
    private void mostrarDatosEstudiante(Estudiante estudiante) {
        txtNombre.setText(estudiante.getNombre());
        txtCorreo.setText(estudiante.getCorreo());
        txtTelefono.setText(estudiante.getTelefono());
        dateFechaNacimiento.setValue(estudiante.getFechaNacimiento());
        txtDireccion.setText(estudiante.getDireccion());
    }
    
    /**
     * Limpia el formulario y desactiva el modo edición.
     */
    private void limpiarFormulario() {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        dateFechaNacimiento.setValue(null);
        txtDireccion.clear();
        estudianteSeleccionado = null;
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
        
        if (!Validador.esTextoValido(txtCorreo.getText())) {
            errores.append("- El correo es obligatorio\n");
        } else if (!Validador.esCorreoValido(txtCorreo.getText())) {
            errores.append("- El formato del correo no es válido\n");
        }
        
        if (txtTelefono.getText() != null && !txtTelefono.getText().isEmpty() && 
            !Validador.esTelefonoValido(txtTelefono.getText())) {
            errores.append("- El formato del teléfono no es válido\n");
        }
        
        if (errores.length() > 0) {
            AlertUtils.mostrarAdvertencia("Datos inválidos", errores.toString());
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtiene los datos del formulario y crea un objeto Estudiante.
     * 
     * @return Objeto Estudiante con los datos del formulario
     */
    private Estudiante obtenerDatosFormulario() {
        Estudiante estudiante = new Estudiante();
        
        if (modoEdicion && estudianteSeleccionado != null) {
            estudiante.setIdEstudiante(estudianteSeleccionado.getIdEstudiante());
        }
        
        estudiante.setNombre(txtNombre.getText().trim());
        estudiante.setCorreo(txtCorreo.getText().trim());
        estudiante.setTelefono(txtTelefono.getText().trim());
        estudiante.setFechaNacimiento(dateFechaNacimiento.getValue());
        estudiante.setDireccion(txtDireccion.getText().trim());
        
        return estudiante;
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
            Estudiante estudiante = obtenerDatosFormulario();
            Integer idEstudiante = modoEdicion ? estudiante.getIdEstudiante() : null;
            
            // Verificar si el correo ya existe
            if (estudianteDAO.existeCorreo(estudiante.getCorreo(), idEstudiante)) {
                AlertUtils.mostrarAdvertencia("Correo duplicado", "El correo ya está registrado para otro estudiante.");
                return;
            }
            
            if (modoEdicion) {
                // Actualizar estudiante existente
                if (estudianteDAO.actualizar(estudiante)) {
                    AlertUtils.mostrarInformacion("Éxito", "Estudiante actualizado correctamente.");
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo actualizar el estudiante.");
                }
            } else {
                // Insertar nuevo estudiante
                int id = estudianteDAO.insertar(estudiante);
                if (id > 0) {
                    estudiante.setIdEstudiante(id);
                    AlertUtils.mostrarInformacion("Éxito", "Estudiante registrado correctamente.");
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo registrar el estudiante.");
                }
            }
            
            cargarEstudiantes();
            limpiarFormulario();
            
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error", "Error al guardar el estudiante: " + e.getMessage());
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
        if (estudianteSeleccionado == null) {
            AlertUtils.mostrarAdvertencia("Selección", "Debe seleccionar un estudiante para eliminar.");
            return;
        }
        
        boolean confirmar = AlertUtils.mostrarConfirmacion("Confirmar eliminación", 
                "¿Está seguro de eliminar al estudiante " + estudianteSeleccionado.getNombre() + "?");
        
        if (confirmar) {
            try {
                if (estudianteDAO.eliminar(estudianteSeleccionado.getIdEstudiante())) {
                    AlertUtils.mostrarInformacion("Éxito", "Estudiante eliminado correctamente.");
                    cargarEstudiantes();
                    limpiarFormulario();
                } else {
                    AlertUtils.mostrarError("Error", "No se pudo eliminar el estudiante.");
                }
            } catch (SQLException e) {
                AlertUtils.mostrarError("Error", "Error al eliminar el estudiante: " + e.getMessage());
            }
        }
    }
}