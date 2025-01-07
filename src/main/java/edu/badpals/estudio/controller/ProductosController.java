package edu.badpals.estudio.controller;

import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.producto.ProductoService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
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
    private Button btnActualizarCabina,btnCrearCabina,btnEliminarCabina, btnHome, btnCitas, btnCabinas, btnClientes, btnProductos, btnTrabajadores;;

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
    public void initialize() {
        EntityManagerFactoryProvider.initialize("test");

        tblProductos.setOnMouseClicked(event -> {

            Producto ProductoSeleccionado = tblProductos.getSelectionModel().getSelectedItem();

            if (ProductoSeleccionado != null) {

                txtIdProducto.setText(String.valueOf(ProductoSeleccionado.getId()));
                txtNombreProducto.setText(ProductoSeleccionado.getNombre());

            }
        });
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

    public void irCabinas(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/cabinas.fxml",this.getClass());
    }

    public void irHome(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/home.fxml",this.getClass());
    }

    public void irCitas(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/citas.fxml",this.getClass());
    }

    public void irClientes(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/clientes.fxml",this.getClass());
    }

    public void irTrabajadores(ActionEvent event){
        SceneManager.goToView(event,"/edu/badpals/estudio/trabajadores.fxml",this.getClass());
    }


}
