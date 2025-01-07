package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cabina.HuecoService;
import edu.badpals.estudio.model.cita.Cita;
import edu.badpals.estudio.model.cita.CitaService;
import edu.badpals.estudio.model.cita.Estado;
import edu.badpals.estudio.model.cita.Tipo;
import edu.badpals.estudio.model.cliente.ClienteService;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.AnilladorService;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.trabajador.TatuadorService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CitasController {

    CitaService citaService;
    CabinaService cabinaService;
    AnilladorService anilladorService;
    TatuadorService tatuadorService;
    HuecoService huecoService;
    ClienteService clienteService;

    @FXML
    private Button btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;

    @FXML
    private TextField txtDescripcion, txtSenal, txtPrecio, txtFechaDesde, txtFechaHasta, txtHoraDesde;

    @FXML
    private ComboBox cmbEstado, cmbTipo, cmbCabina, cmbAnillador, cmbTatuador,cmbEstadoFiltro, cmbTipoFiltro, cmbCabinaFiltro, cmbAnilladorFiltro, cmbTatuadorFiltro ;

    @FXML
    private TableView<Cita> tblCitas;

    @FXML
    private TableColumn<Cita, String> descripcion, anillador, tatuador, cabina, duracion, dia, inicio;

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
        cabinaService = new CabinaService();
        anilladorService = new AnilladorService();
        tatuadorService = new TatuadorService();
        huecoService = new HuecoService();
        clienteService = new ClienteService();

        setCmbEstado();
        setCmbEstadoFiltro();
        setCmbTipo();
        setCmbTipoFiltro();
        setCmbCabina();
        setCmbCabinaFiltro();
        setAnilladores();
        setTatuadores();

        restringirAnumeros(txtPrecio);
        restringirAnumeros(txtSenal);

        List<Cita> citas = citaService.getAllCitas();
        cargarTablaCitas(citas);
    }

    public void setCmbEstado(){
        cmbEstado.setItems(FXCollections.observableArrayList(Estado.values()));
        cmbEstado.setValue(Estado.values()[0]);
    }

    public void setCmbTipo(){
        cmbTipo.setItems(FXCollections.observableArrayList(Tipo.values()));
        cmbTipo.setValue(Tipo.values()[0]);
    }

    public void setCmbEstadoFiltro(){
        ObservableList ol = FXCollections.observableArrayList(Estado.values());
        ol.add(0,"---");
        cmbEstadoFiltro.setItems(ol);
        cmbEstadoFiltro.setValue("---");
    }

    public void setCmbTipoFiltro(){
        ObservableList ol = FXCollections.observableArrayList(Tipo.values());
        ol.add(0,"---");
        cmbTipoFiltro.setItems(ol);
        cmbTipoFiltro.setValue("---");
    }

    public void setCmbCabina(){
        List<Cabina> cabinas = cabinaService.getAllCabinas();
        ObservableList<String> ol = FXCollections.observableArrayList();
        for (Cabina cabina : cabinas) {
            ol.add(cabina.getUbicacion());
        }
        cmbCabina.setItems(ol);
        cmbCabina.setValue(ol.get(0));
    }

    public void setCmbCabinaFiltro(){
        List<Cabina> cabinas = cabinaService.getAllCabinas();
        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.add(0,"---");
        for (Cabina cabina : cabinas) {
            ol.add(cabina.getUbicacion());
        }
        cmbCabinaFiltro.setItems(ol);
        cmbCabinaFiltro.setValue(ol.get(0));
    }

    public void setAnilladores(){
        List<Anillador> anilladores = anilladorService.getAllAnilladores();
        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.add(0,"---");
        for (Anillador anillador : anilladores) {
            String opcion = anillador.getId().toString() + ". " + anillador.getNombre();
            ol.add(opcion);
        }
        cmbAnillador.setItems(ol);
        cmbAnillador.setValue(ol.get(0));
        cmbAnilladorFiltro.setItems(ol);
        cmbAnilladorFiltro.setValue(ol.get(0));
    }

    public void setTatuadores(){
        List<Tatuador> tatuadores = tatuadorService.getAllTatuadores();
        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.add(0,"---");
        for (Tatuador tatuador : tatuadores) {
            String opcion = tatuador.getId().toString() + ". " + tatuador.getNombre();
            ol.add(opcion);
        }
        cmbTatuador.setItems(ol);
        cmbTatuador.setValue(ol.get(0));
        cmbTatuadorFiltro.setItems(ol);
        cmbTatuadorFiltro.setValue(ol.get(0));
    }

    public static void restringirAnumeros(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            }
        });
    }

    public void cargarTablaCitas(List<Cita> citas){
        ObservableList ol = FXCollections.observableArrayList(citas);
        tblCitas.setItems(ol);

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
        dia.setCellValueFactory(cellData ->{
            if (cellData.getValue().getHuecos() != null) {
                LocalDate diaCita = cellData.getValue().getHuecos().get(0).getDia();
                return new SimpleStringProperty(diaCita.toString());
            }
            return new SimpleStringProperty(" ");
        });
        inicio.setCellValueFactory(cellData ->{
            if(cellData.getValue().getHuecos() != null){
                LocalTime horaInicio = null;
                for (Hueco hueco : cellData.getValue().getHuecos()) {
                    if (horaInicio == null){
                        horaInicio = hueco.getHora();
                    }else if(horaInicio.isAfter(hueco.getHora())){
                        horaInicio = hueco.getHora();
                    }
                }
                return new SimpleStringProperty(horaInicio.toString());
            }
            return new SimpleStringProperty(" ");
        });
        duracion.setCellValueFactory(cellData -> {
            if(cellData.getValue().getHuecos() != null){
                String horas = String.valueOf(cellData.getValue().getHuecos().size());
                return new SimpleStringProperty(horas + " horas");
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

    public void irHome(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/home.fxml",this.getClass());
    }

    public void irClientes(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/productos.fxml",this.getClass());
    }

    public void irTrabajadores(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/trabajadores.fxml",this.getClass());
    }
}
