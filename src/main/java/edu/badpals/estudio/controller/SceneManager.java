package edu.badpals.estudio.controller;

import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static View view = new View();

    private static Scene loadFxml(String sceneFxml, Class clase) throws IOException {
        FXMLLoader loader = new FXMLLoader(clase.getResource(sceneFxml));
        Scene scene = new Scene(loader.load(),1200,600);
        return scene;
    }

    public static void goToView(ActionEvent actionEvent, String sceneFxml, Class clase){
        try {
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene newScene = loadFxml(sceneFxml, clase);
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e){
            view.lanzarMensajeError("Error", "No se ha podido cambiar de ventana", "Consulte el log para ver el error más detalladamente");
        }
    }
}
