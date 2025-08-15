package com.inscripcion.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista principal de la aplicación.
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private Tab tabEstudiantes;
    
    @FXML
    private Tab tabCursos;
    
    @FXML
    private Tab tabInscripciones;

    /**
     * Inicializa el controlador.
     * 
     * @param location Ubicación del FXML
     * @param resources Recursos
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Cargar las vistas en cada pestaña
            cargarVistaEstudiantes();
            cargarVistaCursos();
            cargarVistaInscripciones();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carga la vista de estudiantes en la pestaña correspondiente.
     * 
     * @throws IOException Si ocurre un error al cargar la vista
     */
    private void cargarVistaEstudiantes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EstudiantesView.fxml"));
        Parent vista = loader.load();
        tabEstudiantes.setContent(vista);
    }
    
    /**
     * Carga la vista de cursos en la pestaña correspondiente.
     * 
     * @throws IOException Si ocurre un error al cargar la vista
     */
    private void cargarVistaCursos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CursosView.fxml"));
        Parent vista = loader.load();
        tabCursos.setContent(vista);
    }
    
    /**
     * Carga la vista de inscripciones en la pestaña correspondiente.
     * 
     * @throws IOException Si ocurre un error al cargar la vista
     */
    private void cargarVistaInscripciones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InscripcionesView.fxml"));
        Parent vista = loader.load();
        tabInscripciones.setContent(vista);
    }
}