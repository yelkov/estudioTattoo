package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.hibernate.annotations.OnDelete;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorServiceTest {
    private static TrabajadorService trabajadorService;

    @BeforeAll
    static void setUp() {
        EntityManagerFactoryProvider.initialize("test");
        trabajadorService = new TrabajadorService();
    }

    @AfterAll
    static void close() {
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getTrabajador(){
        Trabajador trabajador = trabajadorService.getTrabajador(1);
        assertNotNull(trabajador);
        assertEquals(trabajador.getId(),1);
        assertEquals(trabajador.getNif(), "33445556Y");
        assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(trabajador.getSalario());
        assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(2)
    public void test_getTrabajadorNull(){
        Trabajador trabajador = trabajadorService.getTrabajador(1000);
        assertNull(trabajador);
    }
    @Test
    @Order(3)
    public void test_getTrabajador_Nif(){
        Trabajador trabajador = trabajadorService.getTrabajadorByNif("33445556Y");
        assertNotNull(trabajador);
        assertEquals(trabajador.getId(),1);
        assertEquals(trabajador.getNif(), "33445556Y");
        assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(trabajador.getSalario());
        assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(4)
    public void test_getTrabajadorNull_Nif(){
        Trabajador trabajador = trabajadorService.getTrabajadorByNif("adhjfkhsdsabklg");
        assertNull(trabajador);
    }

    @Test
    @Order(5)
    public void test_getTrabajador_Nss(){
        Trabajador trabajador = trabajadorService.getTrabajadorByNss("1234567891");
        assertNotNull(trabajador);
        assertEquals(trabajador.getId(),1);
        assertEquals(trabajador.getNif(), "33445556Y");
        assertEquals(trabajador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(trabajador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(trabajador.getSalario());
        assertEquals(trabajador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(6)
    public void test_getTrabajadorNull_Nss(){
        Trabajador trabajador = trabajadorService.getTrabajadorByNss("adhjfkhsdsabklg");
        assertNull(trabajador);
    }

    @Test
    @Order(7)
    public void test_getAllTrabajadores(){
        List<Trabajador> trabajadores = trabajadorService.getAllTrabajadores();
        assertNotNull(trabajadores);
        assertEquals(trabajadores.size(),6);
        assertEquals(trabajadores.get(0).getId(),1);
    }

    @Test
    @Order(8)
    public void test_getTrabajadoresByName_A(){
        List<Trabajador> trabajadoresA = trabajadorService.getTrabajadoresStartByName("A");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),3);
        assertEquals(trabajadoresA.get(1).getId(),4);
        assertEquals(trabajadoresA.get(1).getNombre(),"Ana Lladora Buena");
    }

    @Test
    @Order(9)
    public void test_getTrabajadoresByName_null(){
        List<Trabajador> trabajadoresA = trabajadorService.getTrabajadoresStartByName("Wol");
        assertTrue(trabajadoresA.isEmpty());
    }

    @Test
    @Order(10)
    public void test_getTrabajadoresByNameContaining(){
        List<Trabajador> trabajadoresA = trabajadorService.getTrabajadoresByNameContaining("to");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),3);
        assertEquals(trabajadoresA.get(0).getId(),3);
        assertEquals(trabajadoresA.get(0).getNombre(),"Elena Nito del Bosque");
    }

    @Test
    @Order(11)
    public void test_getTrabajadoresByNameContaining_null(){
        List<Trabajador> trabajadoresA = trabajadorService.getTrabajadoresByNameContaining("Wol");
        assertTrue(trabajadoresA.isEmpty());
    }

    @Test
    @Order(12)
    public void test_createTrabajador(){
        String[] telefonos = new String[]{"666999888","777888999"};
        trabajadorService.createTrabajador("44368186J","Trabajador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.trabajador@gmail.com",List.of(telefonos));

        Trabajador trabajador = trabajadorService.getTrabajadorByNif("44368186J");
        assertNotNull(trabajador);
        assertEquals(trabajador.getNombre(),"Trabajador de Prueba");
        assertTrue(trabajador.getTelefonos().contains("666999888"));
        assertTrue(trabajador.getTelefonos().contains("777888999"));

    }

    @Test
    @Order(13)
    public void test_createTrabajadorFalse(){
        String[] telefonos = new String[]{"666999888","777888999"};
           IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  trabajadorService.createTrabajador(null,"Trabajador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.trabajador@gmail.com",List.of(telefonos))) ;

           Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(14)
    public void test_createTrabajadorFalse2(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  trabajadorService.createTrabajador("44368186J","Trabajador de Prueba",null, LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.trabajador@gmail.com",List.of(telefonos))) ;

        Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(15)
    public void test_createTrabajadorFalse3(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  trabajadorService.createTrabajador("33445556Y","Trabajador de Prueba Falso","1234567891", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.trabajador@gmail.com",List.of(telefonos))) ;

        Assertions.assertEquals("El nif o el nss ya existen en el registro",exception.getMessage());
    }

    @Test
    @Order(16)
    public void test_deleteTrabajador(){
        trabajadorService.deleteTrabajador(null,"44368186J",null);

        Trabajador trabajadorPrueba = trabajadorService.getTrabajadorByNif("44368186J");
        assertNull(trabajadorPrueba);
    }

    @Test
    @Order(17)
    public void test_deleteTrabajadorFalse(){
        IllegalArgumentException exception = (Assertions.assertThrows(IllegalArgumentException.class, ()-> trabajadorService.deleteTrabajador(null,"37856937569",null)));
    }

    @Test
    @Order(18)
    public void test_updateTrabajador(){
        String[] telefonos = new String[]{"986261463"};
        Trabajador armando = trabajadorService.getTrabajador(1);
        trabajadorService.updateTrabajador(armando,"66221094T","Armando Barullo Suave","999999999X",null,null,null,null, Set.of(telefonos));

        Trabajador armandoUpdated = trabajadorService.getTrabajador(1);
        assertEquals(armandoUpdated.getNombre(),"Armando Barullo Suave");
        assertEquals(armandoUpdated.getNif(),"66221094T");
        assertEquals(armandoUpdated.getNss(),"999999999X");
        assertEquals(armandoUpdated.getFechaNacimiento(),LocalDate.of(2001,9,11));
        assertTrue(armandoUpdated.getTelefonos().contains("986261463"));

        trabajadorService.updateTrabajador(armandoUpdated,"33445556Y","Armando Barullo Seguro","1234567891",null,null,null,null, Set.of());

    }

}