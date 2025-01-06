package edu.badpals.estudio;

import edu.badpals.estudio.controller.CabinasController;
import edu.badpals.estudio.controller.ProductosController;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("productos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Gestor de Tattoo Studio");
        stage.setScene(scene);
        stage.show();

        EntityManagerFactoryProvider.initialize("test");


        ProductosController productosController = fxmlLoader.getController();
        productosController.loadTabla();


    }
}
