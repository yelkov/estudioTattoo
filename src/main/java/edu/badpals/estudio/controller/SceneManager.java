package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static View view = new View();

    public static void goToView(ActionEvent actionEvent, String sceneFxml, Class clase){
        EntityManagerFactoryProvider.close();
        try {
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(clase.getResource(sceneFxml));
            Scene scene = new Scene(loader.load(),1400,700);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e){
            e.printStackTrace();
            view.lanzarMensajeError("Error", "No se ha podido cambiar de ventana", "Consulte el log para ver el error más detalladamente");
        }
    }
}
