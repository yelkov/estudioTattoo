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

class TatuadorDAOTest {

    private static TatuadorDAO tatuadorDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        tatuadorDAO = new TatuadorDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findById(1);
        if(optionalTatuador.isPresent()) {
            Tatuador tatuador = optionalTatuador.get();
            assertEquals(tatuador.getNombre(), "Armando Barullo Seguro");
            assertEquals(tatuador.getNif(), "33445556Y");
            assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(tatuador.getSalario());
            assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
        }
    }

    @Test
    @Order(2)
    public void test_findNull(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findById(1000);
        assertTrue(optionalTatuador.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findByNif(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findByNif("33445556Y");
        if(optionalTatuador.isPresent()) {
            Tatuador tatuador = optionalTatuador.get();
            assertEquals(tatuador.getId(), 1);
            assertEquals(tatuador.getNombre(), "Armando Barullo Seguro");
            assertEquals(tatuador.getNif(), "33445556Y");
            assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(tatuador.getSalario());
            assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
        }
    }
    @Test
    @Order(4)
    public void test_findByNifNull(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findByNif("000000000");
        assertTrue(optionalTatuador.isEmpty());
    }

    @Test
    @Order(5)
    public void test_findAll(){
        List<Tatuador> tatuadores = tatuadorDAO.findAll();
        assertNotNull(tatuadores);
        assertTrue(tatuadores.size() > 0);
        assertEquals(tatuadores.get(0).getId(),1);
        assertEquals(tatuadores.size(),4);
    }

    @Test
    @Order(6)
    public void test_findByNSS(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findByNss("1234567891");
        if(optionalTatuador.isPresent()) {
            Tatuador tatuador = optionalTatuador.get();
            assertEquals(tatuador.getId(), 1);
            assertEquals(tatuador.getNombre(), "Armando Barullo Seguro");
            assertEquals(tatuador.getNif(), "33445556Y");
            assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
            assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
            assertNull(tatuador.getSalario());
            assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
        }
    }
    @Test
    @Order(7)
    public void test_findByNssNull(){
        Optional<Tatuador> optionalTatuador = tatuadorDAO.findByNss("000000000");
        assertTrue(optionalTatuador.isEmpty());
    }

    @Test
    @Order(8)
    public void test_findStartByName(){
        List<Tatuador> tatuadoresA = tatuadorDAO.findStartByName("A");
        assertNotNull(tatuadoresA);
        assertEquals(tatuadoresA.size(),2);
        assertEquals(tatuadoresA.get(0).getId(),1);
        assertEquals(tatuadoresA.get(0).getNombre(),"Armando Barullo Seguro");
        assertEquals(tatuadoresA.get(1).getNombre(),"Aitor Tilla Rica");
    }

    @Test
    @Order(9)
    public void test_findStartByNameNull(){
        List<Tatuador> tatuadoresW = tatuadorDAO.findStartByName("W");
        assertTrue(tatuadoresW.isEmpty());
    }

    @Test
    @Order(10)
    public void test_findStartByNameArm(){
        List<Tatuador> tatuadoresA = tatuadorDAO.findStartByName("Arm");
        assertNotNull(tatuadoresA);
        assertEquals(tatuadoresA.size(),1);
        assertEquals(tatuadoresA.get(0).getId(),1);
        assertEquals(tatuadoresA.get(0).getNombre(),"Armando Barullo Seguro");
    }

    @Test
    @Order(11)
    public void test_createTatuador(){
        Tatuador tatuador = new Tatuador("90316241W","Javier de Test","081774170785",LocalDate.of(1990,7,7),LocalDate.of(2024,12,12),null,"javier.test@gmail.com",60.0f);
        tatuadorDAO.create(tatuador);

        Optional<Tatuador> javierRecuperado = tatuadorDAO.findByNif("90316241W");
        if(javierRecuperado.isPresent()) {
            Tatuador javier = javierRecuperado.get();
            assertEquals(javier.getNombre(), "Javier de Test");
        }
    }

    @Test
    @Order(12)
    public void test_deleteTatuador(){
        Tatuador javi = tatuadorDAO.findByNif("90316241W").get();
        tatuadorDAO.delete(javi);

        Optional<Tatuador> optional = tatuadorDAO.findByNif("90316241W");
        assertTrue(optional.isEmpty());
    }

    @Test
    @Order(13)
    public void test_findStartByNameContainingBar(){
        List<Tatuador> tatuadoresBar = tatuadorDAO.findByNameContaining("Bar");
        assertNotNull(tatuadoresBar);
        assertEquals(tatuadoresBar.size(),1);
        assertEquals(tatuadoresBar.get(0).getId(),1);
        assertEquals(tatuadoresBar.get(0).getNombre(),"Armando Barullo Seguro");
    }

    @Test
    @Order(14)
    public void test_findByNameContainingNull(){
        List<Tatuador> tatuadoresW = tatuadorDAO.findByNameContaining("Wolfram");
        assertTrue(tatuadoresW.isEmpty());
    }

    @Test
    @Order(15)
    public void test_updateTatuador(){
        Tatuador armando = tatuadorDAO.findByNif("33445556Y").get();
        armando.setFechaAlta(LocalDate.of(2024, 5, 1));
        tatuadorDAO.update(armando);

        Tatuador armandoUpdated = tatuadorDAO.findByNif("33445556Y").get();
        assertEquals(armandoUpdated.getFechaAlta(), LocalDate.of(2024, 5, 1));

        armandoUpdated.setFechaAlta(LocalDate.of(2024, 2, 1));
        tatuadorDAO.update(armandoUpdated);
    }

    @Test
    @Order(16)
    public void test_telefonos(){
        Tatuador armando = tatuadorDAO.findById(1).get();
        armando.addTelefono("999666999");
        tatuadorDAO.update(armando);

        Tatuador armandoUpdated = tatuadorDAO.findById(1).get();
        assertTrue(armandoUpdated.getTelefonos().contains("999666999"));
        armandoUpdated.removeTelefono("999666999");
        tatuadorDAO.update(armandoUpdated);
    }

}