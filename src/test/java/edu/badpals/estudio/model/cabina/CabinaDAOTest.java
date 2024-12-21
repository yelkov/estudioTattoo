package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CabinaDAOTest {
    private static CabinaDAO cabinaDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        cabinaDAO = new CabinaDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Optional<Cabina> optional_cabina1 = cabinaDAO.findById(1);
        if(optional_cabina1.isPresent()){
            Cabina cabina1 = optional_cabina1.get();
            assertNotNull(cabina1);
            assertEquals(cabina1.getId(),1);
            assertEquals(cabina1.getUbicacion(),"FONDO PIERCING");
            assertEquals(cabina1.getSuperficie(),12.5f);
            assertTrue(cabina1.getPuedeHacerPiercing());
        }
    }

    @Test
    @Order(2)
    public void test_find_empty(){
        Optional<Cabina> optional_cabinaEmpty = cabinaDAO.findById(10);
        assertTrue(optional_cabinaEmpty.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findAll(){
        List<Cabina> cabinas = cabinaDAO.findAll();
        assertNotNull(cabinas);
        assertTrue(cabinas.size() > 0);
        assertEquals(cabinas.get(0).getId(),1);
        assertEquals(4,cabinas.size());
    }

    @Test
    @Order(4)
    public void test_findByUbicacion(){
        Optional<Cabina> optionalCabina = cabinaDAO.findByUbicacion("FONDO PIERCING");
        if(optionalCabina.isPresent()){
            Cabina cabina1 = optionalCabina.get();
            assertNotNull(cabina1);
            assertEquals(cabina1.getId(),1);
            assertEquals(cabina1.getUbicacion(), "FONDO PIERCING");
            assertEquals(cabina1.getSuperficie(),12.5f);
            assertTrue(cabina1.getPuedeHacerPiercing());
        }
    }

    @Test
    @Order(5)
    public void test_findByUbicacionLower(){
        Optional<Cabina> optionalCabina = cabinaDAO.findByUbicacion("Fondo piercing");
        if(optionalCabina.isPresent()){
            Cabina cabina1 = optionalCabina.get();
            assertNotNull(cabina1);
            assertEquals(cabina1.getId(),1);
            assertEquals(cabina1.getUbicacion(), "FONDO PIERCING");
            assertEquals(cabina1.getSuperficie(),12.5f);
            assertTrue(cabina1.getPuedeHacerPiercing());
        }
    }

    @Test
    @Order(6)
    public void test_findByUbicacion_empty(){
        Optional<Cabina> optional_cabinaEmpty = cabinaDAO.findByUbicacion("NO EXISTE");
        assertTrue(optional_cabinaEmpty.isEmpty());
    }

    @Test
    @Order(7)
    public void test_create(){
        Cabina cabina5 = new Cabina("FONDO NUEVA",10.5f,true);
        cabinaDAO.create(cabina5);

        Optional<Cabina> cabinaRecuperada = cabinaDAO.findByUbicacion("FONDO NUEVA");
        if(cabinaRecuperada.isPresent()){
            Cabina cabina5recuperada = cabinaRecuperada.get();
            assertNotNull(cabina5recuperada);
            assertEquals(cabina5recuperada.getUbicacion(),"FONDO NUEVA");
            assertEquals(cabina5recuperada.getSuperficie(),10.5f);
            assertTrue(cabina5recuperada.getPuedeHacerPiercing());
        }

    }

    @Test
    @Order(8)
    public void test_delete(){
        Cabina cabinaNueva = cabinaDAO.findByUbicacion("FONDO NUEVA").get();
        cabinaDAO.delete(cabinaNueva);

        Optional<Cabina> optionalCabinaNueva  = cabinaDAO.findByUbicacion("FONDO NUEVA");
        assertTrue(optionalCabinaNueva.isEmpty());
    }

    @Test
    @Order(9)
    public void test_update(){
        Cabina cabinaVentana = cabinaDAO.findByUbicacion("MEDIO VENTANA").get();
        cabinaVentana.setSuperficie(25.5f);
        cabinaDAO.update(cabinaVentana);

        Cabina cabinaVentanaup = cabinaDAO.findByUbicacion("MEDIO VENTANA").get();
        assertEquals(cabinaVentanaup.getSuperficie(),25.5f);
    }

    @Test
    @Order(10)
    public void test_count(){
        long totalCabinas = cabinaDAO.count();
        assertTrue(totalCabinas > 0);
        assertEquals(4,totalCabinas);
    }

}