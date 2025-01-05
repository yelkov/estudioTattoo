package edu.badpals.estudio.model.aguja;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AgujaDAOTest {

    private static AgujaDAO agujaDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        agujaDAO = new AgujaDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){

        Optional<Aguja> optional_aguja1 = agujaDAO.findById(1);
        if(optional_aguja1.isPresent()){
            Aguja aguja1 = optional_aguja1.get();
            assertNotNull(aguja1);
            assertEquals(aguja1.getId(),1);
        }
    }

    @Test
    @Order(2)
    public void test_findNull(){
        Optional<Aguja> optional_aguja1 = agujaDAO.findById(1000);
        assertTrue(optional_aguja1.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findAll(){
        List<Aguja> agujas = agujaDAO.findAll();
        assertNotNull(agujas);
        assertTrue(agujas.size() > 0);
        assertEquals(agujas.size(),10);
    }

    @Test
    @Order(4)
    public void test_findByTipo(){
        Optional<Aguja> optional_aguja1 = agujaDAO.findByTipo("10-1-RL-MEDIUM");
        if(optional_aguja1.isPresent()){
            Aguja aguja1 = optional_aguja1.get();
            assertNotNull(aguja1);
            assertEquals(aguja1.getTipo(),"10-1-RL-MEDIUM");
            assertEquals(aguja1.getId(),1);
            assertEquals(aguja1.getCantidad(),50);
        }
    }

    @Test
    @Order(5)
    public void test_findByTipoNull(){
        Optional<Aguja> agujas = agujaDAO.findByTipo("No hay");
        assertTrue(agujas.isEmpty());
    }

    @Test
    @Order(6)
    public void test_filtrarByTipo(){
        List<Aguja> agujas = agujaDAO.filtrarByTipo("RL");
        assertNotNull(agujas);
        assertTrue(agujas.size() > 0);
        assertEquals(agujas.size(),5);
    }

    @Test
    @Order(7)
    public void test_filtrarByTipoInicio(){
        List<Aguja> agujas = agujaDAO.filtrarByTipo("10");
        assertNotNull(agujas);
        assertTrue(agujas.size() > 0);
        assertEquals(agujas.size(),8);
    }

    @Test
    @Order(8)
    public void test_filtroNull(){
        List<Aguja> agujas = agujaDAO.filtrarByTipo("No hay");
        assertTrue(agujas.isEmpty());
    }

    @Test
    @Order(9)
    public void test_create(){
        Aguja aguja = new Aguja("08-3-RL-EXTRALONG",25l);
        agujaDAO.create(aguja);

        Aguja agujaRecuperada = agujaDAO.findByTipo("08-3-RL-EXTRALONG").get();
        assertNotNull(agujaRecuperada);
        assertEquals(agujaRecuperada.getTipo(),"08-3-RL-EXTRALONG");
        assertEquals(agujaRecuperada.getCantidad(),25l);
    }

    @Test
    @Order(10)
    public void test_update(){
        Aguja aguja = agujaDAO.findByTipo("08-3-RL-EXTRALONG").get();
        aguja.setCantidad(45l);
        agujaDAO.update(aguja);

        Aguja agujaRecuperada = agujaDAO.findByTipo("08-3-RL-EXTRALONG").get();
        assertEquals(agujaRecuperada.getCantidad(),45l);

    }

    @Test
    @Order(11)
    public void deleteAguja(){
        Aguja aguja = agujaDAO.findByTipo("08-3-RL-EXTRALONG").get();
        agujaDAO.delete(aguja);

        Optional<Aguja> agujaRecuperada = agujaDAO.findByTipo("08-3-RL-EXTRALONG");
        assertTrue(agujaRecuperada.isEmpty());
    }
}
