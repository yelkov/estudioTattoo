package edu.badpals.estudio.controller;
import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class CabinasController {

    @FXML
    private Button btnActualizarCabina, btnCrearCabina, btnEliminarCabina,btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;

    @FXML
    private Label lblTrabajadores;

    @FXML
    private TableColumn<Cabina, Integer> rowID;

    @FXML
    private TableColumn<Cabina, Boolean> rowPiercing;

    @FXML
    private TableColumn<Cabina, Float> rowSuperficie;

    @FXML
    private TableColumn<Cabina, String> rowUbicacion;

    @FXML
    private TableView<Cabina> tblCabinas;

    @FXML
    private TextField txtIdCabina;


    @FXML
    private TextField txtSuperficieCabina;

    @FXML
    private TextField txtUbicacionCabina;

    @FXML
    void colocarTexto(ActionEvent event) {



    }

    @FXML
    private TextField txtPiercing;


    @FXML
    public void initialize() {
        EntityManagerFactoryProvider.initialize("test");

        tblCabinas.setOnMouseClicked(event -> {

            Cabina cabinaSeleccionada = tblCabinas.getSelectionModel().getSelectedItem();

            if (cabinaSeleccionada != null) {

                txtIdCabina.setText(String.valueOf(cabinaSeleccionada.getId()));
                txtUbicacionCabina.setText(cabinaSeleccionada.getUbicacion());
                txtSuperficieCabina.setText(String.valueOf(cabinaSeleccionada.getSuperficie()));
                txtPiercing.setText(puedeHacerPiercingStr(cabinaSeleccionada.getPuedeHacerPiercing()));


            }
        });
    }



    public CabinasController() {
        this.cabinaService = new CabinaService();
        this.view = new View();
    }


    private final CabinaService cabinaService;
    private final View view;


    // TODO Load tabla tiene demasiadas responsabilidades, pero funciona, hay que dividir entre el "Asignar datos" y el "Rediseñar las celdas" en dos metodos diferentes.


    public void loadTabla() {

        List<Cabina> cabinasActuales = cabinaService.getAllCabinas();
        tblCabinas.getItems().addAll(cabinasActuales);



        rowID.setText("ID");
        rowUbicacion.setText("Ubicacion");
        rowSuperficie.setText("Superficie");
        rowPiercing.setText("Puede hacer piercing");

        tblCabinas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rowID.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        rowUbicacion.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        rowSuperficie.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        rowPiercing.setMaxWidth(1f * Integer.MAX_VALUE * 10);

        rowID.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        rowSuperficie.setCellValueFactory(new PropertyValueFactory<>("superficie"));
        rowPiercing.setCellValueFactory(new PropertyValueFactory<>("puedeHacerPiercing"));

    }

    private String puedeHacerPiercingStr(boolean puedeHacerPiercing) {
        return puedeHacerPiercing ? "Sí" : "No";
    }

    private Boolean puedeHacerPiercingBool(String puedeHacerPiercing) {
        return puedeHacerPiercing.equals("Sí");
    }

    @FXML
    private void crearCabina (ActionEvent event) {

        if (txtUbicacionCabina.getText().isEmpty() || txtSuperficieCabina.getText().isEmpty() || txtPiercing.getText().isEmpty()) {

            view.lanzarMensajeError("Error", "Campos vacíos", "Por favor, rellene todos los campos");

        } else if (cabinaService.getCabinaByUbicacion(txtUbicacionCabina.getText()) != null) {

            view.lanzarMensajeError("Error", "Ubicación en uso", "La ubicación de la cabina ya exiete");

        } else if (txtPiercing.getText().equals("Sí") || txtPiercing.getText().equals("No")) {


            cabinaService.createCabina(txtUbicacionCabina.getText().toUpperCase(), Float.parseFloat(txtSuperficieCabina.getText()), puedeHacerPiercingBool(txtPiercing.getText()));

            view.lanzarMensajeAviso("Cabina creada", "Cabina creada con éxito", "La cabina ha sido creada con éxito");

            tblCabinas.getItems().clear();
            loadTabla();

        } else {

            view.lanzarMensajeError("Error", "Valor incorrecto", "El campo 'Puede hacer piercing' solo puede ser 'Sí' o 'No'");

        }
    }


    @FXML
    private void updateCabina (ActionEvent event) {

        if (txtUbicacionCabina.getText().isEmpty() || txtSuperficieCabina.getText().isEmpty() || txtPiercing.getText().isEmpty()) {

            view.lanzarMensajeError("Error", "Campos vacíos", "Por favor, rellene todos los campos");

        } else if (cabinaService.getCabinaByUbicacion(txtUbicacionCabina.getText()) != null) {

            view.lanzarMensajeError("Error", "Ubicación en uso", "La ubicación de la cabina ya exiete");

        } else if (txtPiercing.getText().equals("Sí") || txtPiercing.getText().equals("No")) {

            Cabina cabina = cabinaService.getCabina(Integer.parseInt(txtIdCabina.getText()));

            cabinaService.updateCabina(cabina, txtUbicacionCabina.getText().toUpperCase(), Float.parseFloat(txtSuperficieCabina.getText()), puedeHacerPiercingBool(txtPiercing.getText()));

            view.lanzarMensajeAviso("Cabina actualizada", "Cabina actualizada con éxito", "La cabina ha sido actualizada con éxito");

            tblCabinas.getItems().clear();
            loadTabla();

        } else {

            view.lanzarMensajeError("Error", "Valor incorrecto", "El campo 'Puede hacer piercing' solo puede ser 'Sí' o 'No'");

        }

    }

    @FXML
    private void deleteCabina (ActionEvent event) {

            if (txtIdCabina.getText().isEmpty()) {

                view.lanzarMensajeError("Error", "No hay cabina seleccionada", "Por favor, seleccione en una cabina para eliminarla");

            } else {

                cabinaService.deleteCabina(Integer.parseInt(txtIdCabina.getText()));

                view.lanzarMensajeAviso("Cabina eliminada", "Cabina eliminada con éxito", "La cabina ha sido eliminada con éxito");

                tblCabinas.getItems().clear();
                loadTabla();

            }
    }

    public void irHome(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/home.fxml",this.getClass());
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