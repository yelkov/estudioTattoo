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

    public void irCabinas(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/cabinas.fxml",this.getClass());
    }

    public void irProductos(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/productos.fxml",this.getClass());
    }

    public void irCitas(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/citas.fxml",this.getClass());
    }

    public void irHome(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/home.fxml",this.getClass());
    }

    public void irTrabajadores(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/trabajadores.fxml",this.getClass());
    }
}
