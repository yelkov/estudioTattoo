package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HuecoServiceTest {
    private static HuecoService huecoService;
    private static CabinaService cabinaService;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        huecoService = new HuecoService();
        cabinaService = new CabinaService();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getHueco(){
        Hueco hueco = huecoService.getHueco(1,1);
        assertNotNull(hueco);
        assertEquals(LocalTime.of(11,00,00),hueco.getHora());
        assertEquals(LocalDate.of(2024,12,16),hueco.getDia());
    }

    @Test
    @Order(2)
    public void test_getHuecoNull(){
        Hueco hueco = huecoService.getHueco(50000,1);
        assertNull(hueco);
    }

    @Test
    @Order(3)
    public void test_getHuecoNullNoId(){
        Hueco hueco = huecoService.getHueco(-1,1);
        assertNull(hueco);
    }

    @Test
    @Order(4)
    public void test_getHuecoNullNoCabina(){
        Hueco hueco = huecoService.getHueco(1,50);
        assertNull(hueco);
    }

    @Test
    @Order(5)
    public void test_getAllHuecos(){
        List<Hueco> huecos = huecoService.getAllHuecos();
        assertNotNull(huecos);
        assertEquals(216,huecos.size());
    }

    @Test
    @Order(6)
    public void test_getAllFromCabina(){
        Cabina cabina1 = cabinaService.getCabina(1);
        List<Hueco> huecosCabina1 = huecoService.getAllFromCabina(cabina1);
        assertNotNull(huecosCabina1);
        assertEquals(54,huecosCabina1.size());
    }

    @Test
    @Order(7)
    public void test_getAllFromCabinaNull(){
        Cabina cabina1 = cabinaService.getCabina(1000);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huecoService.getAllFromCabina(cabina1));

        Assertions.assertEquals("No existe la cabina indicada.",exception.getMessage());
    }

    @Test
    @Order(8)
    public void test_getHuecosLibres_1dia(){
        List<Hueco> huecosLibres = huecoService.getHuecosLibres(LocalDate.of(2024,12,16),LocalDate.of(2024,12,16));
        assertNotNull(huecosLibres);
        assertEquals(36,huecosLibres.size());
    }

    @Test
    @Order(9)
    public void test_getHuecosLibres_1dianull(){
        List<Hueco> huecosLibres = huecoService.getHuecosLibres(LocalDate.of(2024,12,16),null);
        assertNotNull(huecosLibres);
        assertEquals(36,huecosLibres.size());
    }

    @Test
    @Order(10)
    public void test_getHuecosLibres_2dias(){
        List<Hueco> huecosLibres = huecoService.getHuecosLibres(LocalDate.of(2024,12,16),LocalDate.of(2024,12,17));
        assertNotNull(huecosLibres);
        assertEquals(72,huecosLibres.size());
    }

    @Test
    @Order(11)
    public void test_getHuecosLibres_null(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huecoService.getHuecosLibres(null,LocalDate.of(2024,12,16)));

        Assertions.assertEquals("Es necesario indicar fecha de inicio.",exception.getMessage());
    }

    @Test
    @Order(12)
    public void test_getHuecosLibresCabina(){
        Cabina cabina1 = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabina1 = huecoService.getHuecosLibresCabina(cabina1,LocalDate.of(2024,12,16),LocalDate.of(2024,12,16));
        assertNotNull(huecosLibresCabina1);
        assertEquals(9,huecosLibresCabina1.size());
    }

    @Test
    @Order(13)
    public void test_getHuecosLibresCabinaNull(){
        Cabina cabina1 = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabina1 = huecoService.getHuecosLibresCabina(cabina1,LocalDate.of(2024,12,3),LocalDate.of(2024,12,3));
        assertNotNull(huecosLibresCabina1);
        assertEquals(0,huecosLibresCabina1.size());
    }

    @Test
    @Order(14)
    public void test_getHuecosLibresCabina2Dias(){
        Cabina cabina1 = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabina1 = huecoService.getHuecosLibresCabina(cabina1,LocalDate.of(2024,12,16),LocalDate.of(2024,12,17));
        assertNotNull(huecosLibresCabina1);
        assertEquals(18,huecosLibresCabina1.size());
    }

    @Test
    @Order(11)
    public void test_getHuecosLibresCabina_exception(){
        Cabina cabina1 = cabinaService.getCabina(1000);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huecoService.getHuecosLibresCabina(cabina1,LocalDate.of(2024,12,16),LocalDate.of(2024,12,16)));

        Assertions.assertEquals("No existe la cabina indicada.",exception.getMessage());
    }

    @Test
    @Order(11)
    public void test_getHuecosLibresCabina_exception2(){
        Cabina cabina1 = cabinaService.getCabina(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> huecoService.getHuecosLibresCabina(cabina1,null,LocalDate.of(2024,12,16)));

        Assertions.assertEquals("Es necesario indicar fecha de inicio.",exception.getMessage());
    }

}