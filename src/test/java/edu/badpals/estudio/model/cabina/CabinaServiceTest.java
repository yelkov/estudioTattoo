package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CabinaServiceTest {
    private static CabinaService cabinaService;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        CabinaDAO cabinaDAO = new CabinaDAO();
        cabinaService = new CabinaService(cabinaDAO);
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getCabina(){
        Cabina cabinaFondo = cabinaService.getCabina(1);
        assertNotNull(cabinaFondo);
        assertEquals(cabinaFondo.getId(),1);
        assertEquals("FONDO PIERCING",cabinaFondo.getUbicacion());
        assertEquals(12.5f,cabinaFondo.getSuperficie());
        assertEquals(true,cabinaFondo.getPuedeHacerPiercing());
    }

    @Test
    @Order(2)
    public void test_getCabinaByUbicacion(){
        Cabina cabinaFondo = cabinaService.getCabinaByUbicacion("FONDO PIERCING");
        assertNotNull(cabinaFondo);
        assertEquals(cabinaFondo.getId(),1);
        assertEquals("FONDO PIERCING",cabinaFondo.getUbicacion());
        assertEquals(12.5f,cabinaFondo.getSuperficie());
        assertEquals(true,cabinaFondo.getPuedeHacerPiercing());
    }

    @Test
    public void test_crearCabina(){
        cabinaService.createCabina("TEST CABINA",15.0f,false);

        Cabina cabinaCreada = cabinaService.getCabinaByUbicacion("TEST CABINA");
        assertNotNull(cabinaCreada);
        assertEquals("TEST CABINA",cabinaCreada.getUbicacion());
        assertEquals(15.0f,cabinaCreada.getSuperficie());
        assertEquals(false,cabinaCreada.getPuedeHacerPiercing());


    }
  
}