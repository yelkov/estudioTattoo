package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnilladorServiceTest {
    private static AnilladorService anilladorService;

    @BeforeAll
    static void setUp() {
        EntityManagerFactoryProvider.initialize("test");
        anilladorService = new AnilladorService();
    }

    @AfterAll
    static void close() {
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getAnillador(){
        Anillador anillador = anilladorService.getAnillador(2);
        assertNotNull(anillador);
        assertEquals(anillador.getId(),2);
        assertEquals(anillador.getNif(), "99887766N");
        assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
        assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
        assertNull(anillador.getSalario());
        assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
    }

    @Test
    @Order(2)
    public void test_getAnilladorNull(){
        Anillador anillador = anilladorService.getAnillador(1000);
        assertNull(anillador);
    }
    @Test
    @Order(3)
    public void test_getAnillador_Nif(){
        Anillador anillador = anilladorService.getAnilladorByNif("99887766N");
        assertNotNull(anillador);
        assertEquals(anillador.getId(),2);
        assertEquals(anillador.getNif(), "99887766N");
        assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
        assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
        assertNull(anillador.getSalario());
        assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
    }

    @Test
    @Order(4)
    public void test_getAnilladorNull_Nif(){
        Anillador anillador = anilladorService.getAnilladorByNif("adhjfkhsdsabklg");
        assertNull(anillador);
    }

    @Test
    @Order(5)
    public void test_getAnillador_Nss(){
        Anillador anillador = anilladorService.getAnilladorByNss("9876543212");
        assertNotNull(anillador);
        assertEquals(anillador.getId(),2);
        assertEquals(anillador.getNif(), "99887766N");
        assertEquals(anillador.getFechaNacimiento(), LocalDate.of(1995, 7, 7));
        assertEquals(anillador.getFechaAlta(), LocalDate.of(2020, 2, 28));
        assertNull(anillador.getSalario());
        assertEquals(anillador.getEmail(), "j.cilarte@hotmail.com");
    }

    @Test
    @Order(6)
    public void test_getAnilladorNull_Nss(){
        Anillador anillador = anilladorService.getAnilladorByNss("adhjfkhsdsabklg");
        assertNull(anillador);
    }

    @Test
    @Order(7)
    public void test_getAllAnilladores(){
        List<Anillador> anilladores = anilladorService.getAllAnilladores();
        assertNotNull(anilladores);
        assertEquals(2,anilladores.size());
        assertEquals(anilladores.get(0).getId(),2);
    }

    @Test
    @Order(8)
    public void test_getAnilladoresByName_A(){
        List<Anillador> anilladoresA = anilladorService.getAnilladoresStartByName("A");
        assertNotNull(anilladoresA);
        assertEquals(anilladoresA.size(),1);
        assertEquals(anilladoresA.get(0).getId(),4);
        assertEquals(anilladoresA.get(0).getNombre(),"Ana Lladora Buena");
    }

    @Test
    @Order(9)
    public void test_getAnilladoresByName_null(){
        List<Anillador> anilladoresA = anilladorService.getAnilladoresStartByName("Wol");
        assertTrue(anilladoresA.isEmpty());
    }

    @Test
    @Order(10)
    public void test_getAnilladoresByNameContaining(){
        List<Anillador> anilladoresA = anilladorService.getAnilladoresByNameContaining("r");
        assertNotNull(anilladoresA);
        assertEquals(anilladoresA.size(),2);
        assertEquals(anilladoresA.get(1).getId(),4);
        assertEquals(anilladoresA.get(1).getNombre(),"Ana Lladora Buena");
    }

    @Test
    @Order(11)
    public void test_getAnilladoresByNameContaining_null(){
        List<Anillador> anilladoresA = anilladorService.getAnilladoresByNameContaining("Wol");
        assertTrue(anilladoresA.isEmpty());
    }

    @Test
    @Order(12)
    public void test_createAnillador(){
        String[] telefonos = new String[]{"666999888","777888999"};
        Map<String, Integer> piezas_propias  = new HashMap<>();
        piezas_propias.put("Septum plata",1);
        piezas_propias.put("Dilatacion m2",3);
        anilladorService.createAnillador("44368186J","Anillador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.anillador@gmail.com",60.0f, Set.of(telefonos),piezas_propias);

        Anillador anillador = anilladorService.getAnilladorByNif("44368186J");
        assertNotNull(anillador);
        assertEquals(anillador.getNombre(),"Anillador de Prueba");
        assertTrue(anillador.getTelefonos().contains("666999888"));
        assertTrue(anillador.getTelefonos().contains("777888999"));
        assertTrue(anillador.getPiezas_propias().containsKey("Septum plata"));

    }

    @Test
    @Order(13)
    public void test_createAnilladorFalse(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->  anilladorService.createAnillador(null,"Anillador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.anillador@gmail.com",60.0f,Set.of(telefonos),null)) ;

        Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(14)
    public void test_createAnilladorFalse2(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  anilladorService.createAnillador("44368186J","Anillador de Prueba",null, LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.anillador@gmail.com",60.0f,Set.of(telefonos),null)) ;

        Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(15)
    public void test_createAnilladorFalse3(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  anilladorService.createAnillador("99887766N","Anillador de Prueba Falso","1234567891", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.anillador@gmail.com",60.0f,Set.of(telefonos),null));

        Assertions.assertEquals("El nif o el nss ya existen en el registro",exception.getMessage());
    }

    @Test
    @Order(16)
    public void test_deleteAnillador(){
        anilladorService.deleteAnillador(null,"44368186J",null);

        Anillador anilladorPrueba = anilladorService.getAnilladorByNif("44368186J");
        assertNull(anilladorPrueba);
    }

    @Test
    @Order(17)
    public void test_deleteAnilladorFalse(){
        IllegalArgumentException exception = (Assertions.assertThrows(IllegalArgumentException.class, ()-> anilladorService.deleteAnillador(null,"37856937569",null)));
    }

}