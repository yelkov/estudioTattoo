package edu.badpals.estudio.controller;
import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.producto.ProductoService;
import edu.badpals.estudio.model.trabajador.Trabajador;
import edu.badpals.estudio.model.trabajador.TrabajadorService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TrabajadoresController {

    @FXML
    private Button btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;


    @FXML
    private Button btnActualizarTrabajador;

    @FXML
    private Button btnAñadirTrabajador;

    @FXML
    private TableColumn<?, ?> rowEmail;

    @FXML
    private TableColumn<?, ?> rowFechaAlta;

    @FXML
    private TableColumn<?, ?> rowFechaNacimiento;

    @FXML
    private TableColumn<?, ?> rowID;

    @FXML
    private TableColumn<?, ?> rowNIF;

    @FXML
    private TableColumn<?, ?> rowNSS;

    @FXML
    private TableColumn<?, ?> rowNombre;

    @FXML
    private TableColumn<?, ?> rowSalario;

    @FXML
    private TableView<Trabajador> tblTrabajador;

    @FXML
    private TextField txtEmailTrabajador;

    @FXML
    private TextField txtFechaAltaTrabajador;

    @FXML
    private TextField txtFechaNacimientoTrabajador;

    @FXML
    private TextField txtIDTrabajador;

    @FXML
    private TextField txtNIFTrabajador;

    @FXML
    private TextField txtNSSTrabajador;

    @FXML
    private TextField txtNombreTrabajador;

    @FXML
    private TextField txtSalarioTrabajador;


    @FXML
    public void initialize(){

        EntityManagerFactoryProvider.initialize("test");

        loadTabla();


        tblTrabajador.setOnMouseClicked(event -> {

            Trabajador trabajadorSeleccionado = tblTrabajador.getSelectionModel().getSelectedItem();

            if (trabajadorSeleccionado != null) {

                txtIDTrabajador.setText(String.valueOf(trabajadorSeleccionado.getId()));
                txtNIFTrabajador.setText(String.valueOf(trabajadorSeleccionado.getNif()));
                txtNombreTrabajador.setText(trabajadorSeleccionado.getNombre());
                txtNSSTrabajador.setText(String.valueOf(trabajadorSeleccionado.getNss()));
                txtFechaNacimientoTrabajador.setText(String.valueOf(trabajadorSeleccionado.getFechaNacimiento()));
                txtFechaAltaTrabajador.setText(String.valueOf(trabajadorSeleccionado.getFechaAlta()));
                txtSalarioTrabajador.setText(String.valueOf(trabajadorSeleccionado.getSalario()));
                txtEmailTrabajador.setText(trabajadorSeleccionado.getEmail());

            }
        });
    }

    public TrabajadoresController() {
        this.trabajadorService = new TrabajadorService();
        this.view = new View();
    }

    private final TrabajadorService trabajadorService;
    private final View view;

    public void loadTabla() {

        List<Trabajador> trabajadoresActuales = trabajadorService.getAllTrabajadores();
        tblTrabajador.getItems().addAll(trabajadoresActuales);

        rowID.setText("ID");
        rowNIF.setText("NIF");
        rowNombre.setText("Nombre");
        rowNSS.setText("NSS");
        rowFechaNacimiento.setText("Fecha de Nacimiento");
        rowFechaAlta.setText("Fecha de Alta");
        rowSalario.setText("Salario");
        rowEmail.setText("Email");


        tblTrabajador.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rowID.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        rowNombre.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowNIF.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowNSS.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowFechaNacimiento.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowFechaAlta.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowSalario.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowEmail.setMaxWidth(1f * Integer.MAX_VALUE * 40);



        rowID.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        rowNIF.setCellValueFactory(new PropertyValueFactory<>("nif"));
        rowNSS.setCellValueFactory(new PropertyValueFactory<>("nss"));
        rowFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        rowFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        rowSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        rowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


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

    public void irClientes(ActionEvent event){
        irVista(event,"/edu/badpals/estudio/clientes.fxml");
    }


}
