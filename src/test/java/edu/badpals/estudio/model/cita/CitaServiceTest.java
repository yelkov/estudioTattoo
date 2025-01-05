package edu.badpals.estudio.model.cita;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaService;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cabina.HuecoService;
import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.cliente.ClienteService;
import edu.badpals.estudio.model.cliente.Parentesco;
import edu.badpals.estudio.model.trabajador.AnilladorService;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.trabajador.TatuadorService;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitaServiceTest {
    private static CitaService citaService;
    private static CabinaService cabinaService;
    private static HuecoService huecoService;
    private static TatuadorService tatuadorService;
    private static AnilladorService anilladorService;
    private static ClienteService clienteService;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        citaService = new CitaService();
        cabinaService = new CabinaService();
        huecoService = new HuecoService();
        tatuadorService = new TatuadorService();
        anilladorService = new AnilladorService();
        clienteService = new ClienteService();

        cabinaService.crearHuecosSemanales(LocalDate.of(2024,12,16),1);
    }

    @AfterAll
    public static void close(){
        cabinaService.eliminarHuecosVacios();
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getCita(){
        Cita cita1 = citaService.getCita(1);
        assertEquals("Primera cita de prueba",cita1.getDescripcion());
        assertEquals(120.0f,cita1.getPrecio());
        assertEquals(1,cita1.getTatuador().getId());
    }

    @Test
    @Order(2)
    public void test_getCita_null(){
        Cita cita1 = citaService.getCita(1000);
        assertNull(cita1);
    }

    @Test
    @Order(3)
    public void test_getCitaByDescripcionCabina(){
        Cita cita1 = citaService.getCitaByDescripcionCabina("Primera cita de prueba",1);
        assertEquals("Primera cita de prueba",cita1.getDescripcion());
        assertEquals(120.0f,cita1.getPrecio());
        assertEquals(1,cita1.getTatuador().getId());
    }

    @Test
    @Order(4)
    public void test_getCitaByDescripcionCabina_null(){
        Cita cita1 = citaService.getCitaByDescripcionCabina("Descripcion no presente",1000);
        assertNull(cita1);
    }

    @Test
    @Order(5)
    public void test_getAllCitas(){
        List<Cita> citas = citaService.getAllCitas();
        assertEquals(4,citas.size());
        assertEquals("Primera cita de prueba",citas.get(0).getDescripcion());
    }

    @Test
    @Order(6)
    public void test_getCitasByTipo(){
        List<Cita> citasTatuaje = citaService.getCitasByTipo(Tipo.TATUAJE);
        assertEquals(2,citasTatuaje.size());
        assertEquals("Primera cita de prueba",citasTatuaje.get(0).getDescripcion());
    }

    @Test
    @Order(7)
    public void test_getCitasByCabina(){
        List<Cita> citasCabina1 = citaService.getCitasByCabina(1);
        assertEquals(2,citasCabina1.size());
        assertEquals(Estado.RESERVADA,citasCabina1.get(1).getEstado());
    }

    @Test
    @Order(8)
    public void test_getCitasByCabina_null(){
        List<Cita> citasCabinaNull = citaService.getCitasByCabina(1000);
        assertTrue(citasCabinaNull.isEmpty());
    }

    @Test
    @Order(9)
    public void test_getCitasByTatuador(){
        List<Cita> citasTatuador1 = citaService.getCitasByTatuador(1);
        assertEquals(1,citasTatuador1.size());
        assertEquals("Primera cita de prueba",citasTatuador1.get(0).getDescripcion());
    }

    @Test
    @Order(10)
    public void test_getCitasByTatuador_null(){
        List<Cita> citasTatuadorNull = citaService.getCitasByTatuador(1000);
        assertTrue(citasTatuadorNull.isEmpty());
    }

    @Test
    @Order(11)
    public void test_getCitasByAnillador(){
        List<Cita> citasAnillador2 = citaService.getCitasByAnillador(2);
        assertEquals(1,citasAnillador2.size());
        assertEquals("Cita de piercing de prueba",citasAnillador2.get(0).getDescripcion());
    }

    @Test
    @Order(12)
    public void test_getCitasByAnillador_null(){
        List<Cita> citasAnilladorNull = citaService.getCitasByAnillador(1000);
        assertTrue(citasAnilladorNull.isEmpty());
    }

    @Test
    @Order(13)
    public void test_getCitasByEstado(){
        List<Cita> citasReservadas = citaService.getCitasByEstado(Estado.RESERVADA);
        assertEquals(2,citasReservadas.size());
        assertEquals("Primera cita de prueba",citasReservadas.get(0).getDescripcion());
    }

    @Test
    @Order(14)
    public void test_crearCitaTatuaje(){
        Hueco hueco1 = huecoService.getHueco(1,1);
        Tatuador tatuador1 = tatuadorService.getTatuador(1);
        Cabina cabina1 = cabinaService.getCabina(1);
        Cliente cliente1 = clienteService.getCliente(1);
        citaService.crearCita(Tipo.TATUAJE,"Nueva cita de testing",250.0f,20.0f,Estado.RESERVADA,tatuador1,null,cabina1, Set.of(hueco1),Set.of(cliente1));

        Cita citaRecuperada = citaService.getCitaByDescripcionCabina("Nueva cita de testing",1);
        assertEquals(Estado.RESERVADA,citaRecuperada.getEstado());
        assertEquals(250.0f,citaRecuperada.getPrecio());
        assertEquals(Tipo.TATUAJE,citaRecuperada.getTipo());
    }

    @Test
    @Order(16)
    public void test_deleteCitaTatuaje(){
        Cita citaTest = citaService.getCitaByDescripcionCabina("Nueva cita de testing",1);
        assertNotNull(citaTest);
        citaService.deleteCita(citaTest);

        Cita citaRecuperada = citaService.getCitaByDescripcionCabina("Nueva cita de testing",1);
        assertNull(citaRecuperada);
    }


}