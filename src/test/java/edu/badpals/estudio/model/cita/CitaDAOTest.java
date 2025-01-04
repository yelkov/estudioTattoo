package edu.badpals.estudio.model.cita;
import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitaDAOTest {
    private static CitaDAO citaDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        citaDAO = new CitaDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_findCita(){
        Optional<Cita> citaOptional = citaDAO.findById(1);
        if(citaOptional.isPresent()){
            Cita cita = citaOptional.get();
            assertEquals(1, cita.getId());
            assertEquals(1,cita.getTatuador().getId());
            assertEquals(1,cita.getCabina().getId());
        }
    }

    @Test
    @Order(2)
    public void test_findCitaNull(){
        Optional<Cita> citaOptional = citaDAO.findById(1000);
        assertTrue(citaOptional.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findAll(){
        List<Cita> citas = citaDAO.findAll();
        assertTrue(citas.size() > 0);
        assertEquals(4, citas.size());
    }

    @Test
    @Order(4)
    public void test_findByTipoTatuaje(){
        List<Cita> citasTatuaje = citaDAO.findByTipo(Tipo.TATUAJE);
        assertEquals(2, citasTatuaje.size());
        assertEquals(Tipo.TATUAJE, citasTatuaje.get(0).getTipo());
    }

    @Test
    @Order(5)
    public void test_findByTipoPiercing(){
        List<Cita> citasPiercing = citaDAO.findByTipo(Tipo.PIERCING);
        assertEquals(2, citasPiercing.size());
        assertEquals(Tipo.PIERCING, citasPiercing.get(0).getTipo());
    }

    @Test
    @Order(6)
    public void test_findByTatuador(){
        List<Cita> citasTatuador1 = citaDAO.findByTatuador(1);
        assertEquals(1, citasTatuador1.size());
        assertEquals("Primera cita de prueba",citasTatuador1.get(0).getDescripcion());
    }

    @Test
    @Order(7)
    public void test_findByTatuadorNull(){
        List<Cita> citaTatuadorNull = citaDAO.findByTatuador(1000);
        assertTrue(citaTatuadorNull.isEmpty());
    }

    @Test
    @Order(8)
    public void test_findByAnillador(){
        List<Cita> citasAnillador2 = citaDAO.findByAnillador(2);
        assertEquals(1, citasAnillador2.size());
        assertEquals("Cita de piercing de prueba",citasAnillador2.get(0).getDescripcion());
    }

    @Test
    @Order(9)
    public void test_findByAnilladorNull(){
        List<Cita> citasAnilladorNull = citaDAO.findByAnillador(1000);
        assertTrue(citasAnilladorNull.isEmpty());
    }

    @Test
    @Order(10)
    public void test_findByCabina(){
        List<Cita> citasCabina = citaDAO.findByCabina(1);
        assertEquals(2, citasCabina.size());
        assertEquals("Primera cita de prueba",citasCabina.get(0).getDescripcion());
        assertEquals("Cita de piercing de prueba",citasCabina.get(1).getDescripcion());
    }

    @Test
    @Order(11)
    public void test_findByCabinaNull(){
        List<Cita> citasCabinaNull = citaDAO.findByCabina(1000);
        assertTrue(citasCabinaNull.isEmpty());
    }

    @Test
    @Order(12)
    public void test_findByDescripcionCabina(){
        Optional<Cita> citaOptional = citaDAO.findByDescripcionCabina("Cita de piercing de prueba",1);
        if(citaOptional.isPresent()){
            Cita cita = citaOptional.get();
            assertEquals(3, cita.getId());
            assertEquals("Cita de piercing de prueba",cita.getDescripcion());
            assertEquals(Estado.RESERVADA, cita.getEstado());
        }
    }

    @Test
    @Order(13)
    public void test_findByDescripcionCabinaNull(){
        Optional<Cita> citaOptional = citaDAO.findByDescripcionCabina("No existe",1);
        assertTrue(citaOptional.isEmpty());
    }

    @Test
    @Order(14)
    public void test_findByEstado(){
        List<Cita> citasReservadas = citaDAO.findByEstado(Estado.RESERVADA);
        assertEquals(2, citasReservadas.size());
        assertEquals("Primera cita de prueba", citasReservadas.get(0).getDescripcion());
    }

    @Test
    @Order(15)
    public void test_findByDescripcionContaining(){
        List<Cita> citasDescripcion = citaDAO.findByDescripcionContaining("cabina");
        assertEquals(2, citasDescripcion.size());
        assertEquals("Tatuaje en cabina 2", citasDescripcion.get(0).getDescripcion());
        assertEquals(Estado.REALIZADA, citasDescripcion.get(1).getEstado());
    }

}