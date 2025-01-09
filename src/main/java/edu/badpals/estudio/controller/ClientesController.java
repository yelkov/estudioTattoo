package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.cliente.ClienteService;
import edu.badpals.estudio.model.cliente.Parentesco;
import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.trabajador.Trabajador;
import edu.badpals.estudio.model.trabajador.TrabajadorService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class ClientesController {

    @FXML
    private Button btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TableColumn<?, ?> rowDNI;

    @FXML
    private TableColumn<?, ?> rowDireccion;

    @FXML
    private TableColumn<?, ?> rowEmail;

    @FXML
    private TableColumn<?, ?> rowFechaNacimiento;

    @FXML
    private TableColumn<?, ?> rowID;

    @FXML
    private TableColumn<?, ?> rowInstagram;

    @FXML
    private TableColumn<?, ?> rowNombre;

    @FXML
    private TableColumn<?, ?> rowParentesco;

    @FXML
    private TableColumn<?, ?> rowTelefono;

    @FXML
    private TableColumn<Cliente, String> rowTutor;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TextField txtDNICliente;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtFechaNacimientoCliente;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtInstagramCliente;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtParentescoCliente;

    @FXML
    private TextField txtTelefonoCliente;

    @FXML
    private TextField txtTutorCliente;

    @FXML
    void createCliente(ActionEvent event) {

        if ( txtDNICliente.getText().isEmpty() || txtNombreCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || txtDireccionCliente.getText().isEmpty() || txtFechaNacimientoCliente.getText().isEmpty() || txtEmailCliente.getText().isEmpty()) {
            view.lanzarMensajeError("Error", "Por favor, rellene todos los campos", "Por favor, rellene todos los campos");

        } else {

            clientesService.crearCliente(txtDNICliente.getText(), txtNombreCliente.getText(), txtTelefonoCliente.getText(), txtDireccionCliente.getText(), LocalDate.parse(txtFechaNacimientoCliente.getText()), txtInstagramCliente.getText(), txtEmailCliente.getText(), clientesService.getClienteByDni(txtTutorCliente.getText()), Parentesco.valueOf(txtParentescoCliente.getText()), null );

            view.lanzarMensajeConfirmacion("Cliente creado", "El cliente ha sido creado correctamente", "El cliente ha sido creado correctamente");

        }

    }

    @FXML
    void deleteCliente(ActionEvent event) {

        if ( txtIdCliente.getText().isEmpty())  {

            view.lanzarMensajeError("Error", "Por favor, seleccione un cliente", "Por favor, seleccione un cliente");

        } else {

            clientesService.deleteCliente(Integer.parseInt(txtIdCliente.getText()), txtDNICliente.getText(), txtTelefonoCliente.getText());

            view.lanzarMensajeConfirmacion("Cliente eliminado", "El cliente ha sido eliminado correctamente", "El cliente ha sido eliminado correctamente");

        }

    }

    @FXML
    void updateCliente(ActionEvent event) {

        if ( txtIdCliente.getText().isEmpty())  {

            view.lanzarMensajeError("Error", "Por favor, seleccione un cliente", "Por favor, seleccione un cliente");

        } else if ( txtDNICliente.getText().isEmpty() || txtNombreCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || txtDireccionCliente.getText().isEmpty() || txtFechaNacimientoCliente.getText().isEmpty() || txtEmailCliente.getText().isEmpty()) {


            view.lanzarMensajeError("Error", "Por favor, rellene todos los campos", "Por favor, rellene todos los campos");


        } else {

            clientesService.updateCliente(clientesService.getCliente(Integer.parseInt(txtIdCliente.getText())), txtDNICliente.getText(), txtNombreCliente.getText(), txtTelefonoCliente.getText(), txtDireccionCliente.getText(), LocalDate.parse(txtFechaNacimientoCliente.getText()), txtInstagramCliente.getText(), txtEmailCliente.getText(), clientesService.getClienteByDni(txtTutorCliente.getText()), Parentesco.valueOf(txtParentescoCliente.getText()));

            view.lanzarMensajeConfirmacion("Cliente actualizado", "El cliente ha sido actualizado correctamente", "El cliente ha sido actualizado correctamente");

        }

    }

    @FXML
    public void initialize(){

        EntityManagerFactoryProvider.initialize("test");

        loadTabla();

        tblClientes.setOnMouseClicked(event -> {

            Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();

            if (clienteSeleccionado != null) {

                txtIdCliente.clear();
                txtDNICliente.clear();
                txtNombreCliente.clear();
                txtTelefonoCliente.clear();
                txtDireccionCliente.clear();
                txtFechaNacimientoCliente.clear();
                txtInstagramCliente.clear();
                txtEmailCliente.clear();
                txtTutorCliente.clear();
                txtParentescoCliente.clear();

                txtIdCliente.setText(String.valueOf(clienteSeleccionado.getId()));
                txtDNICliente.setText(clienteSeleccionado.getDni());
                txtNombreCliente.setText(clienteSeleccionado.getNombre());
                txtTelefonoCliente.setText(clienteSeleccionado.getTelefono());
                txtDireccionCliente.setText(clienteSeleccionado.getDirección());
                txtFechaNacimientoCliente.setText(String.valueOf(clienteSeleccionado.getFechaNacimiento()));
                txtInstagramCliente.setText(clienteSeleccionado.getInstagram());
                txtEmailCliente.setText(clienteSeleccionado.getEmail());

                if (clienteSeleccionado.getTutor() != null) {
                    txtTutorCliente.setText(clienteSeleccionado.getTutor().getDni());
                    txtParentescoCliente.setText(clienteSeleccionado.getParentesco().toString());

                }

            }

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

    public void irHome(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/home.fxml",this.getClass());
    }

    public void irTrabajadores(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/trabajadores.fxml",this.getClass());
    }

    public ClientesController() {
        this.clientesService = new ClienteService();
        this.view = new View();
    }

    private final ClienteService clientesService;
    private final View view;

    public void loadTabla() {

        List<Cliente> clientesActuales = clientesService.getAllClientes();
        tblClientes.getItems().addAll(clientesActuales);

        rowID.setText("ID");
        rowDNI.setText("DNI");
        rowNombre.setText("Nobre");
        rowTelefono.setText("Teléfono");
        rowDireccion.setText("Dirección");
        rowFechaNacimiento.setText("Fecha de Nacimiento");
        rowInstagram.setText("Instagram");
        rowEmail.setText("Email");
        rowTutor.setText("Tutor");
        rowParentesco.setText("Parentesco");

        tblClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rowID.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        rowDNI.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowNombre.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowTelefono.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowDireccion.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowFechaNacimiento.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowInstagram.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowEmail.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowTutor.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowParentesco.setMaxWidth(1f * Integer.MAX_VALUE * 40);


        rowID.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        rowTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dirección"));
        rowFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        rowInstagram.setCellValueFactory(new PropertyValueFactory<>("instagram"));
        rowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        rowTutor.setCellValueFactory(cellData -> {
            Cliente tutor = cellData.getValue().getTutor();
            if (tutor != null) {
                return new SimpleStringProperty(tutor.getDni());
            }
            return new SimpleStringProperty("");
        });
        rowParentesco.setCellValueFactory(new PropertyValueFactory<>("parentesco"));


    }
}
