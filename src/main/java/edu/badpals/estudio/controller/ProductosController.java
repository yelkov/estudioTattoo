package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.producto.ProductoService;
import edu.badpals.estudio.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProductosController {

    @FXML
    private Button btnActualizarCabina;

    @FXML
    private Button btnCrearCabina;

    @FXML
    private Button btnEliminarCabina;

    @FXML
    private Button btnGotoCabinas;

    @FXML
    private Label lblTrabajadores;

    @FXML
    private TableColumn<?, ?> rowID;

    @FXML
    private TableColumn<?, ?> rowNombre;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    void colocarTexto(ActionEvent event) {

    }

    @FXML
    void crearProducto(ActionEvent event) {

        if (txtNombreProducto.getText().isEmpty()) {
            view.lanzarMensajeAviso("Campo vacío", "El campo nombre no puede estar vacío", "Por favor, introduzca un nombre");
        } else {

            Producto producto = new Producto();
            producto.setNombre(txtNombreProducto.getText().toLowerCase());
            productoService.create(producto);

            view.lanzarMensajeAviso("Producto creado", "Producto creado con éxito", "El Producto ha sido creado con éxito");

            tblProductos.getItems().clear();
            loadTabla();
        }

    }

    @FXML
    void deleteProducto(ActionEvent event) {

        if( txtIdProducto.getText().isEmpty() ) {
            view.lanzarMensajeError("Sin producto seleccionado", "Seleccionar un producto", "Por favor, seleccione un producto a borrar");

        } else {

            int id = Integer.parseInt(txtIdProducto.getText());
            productoService.delete(id);

            view.lanzarMensajeAviso("Producto eliminado", "Producto eliminado con éxito", "El Producto ha sido eliminado con éxito");

            tblProductos.getItems().clear();
            loadTabla();
        }

    }

    @FXML
    void gotoCabinas(ActionEvent event) {

        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/badpals/estudio/test.fxml"));
            Parent root = fxmlLoader.load();

            CabinasController cabinasController = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Cabinas");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.setOnHiding(e -> cabinasController.loadTabla());

        } catch (IOException e) {
            view.lanzarMensajeError("No se pudo ir a la ventana de Cabinas", "Error", "No se pudo ir a la ventana de Cabinas");
        }

    }

    @FXML
    void updateProducto(ActionEvent event) {

        if (txtNombreProducto.getText().isEmpty() ) {

            view.lanzarMensajeAviso("Campo vacío", "El campo nombre no puede estar vacío", "Por favor, introduzca un nombre");

        } else if(productoService.findbyName(txtNombreProducto.getText()) != null) {

            view.lanzarMensajeAviso("Producto ya existe", "Producto ya existe", "El Producto ya existe");

        }else {

            Producto producto = new Producto();
            producto.setId(Integer.parseInt(txtIdProducto.getText()));
            producto.setNombre(txtNombreProducto.getText());

            productoService.update(producto, txtNombreProducto.getText().toLowerCase());

            view.lanzarMensajeAviso("Producto actualizado", "Producto actualizado con éxito", "El Producto ha sido actualizado con éxito");

            tblProductos.getItems().clear();
            loadTabla();
        }

    }

    @FXML
    public void initialize() {

        tblProductos.setOnMouseClicked(event -> {

            Producto ProductoSeleccionado = tblProductos.getSelectionModel().getSelectedItem();

            if (ProductoSeleccionado != null) {

                txtIdProducto.setText(String.valueOf(ProductoSeleccionado.getId()));
                txtNombreProducto.setText(ProductoSeleccionado.getNombre());

            }
        });
    }

    public ProductosController() {
        this.productoService = new ProductoService();
        this.view = new View();
    }

    private final ProductoService productoService;
    private final View view;

    // TODO Load tabla tiene demasiadas responsabilidades, pero funciona, hay que dividir entre el "Asignar datos" y el "Rediseñar las celdas" en dos metodos diferentes.

    public void loadTabla() {

        List<Producto> productosActuales = productoService.getAllProductos();
        tblProductos.getItems().addAll(productosActuales);

        rowID.setText("ID");
        rowNombre.setText("Nobre");

        tblProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rowID.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        rowNombre.setMaxWidth(1f * Integer.MAX_VALUE * 40);


        rowID.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    }


}
