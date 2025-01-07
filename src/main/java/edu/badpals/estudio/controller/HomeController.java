package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cita.Cita;
import edu.badpals.estudio.model.cita.CitaService;
import edu.badpals.estudio.model.cita.Estado;
import edu.badpals.estudio.model.cita.Tipo;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import java.util.List;

public class HomeController {
    private View view;
    private CitaService citaService;

    @FXML
    private Button btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;

    @FXML
    private TableView<Cita> tblProxCitas;

    @FXML
    private TableColumn<Cita, String> descripcion, anillador, tatuador, cabina;

    @FXML
    private TableColumn<Cita, Tipo> tipo;

    @FXML
    private TableColumn<Cita, Integer> id;

    @FXML
    private TableColumn<Cita, Float> precio, señal;

    @FXML
    private TableColumn<Cita, Estado> estado;

    @FXML
    public void initialize(){
        EntityManagerFactoryProvider.initialize("test");
        citaService = new CitaService();
        view = new View();

        cargarTablaCitas();
    }

    public void cargarTablaCitas(){
        List<Cita> citas = citaService.getAllCitas();
        ObservableList ol = FXCollections.observableArrayList(citas);
        tblProxCitas.setItems(ol);

        id.setCellValueFactory(new PropertyValueFactory<Cita,Integer>("id"));
        tipo.setCellValueFactory(new PropertyValueFactory<Cita,Tipo>("tipo"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Cita,String>("descripcion"));
        precio.setCellValueFactory(new PropertyValueFactory<Cita,Float>("precio"));
        señal.setCellValueFactory(new PropertyValueFactory<Cita,Float>("señal"));
        estado.setCellValueFactory(new PropertyValueFactory<Cita,Estado>("estado"));
        anillador.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAnillador() != null) {
                String nombre = cellData.getValue().getAnillador().getNombre();
                if (nombre != null) {
                    return new SimpleStringProperty(nombre); // Nombre válido
                }
            }
            return new SimpleStringProperty(" ");
        });
        tatuador.setCellValueFactory( cellData ->{
            if (cellData.getValue().getTatuador() != null) {
                String nombre = cellData.getValue().getTatuador().getNombre();
                if (nombre != null) {
                    return new SimpleStringProperty(nombre); // Nombre válido
                }
            }
            return new SimpleStringProperty(" ");
        });
        cabina.setCellValueFactory(cellData ->{
            if (cellData.getValue().getCabina() != null) {
                String nombre = cellData.getValue().getCabina().getUbicacion();
                if (nombre != null) {
                    return new SimpleStringProperty(nombre); // Nombre válido
                }
            }
            return new SimpleStringProperty(" ");
        });

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

    public void irClientes(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/productos.fxml",this.getClass());
    }

    public void irTrabajadores(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/trabajadores.fxml",this.getClass());
    }
}
