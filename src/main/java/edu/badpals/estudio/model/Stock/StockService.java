package edu.badpals.estudio.model.Stock;

import java.time.LocalDate;
import java.util.Optional;

public class StockService {

    private StockDAO stockDAO;


    public StockService() {
        stockDAO = new StockDAO();
    }

    public Stock getStock(int id){

        Optional<Stock> stock = stockDAO.findById(id);
        return stock.isPresent()? stock.get() : null;
    }

    public void update(Stock stock, int nuevoProducto, int nuevaCabina, Long nuevaCantidadDisponible, LocalDate nuevaFechaCaducidad) {

        Stock updatedProducto = stock;

        if (!stockDAO.findById(stock.getId()).isPresent()) {
            throw new IllegalArgumentException("No se encuentra el stock especificado.");
        }
        if (!stockDAO.findProducto(nuevoProducto).isPresent()) {
            throw new IllegalArgumentException("No se encuentra el producto especificado.");
        }
        if (!stockDAO.findCabina(nuevoProducto).isPresent()) {
            throw new IllegalArgumentException("No se encuentra la cabina especificada.");
        } else {

            updatedProducto.setProducto(stockDAO.findProducto(nuevoProducto).get());
            updatedProducto.setCabina(stockDAO.findCabina(nuevoProducto).get());
            updatedProducto.setCantidadDisponible(nuevaCantidadDisponible);
            updatedProducto.setFechaCaducidad(nuevaFechaCaducidad);
            stockDAO.update(stock);
        }
    }

    public void delete(int id) {
        Optional<Stock> stockOptional = stockDAO.findById(id);
        if (!stockOptional.isPresent()) {
            throw new IllegalArgumentException();
        }else {
            stockDAO.delete(stockOptional.get());
        }
    }


}
