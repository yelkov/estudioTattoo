package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.cliente.ClienteService;
import edu.badpals.estudio.model.cliente.Parentesco;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ModalClientesController {
    ClienteService clienteService;

    @FXML
    private TableView<Cliente> tblClientesSeleccionados, tblClientes;

    @FXML
    private TableColumn<Cliente,Integer> idSelec, id;

    @FXML
    private TableColumn<Cliente,String> dni, dniSelec, nombre, nombreSelec, telefono, telefonoSelec, direccion, direccionSelec, fecha, fechaSelec, instagram, instagramSelec, email, emailSelec, tutor, tutorSelec;

    @FXML
    private TableColumn<Cliente, Parentesco> parentesco, parentescoSelec;

    List<Cliente> clientesSeleccionados;

    @FXML
    public void initialize(){
        EntityManagerFactoryProvider.initialize("test");
        clienteService = new ClienteService();

        List<Cliente> allClientes = clienteService.getAllClientes();
        setTblClientes(allClientes);
    }

    public void setClientesSeleccionados(List<Cliente> clientes){
        this.clientesSeleccionados = clientes;
        setTblClientesSeleccionados(clientesSeleccionados);
    }

    public void setTblClientesSeleccionados(List<Cliente> clientes) {
        ObservableList ol = FXCollections.observableArrayList(clientes);
        tblClientesSeleccionados.setItems(ol);

        idSelec.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        dniSelec.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
        nombreSelec.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        telefonoSelec.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        direccionSelec.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dirección"));
        fechaSelec.setCellValueFactory(cellData ->{
            if(cellData.getValue().getFechaNacimiento() != null){
                return new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaNacimiento()));
            }
            return new SimpleStringProperty(" ");
        });
        instagramSelec.setCellValueFactory(new PropertyValueFactory<Cliente, String>("instagram"));
        emailSelec.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
        tutorSelec.setCellValueFactory(cellData -> {
            if(cellData.getValue().getTutor() != null){
                return new SimpleStringProperty(cellData.getValue().getTutor().getNombre());
            }
            return new SimpleStringProperty(" ");
        });
        parentescoSelec.setCellValueFactory(new PropertyValueFactory<Cliente,Parentesco>("parentesco"));

    }

    public void setTblClientes(List<Cliente> clientes) {
        ObservableList ol = FXCollections.observableArrayList(clientes);
        tblClientes.setItems(ol);

        id.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        dni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
        nombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        telefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dirección"));
        fecha.setCellValueFactory(cellData ->{
            if(cellData.getValue().getFechaNacimiento() != null){
                return new SimpleStringProperty(String.valueOf(cellData.getValue().getFechaNacimiento()));
            }
            return new SimpleStringProperty(" ");
        });
        instagram.setCellValueFactory(new PropertyValueFactory<Cliente, String>("instagram"));
        email.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
        tutor.setCellValueFactory(cellData -> {
            if(cellData.getValue().getTutor() != null){
                return new SimpleStringProperty(cellData.getValue().getTutor().getNombre());
            }
            return new SimpleStringProperty(" ");
        });
        parentesco.setCellValueFactory(new PropertyValueFactory<Cliente,Parentesco>("parentesco"));

    }

    public void anadirCliente(){
        Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            clientesSeleccionados.add(cliente);
        }
        setTblClientesSeleccionados(clientesSeleccionados);
    }

    public void eliminarCliente(){
        Cliente cliente = tblClientesSeleccionados.getSelectionModel().getSelectedItem();
        if(cliente != null){
            clientesSeleccionados.remove(cliente);
        }
        setClientesSeleccionados(clientesSeleccionados);
    }

}
