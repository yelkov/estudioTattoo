package edu.badpals.estudio.controller;
import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class Controller {

    @FXML
    private Button btnActualizarCabina;

    @FXML
    private Button btnCrearCabina;

    @FXML
    private Button btnEliminarCabina;

    @FXML
    private Button btnTrabajadores;

    @FXML
    private Label lblTrabajadores;

    @FXML
    private TableColumn<?, ?> rowID;

    @FXML
    private TableColumn<?, ?> rowPiercing;

    @FXML
    private TableColumn<?, ?> rowSuperficie;

    @FXML
    private TableColumn<?, ?> rowUbicacion;

    @FXML
    private TableView<Cabina> tblCabinas;

    @FXML
    private TextField txtIdCabina;

    @FXML
    private TextField txtPiercingCabina;

    @FXML
    private TextField txtSuperficieCabina;

    @FXML
    private TextField txtUbicacionCabina;

    @FXML
    void colocarTexto(ActionEvent event) {

    }

    private final CabinaService cabinaService;

    public Controller() {
        this.cabinaService = new CabinaService();
    }

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


}