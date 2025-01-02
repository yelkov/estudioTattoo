package edu.badpals.estudio.model.producto;

import java.util.Optional;

public class ProductoService {

    private ProductoDAO productoDAO;

    public ProductoService() {
        productoDAO = new ProductoDAO();
    }

    public Producto getProducto(int id){

        Optional<Producto> producto = productoDAO.findById(id);
        return producto.isPresent()? producto.get() : null;
    }

    public void create(Producto producto) {
        productoDAO.create(producto);
    }

    public void update(Producto producto, String nuevoNombre) {

        Producto updatedProducto = producto;

        if (!productoDAO.findById(producto.getId()).isPresent()) {
            throw new IllegalArgumentException("No se encuentra la aguja especificada.");

        } else {
            producto.setNombre(nuevoNombre);
            productoDAO.update(producto);
        }
    }

    public void delete(int id) {
        Optional<Producto> ProductoOptional = productoDAO.findById(id);
        if (!ProductoOptional.isPresent()) {
            throw new IllegalArgumentException();
        }else {
            productoDAO.delete(ProductoOptional.get());
        }
    }

    public Producto findById(int id) {
        return productoDAO.findById(id).orElse(null);
    }
}
