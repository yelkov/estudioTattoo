package edu.badpals.estudio.model.producto;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoDAOTest {

    private static ProductoDAO productoDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        productoDAO = new ProductoDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){

        Optional<Producto> optional_producto1 = productoDAO.findById(1);
        if(optional_producto1.isPresent()){
            Producto producto1 = optional_producto1.get();
            assertNotNull(producto1);
            assertEquals(producto1.getId(),1);
            assertEquals(producto1.getNombre(),"vaselina");
        }
    }

    @Test
    @Order(2)
    public void test_findByName(){

        Optional<Producto> optional_producto1 = productoDAO.findByName("vaselina");
        if(optional_producto1.isPresent()){
            Producto producto1 = optional_producto1.get();
            assertNotNull(producto1);
            assertEquals(producto1.getId(),1);
            assertEquals(producto1.getNombre(),"vaselina");
        }
    }


    @Test
    @Order(3)
    public void test_create(){

        Producto producto = new Producto();
        producto.setNombre("placeholder");
        productoDAO.create(producto);

        Producto productoRecuperado = productoDAO.findByName("placeholder").get();
        assertNotNull(productoRecuperado);

    }

    @Test
    @Order(4)
    public void test_delete(){

        Producto producto = productoDAO.findByName("Placeholder").get();
        productoDAO.delete(producto);

        Optional<Producto> productoRecuperado = productoDAO.findByName("Placeholder");
        assertFalse(productoRecuperado.isPresent());
    }




}
