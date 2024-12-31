package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Tatuador trabajador = tatuadorService.getTatuador(1);
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
    public void test_getTatuadorNull(){
        Tatuador trabajador = tatuadorService.getTatuador(1000);
        assertNull(trabajador);
    }
    @Test
    @Order(3)
    public void test_getTatuador_Nif(){
        Tatuador trabajador = tatuadorService.getTatuadorByNif("33445556Y");
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
    public void test_getTatuadorNull_Nif(){
        Tatuador trabajador = tatuadorService.getTatuadorByNif("adhjfkhsdsabklg");
        assertNull(trabajador);
    }

    @Test
    @Order(5)
    public void test_getTatuador_Nss(){
        Tatuador trabajador = tatuadorService.getTatuadorByNss("1234567891");
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
    public void test_getTatuadorNull_Nss(){
        Tatuador trabajador = tatuadorService.getTatuadorByNss("adhjfkhsdsabklg");
        assertNull(trabajador);
    }

    @Test
    @Order(7)
    public void test_getAllTatuadores(){
        List<Tatuador> trabajadores = tatuadorService.getAllTatuadores();
        assertNotNull(trabajadores);
        assertEquals(trabajadores.size(),4);
        assertEquals(trabajadores.get(0).getId(),1);
    }

    @Test
    @Order(8)
    public void test_getTatuadoresByName_A(){
        List<Tatuador> trabajadoresA = tatuadorService.getTatuadoresStartByName("A");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),2);
        assertEquals(trabajadoresA.get(1).getId(),6);
        assertEquals(trabajadoresA.get(1).getNombre(),"Aitor Tilla Rica");
    }

    @Test
    @Order(9)
    public void test_getTatuadoresByName_null(){
        List<Tatuador> trabajadoresA = tatuadorService.getTatuadoresStartByName("Wol");
        assertTrue(trabajadoresA.isEmpty());
    }

    @Test
    @Order(10)
    public void test_getTatuadoresByNameContaining(){
        List<Tatuador> trabajadoresA = tatuadorService.getTatuadoresByNameContaining("to");
        assertNotNull(trabajadoresA);
        assertEquals(trabajadoresA.size(),2);
        assertEquals(trabajadoresA.get(0).getId(),5);
        assertEquals(trabajadoresA.get(0).getNombre(),"Paco Merlo Toito");
    }

    @Test
    @Order(11)
    public void test_getTatuadoresByNameContaining_null(){
        List<Tatuador> trabajadoresA = tatuadorService.getTatuadoresByNameContaining("Wol");
        assertTrue(trabajadoresA.isEmpty());
    }
}