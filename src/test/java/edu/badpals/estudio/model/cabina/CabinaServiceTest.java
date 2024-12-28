package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @Order(3)
    public void test_crearCabina(){
        cabinaService.createCabina("TEST CABINA",15.0f,false);

        Cabina cabinaCreada = cabinaService.getCabinaByUbicacion("TEST CABINA");
        assertNotNull(cabinaCreada);
        assertEquals("TEST CABINA",cabinaCreada.getUbicacion());
        assertEquals(15.0f,cabinaCreada.getSuperficie());
        assertEquals(false,cabinaCreada.getPuedeHacerPiercing());
    }

    @Test
    @Order(4)
    public void test_updateCabina(){
        Cabina cabinaTest = cabinaService.getCabinaByUbicacion("TEST CABINA");
        cabinaService.updateCabina(cabinaTest,"TEST CABINA PIERCING",null,true);


        assertNotNull(cabinaTest);
        assertEquals(cabinaTest.getSuperficie(),15.0f);
        assertEquals(true,cabinaTest.getPuedeHacerPiercing());
        assertEquals("TEST CABINA PIERCING",cabinaTest.getUbicacion());
    }

    @Test
    @Order(5)
    public void test_borrarCabina(){
        Cabina cabinaTest = cabinaService.getCabinaByUbicacion("TEST CABINA PIERCING");
        cabinaService.deleteCabina("TEST CABINA PIERCING");

        Cabina cabinaTestPresente = cabinaService.getCabinaByUbicacion("TEST CABINA PIERCING");
        assertNull(cabinaTestPresente);
    }

    @Test
    @Order(6)
    public void test_cabinaNoExiste(){
        Cabina cabinaNoExiste = cabinaService.getCabinaByUbicacion("NO EXISTE");
        assertNull(cabinaNoExiste);

        Cabina cabina256 = cabinaService.getCabina(256);
        assertNull(cabina256);
    }

    @Test
    @Order(7)
    public void test_getAllCabinas(){
        List<Cabina> allCabinas = cabinaService.getAllCabinas();
        assertNotNull(allCabinas);
        assertTrue(allCabinas.size()>0);
        assertEquals(allCabinas.get(0).getId(),1);
    }

    @Test
    @Order(8)
    public void test_count(){
        assertEquals(4,cabinaService.countCabinas());
    }

  
}