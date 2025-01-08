package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

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

    public static <T> T openModal(String sceneFxml, Class clase, Stage owner, Consumer<T> dataHandler) {
        try {
            FXMLLoader loader = new FXMLLoader(clase.getResource(sceneFxml));
            Parent root = loader.load();

            // Obtener el controlador asociado
            T controller = loader.getController();

            // Crear la ventana modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(owner);
            modalStage.setScene(new Scene(root));
            modalStage.setResizable(false);

            // Permitir al controlador interactuar con el llamador
            if (dataHandler != null) {
                dataHandler.accept(controller);
            }

            // Mostrar la ventana y esperar hasta que se cierre
            modalStage.showAndWait();

            // Retornar el controlador para obtener datos de vuelta
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            view.lanzarMensajeError("Error", "No se pudo abrir la ventana modal", "Consulte el log para más detalles");
            return null;
        }
    }
}
