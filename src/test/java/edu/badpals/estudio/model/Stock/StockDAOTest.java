package edu.badpals.estudio.model.Stock;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaDAO;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.producto.Producto;
import edu.badpals.estudio.model.producto.ProductoService;
import edu.badpals.estudio.model.stock.Stock;
import edu.badpals.estudio.model.stock.StockDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockDAOTest {


    private static StockDAO stockDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        stockDAO = new StockDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Optional<Stock> optional_stock1 = stockDAO.findById(1);
        if(optional_stock1.isPresent()){
            Stock stock1 = optional_stock1.get();
            assertNotNull(stock1);
            assertEquals(stock1.getId(),1);
        }
    }

    @Test
    @Order(2)
    public void test_create(){

        ProductoService productoService = new ProductoService();
        Producto producto = productoService.findById(1);

        CabinaDAO CabinaDAO = new CabinaDAO();

        CabinaService cabinaService = new CabinaService();
        Cabina cabina = cabinaService.getCabina(2);

        Stock stock = new Stock(producto, cabina, 10L , LocalDate.now());

        stockDAO.create(stock);

        Stock stockRecuperado = stockDAO.findById(stock.getId()).get();
    }


}