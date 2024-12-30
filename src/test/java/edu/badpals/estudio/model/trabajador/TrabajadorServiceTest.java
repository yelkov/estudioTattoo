package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.hibernate.annotations.OnDelete;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
}