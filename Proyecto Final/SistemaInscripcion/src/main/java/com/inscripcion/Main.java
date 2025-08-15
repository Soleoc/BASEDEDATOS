package com.inscripcion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 */
public class Main extends Application {

    /**
     * Método principal que inicia la aplicación JavaFX.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método que se ejecuta al iniciar la aplicación JavaFX.
     * Carga la vista principal y configura la ventana.
     * 
     * @param primaryStage Escenario principal de la aplicación
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("Sistema de Inscripción de Estudiantes");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
}