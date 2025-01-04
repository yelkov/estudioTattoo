package edu.badpals.estudio.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button btnTrabajadores;

    @FXML
    private Label lblTrabajadores;

    @FXML
    public void initialize(){}

    public void colocarTexto(){
        lblTrabajadores.setText("TRABAJADORES");
    }
}
