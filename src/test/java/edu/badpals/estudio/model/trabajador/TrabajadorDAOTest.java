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

class TrabajadorDAOTest {

    private static TrabajadorDAO trabajadorDAO;


    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        trabajadorDAO = new TrabajadorDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findById(1);
        if(optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();
            assertEquals(trabajador.getNombre(), "Armando Barullo Seguro");
            assertEquals(trabajador.getNif(), "33445556Y");
            assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(trabajador.getSalario());
            assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
        }
    }

    @Test
    @Order(2)
    public void test_findNull(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findById(1000);
        assertTrue(optionalTrabajador.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findByNif(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findByNif("33445556Y");
        if(optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();
            assertEquals(trabajador.getId(), 1);
            assertEquals(trabajador.getNombre(), "Armando Barullo Seguro");
            assertEquals(trabajador.getNif(), "33445556Y");
            assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(trabajador.getSalario());
            assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
        }
    }
    @Test
    @Order(4)
    public void test_findByNifNull(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findByNif("000000000");
        assertTrue(optionalTrabajador.isEmpty());
    }

    @Test
    @Order(5)
    public void test_findAll(){
        List<Trabajador> trabajadores = trabajadorDAO.findAll();
        assertNotNull(trabajadores);
        assertTrue(trabajadores.size() > 0);
        assertEquals(trabajadores.get(0).getId(),1);
        assertEquals(trabajadores.size(),6);
    }

    @Test
    @Order(6)
    public void test_findByNSS(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findByNss("1234567891");
        if(optionalTrabajador.isPresent()) {
            Trabajador trabajador = optionalTrabajador.get();
            assertEquals(trabajador.getId(), 1);
            assertEquals(trabajador.getNombre(), "Armando Barullo Seguro");
            assertEquals(trabajador.getNif(), "33445556Y");
            assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(trabajador.getSalario());
            assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
        }
    }
    @Test
    @Order(7)
    public void test_findByNssNull(){
        Optional<Trabajador> optionalTrabajador = trabajadorDAO.findByNss("000000000");
        assertTrue(optionalTrabajador.isEmpty());
    }

    @Test
    @Order(8)
    public void test_findStartByName(){
        List<Trabajador> trabajadoresA = trabajadorDAO.findStartByName("A");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),3);
        assertEquals(trabajadoresA.get(0).getId(),1);
        assertEquals(trabajadoresA.get(0).getNombre(),"Armando Barullo Seguro");
        assertEquals(trabajadoresA.get(1).getNombre(),"Ana Lladora Buena");
        assertEquals(trabajadoresA.get(2).getNombre(),"Aitor Tilla Rica");
    }

    @Test
    @Order(9)
    public void test_findStartByNameNull(){
        List<Trabajador> trabajadoresW = trabajadorDAO.findStartByName("W");
        assertTrue(trabajadoresW.isEmpty());
    }

    @Test
    @Order(10)
    public void test_findStartByNameArm(){
        List<Trabajador> trabajadoresA = trabajadorDAO.findStartByName("Arm");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),1);
        assertEquals(trabajadoresA.get(0).getId(),1);
        assertEquals(trabajadoresA.get(0).getNombre(),"Armando Barullo Seguro");
    }

    @Test
    @Order(11)
    public void test_createTrabajador(){
        Trabajador trabajador = new Trabajador("90316241W","Javier de Test","081774170785",LocalDate.of(1990,7,7),LocalDate.of(2024,12,12),null,"javier.test@gmail.com");
        trabajadorDAO.create(trabajador);

        Optional<Trabajador> javierRecuperado = trabajadorDAO.findByNif("90316241W");
        if(javierRecuperado.isPresent()) {
            Trabajador javier = javierRecuperado.get();
            assertEquals(javier.getNombre(), "Javier de Test");
        }
    }

    @Test
    @Order(12)
    public void test_deleteTrabajador(){
        Trabajador javi = trabajadorDAO.findByNif("90316241W").get();
        trabajadorDAO.delete(javi);

        Optional<Trabajador> optional = trabajadorDAO.findByNif("90316241W");
        assertTrue(optional.isEmpty());
    }

    @Test
    @Order(13)
    public void test_findStartByNameContainingBar(){
        List<Trabajador> trabajadoresBar = trabajadorDAO.findByNameContaining("Bar");
        assertNotNull(trabajadoresBar);
        assertEquals(trabajadoresBar.size(),1);
        assertEquals(trabajadoresBar.get(0).getId(),1);
        assertEquals(trabajadoresBar.get(0).getNombre(),"Armando Barullo Seguro");
    }

    @Test
    @Order(14)
    public void test_findByNameContainingNull(){
        List<Trabajador> trabajadoresW = trabajadorDAO.findByNameContaining("Wolfram");
        assertTrue(trabajadoresW.isEmpty());
    }

    @Test
    @Order(15)
    public void test_updateTrabajador(){
        Trabajador armando = trabajadorDAO.findByNif("33445556Y").get();
        armando.setFechaAlta(LocalDate.of(2024, 5, 1));
        trabajadorDAO.update(armando);

        Trabajador armandoUpdated = trabajadorDAO.findByNif("33445556Y").get();
        assertEquals(armandoUpdated.getFechaAlta(), LocalDate.of(2024, 5, 1));

        armandoUpdated.setFechaAlta(LocalDate.of(2024, 2, 1));
        trabajadorDAO.update(armandoUpdated);
    }

    @Test
    @Order(16)
    public void test_telefonos(){
        Trabajador armando = trabajadorDAO.findById(1).get();
        armando.addTelefono("999666999");
        trabajadorDAO.update(armando);

        Trabajador armandoUpdated = trabajadorDAO.findById(1).get();
        assertTrue(armandoUpdated.getTelefonos().contains("999666999"));
        armandoUpdated.removeTelefono("999666999");
        trabajadorDAO.update(armandoUpdated);
    }


}