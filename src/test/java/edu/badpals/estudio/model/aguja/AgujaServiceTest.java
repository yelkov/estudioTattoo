package edu.badpals.estudio.model.aguja;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AgujaServiceTest {

    private static AgujaService agujaService;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        agujaService = new AgujaService();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getAguja(){
        Aguja aguja = agujaService.getAguja(1);
        assertNotNull(aguja);
        assertEquals("10-1-RL-MEDIUM",aguja.getTipo());
        assertEquals(50l,aguja.getCantidad());
    }

    @Test
    @Order(2)
    public void test_getAgujaNull(){
        Aguja aguja = agujaService.getAguja(1000);
        assertNull(aguja);
    }

    @Test
    @Order(3)
    public void test_getAgujaByTipo(){
        Aguja aguja = agujaService.getAgujaByTipo("10-1-RL-MEDIUM");
        assertNotNull(aguja);
        assertEquals("10-1-RL-MEDIUM",aguja.getTipo());
        assertEquals(50l,aguja.getCantidad());
    }

    @Test
    @Order(4)
    public void test_getAgujaByTipoNull(){
        Aguja aguja = agujaService.getAgujaByTipo("No hay");
        assertNull(aguja);
    }

    @Test
    @Order(5)
    public void test_filtrarAgujas(){
        List<Aguja> agujasRL = agujaService.filtrarAgujaByTipo("RL");
        assertNotNull(agujasRL);
        assertEquals(5,agujasRL.size());
    }

    @Test
    @Order(6)
    public void test_filtrarAgujaByTipoNull(){
        List<Aguja> agujas = agujaService.filtrarAgujaByTipo("No hay");
        assertTrue(agujas.isEmpty());
    }

    @Test
    @Order(7)
    public void test_getAllAgujaS(){
        List<Aguja> allAgujas = agujaService.getAllAgujas();
        assertNotNull(allAgujas);
        assertEquals(allAgujas.size(),10);
    }

    @Test
    @Order(8)
    public void test_crearAguja(){
        agujaService.crearAguja("08-3-RL-EXTRALONG",25l);

        Aguja aguja = agujaService.getAgujaByTipo("08-3-RL-EXTRALONG");
        assertNotNull(aguja);
        assertEquals("08-3-RL-EXTRALONG",aguja.getTipo());
        assertEquals(25l,aguja.getCantidad());
    }

    @Test
    @Order(9)
    public void test_updateAguja(){
        Aguja aguja = agujaService.getAgujaByTipo("08-3-RL-EXTRALONG");
        agujaService.updateAguja(aguja,null,75l);

        Aguja agujaActualizada = agujaService.getAgujaByTipo("08-3-RL-EXTRALONG");
        assertEquals(75l,agujaActualizada.getCantidad());
    }

    @Test
    @Order(10)
    public void test_deleteAguja(){
        agujaService.deleteAguja(null,"08-3-RL-EXTRALONG");

        Aguja aguja = agujaService.getAgujaByTipo("08-3-RL-EXTRALONG");
        assertNull(aguja);
    }

}