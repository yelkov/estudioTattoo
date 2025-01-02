package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TatuadorServiceTest {
    private static TatuadorService tatuadorService;

    @BeforeAll
    static void setUp() {
        EntityManagerFactoryProvider.initialize("test");
        tatuadorService = new TatuadorService();
    }

    @AfterAll
    static void close() {
        EntityManagerFactoryProvider.close();
    }



    @Test
    @Order(1)
    public void test_getTatuador(){
        Tatuador tatuador = tatuadorService.getTatuador(1);
        assertNotNull(tatuador);
        assertEquals(tatuador.getId(),1);
        assertEquals(tatuador.getNif(), "33445556Y");
        assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(tatuador.getSalario());
        assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(2)
    public void test_getTatuadorNull(){
        Tatuador tatuador = tatuadorService.getTatuador(1000);
        assertNull(tatuador);
    }
    @Test
    @Order(3)
    public void test_getTatuador_Nif(){
        Tatuador tatuador = tatuadorService.getTatuadorByNif("33445556Y");
        assertNotNull(tatuador);
        assertEquals(tatuador.getId(),1);
        assertEquals(tatuador.getNif(), "33445556Y");
        assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(tatuador.getSalario());
        assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(4)
    public void test_getTatuadorNull_Nif(){
        Tatuador tatuador = tatuadorService.getTatuadorByNif("adhjfkhsdsabklg");
        assertNull(tatuador);
    }

    @Test
    @Order(5)
    public void test_getTatuador_Nss(){
        Tatuador tatuador = tatuadorService.getTatuadorByNss("1234567891");
        assertNotNull(tatuador);
        assertEquals(tatuador.getId(),1);
        assertEquals(tatuador.getNif(), "33445556Y");
        assertEquals(tatuador.getFechaNacimiento(), LocalDate.of(2001, 9, 11));
        assertEquals(tatuador.getFechaAlta(), LocalDate.of(2024, 2, 1));
        assertNull(tatuador.getSalario());
        assertEquals(tatuador.getEmail(), "armando.barullo@gmail.com");
    }

    @Test
    @Order(6)
    public void test_getTatuadorNull_Nss(){
        Tatuador tatuador = tatuadorService.getTatuadorByNss("adhjfkhsdsabklg");
        assertNull(tatuador);
    }

    @Test
    @Order(7)
    public void test_getAllTatuadores(){
        List<Tatuador> tatuadores = tatuadorService.getAllTatuadores();
        assertNotNull(tatuadores);
        assertEquals(4,tatuadores.size());
        assertEquals(tatuadores.get(0).getId(),1);
    }

    @Test
    @Order(8)
    public void test_getTatuadoresByName_A(){
        List<Tatuador> tatuadoresA = tatuadorService.getTatuadoresStartByName("A");
        assertNotNull(tatuadoresA);
        assertEquals(tatuadoresA.size(),2);
        assertEquals(tatuadoresA.get(1).getId(),6);
        assertEquals(tatuadoresA.get(1).getNombre(),"Aitor Tilla Rica");
    }

    @Test
    @Order(9)
    public void test_getTatuadoresByName_null(){
        List<Tatuador> tatuadoresA = tatuadorService.getTatuadoresStartByName("Wol");
        assertTrue(tatuadoresA.isEmpty());
    }

    @Test
    @Order(10)
    public void test_getTatuadoresByNameContaining(){
        List<Tatuador> tatuadoresA = tatuadorService.getTatuadoresByNameContaining("to");
        assertNotNull(tatuadoresA);
        assertEquals(tatuadoresA.size(),2);
        assertEquals(tatuadoresA.get(0).getId(),5);
        assertEquals(tatuadoresA.get(0).getNombre(),"Paco Merlo Toito");
    }

    @Test
    @Order(11)
    public void test_getTatuadoresByNameContaining_null(){
        List<Tatuador> tatuadoresA = tatuadorService.getTatuadoresByNameContaining("Wol");
        assertTrue(tatuadoresA.isEmpty());
    }

    @Test
    @Order(12)
    public void test_createTatuador(){
        String[] telefonos = new String[]{"666999888","777888999"};
        tatuadorService.createTatuador("44368186J","Tatuador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.tatuador@gmail.com",60.0f, Set.of(telefonos));

        Tatuador tatuador = tatuadorService.getTatuadorByNif("44368186J");
        assertNotNull(tatuador);
        assertEquals(tatuador.getNombre(),"Tatuador de Prueba");
        assertTrue(tatuador.getTelefonos().contains("666999888"));
        assertTrue(tatuador.getTelefonos().contains("777888999"));
    }

    @Test
    @Order(13)
    public void test_createTatuadorFalse(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->  tatuadorService.createTatuador(null,"Tatuador de Prueba","021054466554", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.tatuador@gmail.com",60.0f,Set.of(telefonos))) ;

        Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(14)
    public void test_createTatuadorFalse2(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  tatuadorService.createTatuador("44368186J","Tatuador de Prueba",null, LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.tatuador@gmail.com",60.0f,Set.of(telefonos))) ;

        Assertions.assertEquals("Hay campos obligatorios sin rellenar",exception.getMessage());
    }

    @Test
    @Order(15)
    public void test_createTatuadorFalse3(){
        String[] telefonos = new String[]{"666999888","777888999"};
        IllegalArgumentException exception =Assertions.assertThrows(IllegalArgumentException.class, () ->  tatuadorService.createTatuador("33445556Y","Tatuador de Prueba Falso","1234567891", LocalDate.of(2000,7,5),LocalDate.of(2024,9,1),null,"prueba.tatuador@gmail.com",60.0f,Set.of(telefonos)));

        Assertions.assertEquals("El nif o el nss ya existen en el registro",exception.getMessage());
    }

    public void test_updateTatuador(){
        Tatuador tatuadorPrueba = tatuadorService.getTatuadorByNif("44368186J");
        tatuadorService.updateTatuador(tatuadorPrueba,"44368186J","Tatuador con otro nombre","000000000000",null,null,null,"newmail@mail.com",null,65.0f);

        Tatuador tatuadorRecuperado = tatuadorService.getTatuadorByNif("44368186J");
        assertNotNull(tatuadorPrueba);
        assertEquals(tatuadorPrueba.getNombre(),"Tatuador con otro nombre");
        assertEquals(tatuadorPrueba.getNss(),"000000000000");
        assertEquals(tatuadorPrueba.getEmail(),"newmail@mail.com");
        assertEquals(tatuadorPrueba.getComision(),65.0f);
    }

    @Test
    @Order(17)
    public void test_deleteTatuador(){
        tatuadorService.deleteTatuador(null,"44368186J",null);

        Tatuador tatuadorPrueba = tatuadorService.getTatuadorByNif("44368186J");
        assertNull(tatuadorPrueba);
    }

    @Test
    @Order(18)
    public void test_deleteTatuadorFalse(){
        IllegalArgumentException exception = (Assertions.assertThrows(IllegalArgumentException.class, ()-> tatuadorService.deleteTatuador(null,"37856937569",null)));
    }

    @Test
    @Order(19)
    public void test_getTatuadorWithDiseños(){
        Tatuador armando = tatuadorService.getTatuadorWithDiseños(1);
        assertNotNull(armando);
        assertTrue(armando.getDiseños().containsKey("Tag de prueba"));
    }

    @Test
    @Order(20)
    public void test_addDiseño(){
        Tatuador joseba = tatuadorService.getTatuador(2);
        String tag = "Diseño de prueba de Joseba";
        byte[] diseño = tag.getBytes();

        tatuadorService.addDiseño(joseba.getId(),tag,diseño);

        Tatuador josebaRecuperado = tatuadorService.getTatuadorWithDiseños(2);
        assertNotNull(josebaRecuperado);
        assertTrue(josebaRecuperado.getDiseños().containsKey("Diseño de prueba de Joseba"));
    }

    @Test
    @Order(21)
    public void test_removeDiseño(){
        Tatuador joseba = tatuadorService.getTatuador(2);
        String tag = "Diseño de prueba de Joseba";

        tatuadorService.removeDiseño(joseba.getId(),tag);

        Tatuador josebaRecuperado = tatuadorService.getTatuadorWithDiseños(2);
        assertNotNull(josebaRecuperado);
        assertFalse(josebaRecuperado.getDiseños().containsKey("Diseño de prueba de Joseba"));
    }


}