package edu.badpals.estudio;

import edu.badpals.estudio.controller.Controller;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Gestor de Tattoo Studio");
        stage.setScene(scene);
        stage.show();

        EntityManagerFactoryProvider.initialize("test");


        Controller controller = fxmlLoader.getController();
        controller.loadTabla();


    }
}
