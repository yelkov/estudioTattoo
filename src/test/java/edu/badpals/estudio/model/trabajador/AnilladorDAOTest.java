package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnilladorDAOTest {

    private static AnilladorDAO anilladorDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        anilladorDAO = new AnilladorDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findById(2);
        if(optionalAnillador.isPresent()) {
            Anillador anillador = optionalAnillador.get();
            assertEquals(anillador.getNombre(), "Joseba Cilarte");
            assertEquals(anillador.getNif(), "99887766N");
            assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
            assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
            assertNull(anillador.getSalario());
            assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
        }
    }

    @Test
    @Order(2)
    public void test_findNull(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findById(1000);
        assertTrue(optionalAnillador.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findByNif(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findByNif("99887766N");
        if(optionalAnillador.isPresent()) {
            Anillador anillador = optionalAnillador.get();
            assertEquals(anillador.getId(), 2);
            assertEquals(anillador.getNombre(), "Joseba Cilarte");
            assertEquals(anillador.getNif(), "99887766N");
            assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
            assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
            assertNull(anillador.getSalario());
            assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
        }
    }
    @Test
    @Order(4)
    public void test_findByNifNull(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findByNif("000000000");
        assertTrue(optionalAnillador.isEmpty());
    }

    @Test
    @Order(5)
    public void test_findAll(){
        List<Anillador> anilladores = anilladorDAO.findAll();
        assertNotNull(anilladores);
        assertTrue(anilladores.size() > 0);
        assertEquals(anilladores.get(0).getId(),2);
        assertEquals(anilladores.size(),2);
    }

    @Test
    @Order(6)
    public void test_findByNSS(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findByNss("9876543212");
        if(optionalAnillador.isPresent()) {
            Anillador anillador = optionalAnillador.get();
            assertEquals(anillador.getId(), 2);
            assertEquals(anillador.getNombre(), "Joseba Cilarte");
            assertEquals(anillador.getNif(), "99887766N");
            assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
            assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
            assertNull(anillador.getSalario());
            assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
        }
    }
    @Test
    @Order(7)
    public void test_findByNssNull(){
        Optional<Anillador> optionalAnillador = anilladorDAO.findByNss("000000000");
        assertTrue(optionalAnillador.isEmpty());
    }

    @Test
    @Order(8)
    public void test_findStartByName(){
        List<Anillador> anilladoresA = anilladorDAO.findStartByName("A");
        assertNotNull(anilladoresA);
        assertEquals(anilladoresA.size(),1);
        assertEquals(anilladoresA.get(0).getId(),4);
        assertEquals(anilladoresA.get(0).getNombre(),"Ana Lladora Buena");
    }

    @Test
    @Order(9)
    public void test_findStartByNameNull(){
        List<Anillador> anilladoresW = anilladorDAO.findStartByName("W");
        assertTrue(anilladoresW.isEmpty());
    }

    @Test
    @Order(11)
    public void test_createAnillador(){
        Anillador anillador = new Anillador("90316241W","Javier de Test","081774170785",LocalDate.of(1990,7,7),LocalDate.of(2024,12,12),null,"javier.test@gmail.com",60.0f);
        anilladorDAO.create(anillador);

        Optional<Anillador> javierRecuperado = anilladorDAO.findByNif("90316241W");
        if(javierRecuperado.isPresent()) {
            Anillador javier = javierRecuperado.get();
            assertEquals(javier.getNombre(), "Javier de Test");
        }
    }

    @Test
    @Order(12)
    public void test_deleteAnillador(){
        Anillador javi = anilladorDAO.findByNif("90316241W").get();
        anilladorDAO.delete(javi);

        Optional<Anillador> optional = anilladorDAO.findByNif("90316241W");
        assertTrue(optional.isEmpty());
    }

    @Test
    @Order(13)
    public void test_findStartByNameContainingBar(){
        List<Anillador> anilladoresBar = anilladorDAO.findByNameContaining("lar");
        assertNotNull(anilladoresBar);
        assertEquals(anilladoresBar.size(),1);
        assertEquals(anilladoresBar.get(0).getId(),2);
        assertEquals(anilladoresBar.get(0).getNombre(),"Joseba Cilarte");
    }

    @Test
    @Order(14)
    public void test_findByNameContainingNull(){
        List<Anillador> anilladoresW = anilladorDAO.findByNameContaining("Wolfram");
        assertTrue(anilladoresW.isEmpty());
    }

    @Test
    @Order(15)
    public void test_updateAnillador(){
        Anillador joseba = anilladorDAO.findByNif("99887766N").get();
        joseba.setFechaAlta(LocalDate.of(2024, 5, 1));
        anilladorDAO.update(joseba);

        Anillador josebaUpdated = anilladorDAO.findByNif("99887766N").get();
        assertEquals(josebaUpdated.getFechaAlta(), LocalDate.of(2024, 5, 1));

        josebaUpdated.setFechaAlta(LocalDate.of(2020, 2, 28));
        anilladorDAO.update(josebaUpdated);
    }

    @Test
    @Order(16)
    public void test_telefonos(){
        Anillador joseba = anilladorDAO.findById(2).get();
        joseba.addTelefono("999666999");
        anilladorDAO.update(joseba);

        Anillador josebaUpdated = anilladorDAO.findById(2).get();
        assertTrue(josebaUpdated.getTelefonos().contains("999666999"));
        josebaUpdated.removeTelefono("999666999");
        anilladorDAO.update(josebaUpdated);
    }

}