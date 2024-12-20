package edu.badpals.estudio.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class View {

    public void lanzarMensajeAviso(String titulo, String cabecera, String mensaje){
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle(titulo);
        error.setHeaderText(cabecera);
        error.setContentText(mensaje);

        error.showAndWait();
    }

    public void lanzarMensajeError(String titulo, String cabecera, String mensaje){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(titulo);
        error.setHeaderText(cabecera);
        error.setContentText(mensaje);
        error.setHeight(300.0);

        error.showAndWait();
    }

    public Optional<ButtonType> lanzarMensajeConfirmacion(String titulo, String cabecera, String mensaje){
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle(titulo);
        confirmacion.setHeaderText(cabecera);
        confirmacion.setContentText(mensaje);

        ButtonType btnSi = new ButtonType("Sí");
        ButtonType btnNo = new ButtonType("No");
        confirmacion.getButtonTypes().setAll(btnSi, btnNo);


        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        return respuesta;
    }

    public Optional<File> abrirFileChooserExp(Stage stage){
        FileChooser fl = new FileChooser();
        fl.setInitialDirectory(new File(System.getProperty("user.home")));
        fl.setTitle("Guardar archivo");

        fl.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json"));

        File selectedFile = fl.showSaveDialog(stage);
        return selectedFile == null? Optional.empty(): Optional.of(selectedFile);

    }
}
