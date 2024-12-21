package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    public void test_find(){
        Optional<Cabina> optional_cabina1 = cabinaDAO.findById(1);
        Cabina cabina1 = optional_cabina1.get();
        assertNotNull(cabina1);
        assertEquals(cabina1.getId(),1);
        assertEquals(cabina1.getUbicacion(),"FONDO PIERCING");
        assertEquals(cabina1.getSuperficie(),12.5f);
        assertTrue(cabina1.getPuedeHacerPiercing());
    }

    @Test
    public void test_find_empty(){
        Optional<Cabina> optional_cabinaEmpty = cabinaDAO.findById(10);
        assertTrue(optional_cabinaEmpty.isEmpty());
    }

    @Test
    public void test_findAll(){
        List<Cabina> cabinas = cabinaDAO.findAll();
        assertNotNull(cabinas);
        assertTrue(cabinas.size() > 0);
        assertEquals(cabinas.get(0).getId(),1);
        assertEquals(4,cabinas.size());
    }

}