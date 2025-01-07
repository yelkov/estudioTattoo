package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientesController {

    @FXML
    private Button btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;

    @FXML
    public void initialize(){
        EntityManagerFactoryProvider.initialize("test");
    }

    public void irVista(ActionEvent event, String vistaFxml) {
        EntityManagerFactoryProvider.close();
        SceneManager.goToView(event, vistaFxml, this.getClass());
    }

    public void irHome(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/home.fxml");
    }

    public void irCabinas(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/cabinas.fxml");
    }

    public void irProductos(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/productos.fxml");
    }

    public void irCitas(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/citas.fxml");
    }

    public void irTrabajadores(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/trabajadores.fxml");
    }
}
