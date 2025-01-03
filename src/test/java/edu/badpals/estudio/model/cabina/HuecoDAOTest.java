package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HuecoDAOTest {
    private static CabinaService cabinaService;
    private static HuecoDAO huecoDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        cabinaService = new CabinaService();
        huecoDAO = new HuecoDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){
        Cabina cabina = cabinaService.getCabina(1);
        Integer numero = 1;
        HuecoId id = new HuecoId(numero,cabina.getId());
        Optional<Hueco> huecoOptional = huecoDAO.findById(id);
        assertTrue(huecoOptional.isPresent());
        Hueco hueco = huecoOptional.get();
        assertEquals(hueco.getDia(), LocalDate.of(2024,12,16));
    }

    @Test
    @Order(2)
    public void test_findAllFromCabina(){
        Cabina cabina = cabinaService.getCabina(1);
        List<Hueco> huecosCabina1 = huecoDAO.findAllFromCabina(cabina);

        assertNotNull(huecosCabina1);
        assertEquals(54, huecosCabina1.size());
    }

    @Test
    @Order(3)
    public void test_findHuecosLibres(){
        List<Hueco> huecosLibresDia16 = huecoDAO.findHuecosLibres(LocalDate.of(2024,12,16),LocalDate.of(2024,12,16));
        assertNotNull(huecosLibresDia16);
        assertEquals(36, huecosLibresDia16.size());
    }

    @Test
    @Order(4)
    public void test_findHuecoLibresNull(){
        List<Hueco> huecosLibresDia3 = huecoDAO.findHuecosLibres(LocalDate.of(2023,12,3),LocalDate.of(2023,12,3));
        assertTrue(huecosLibresDia3.isEmpty());
    }

    @Test
    @Order(5)
    public void test_findHuecosLibresRango(){
        List<Hueco> huecosLibresDia16_18 = huecoDAO.findHuecosLibres(LocalDate.of(2024,12,16),LocalDate.of(2024,12,18));
        assertNotNull(huecosLibresDia16_18);
        assertEquals(108, huecosLibresDia16_18.size());
    }

    @Test
    @Order(6)
    public void test_findHuecosLibresCabina(){
        Cabina cabina = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabinaDia16 = huecoDAO.findHuecosLibresCabina(cabina,LocalDate.of(2024,12,16),LocalDate.of(2024,12,16));

        assertNotNull(huecosLibresCabinaDia16);
        assertEquals(9, huecosLibresCabinaDia16.size());
    }

    @Test
    @Order(7)
    public void test_findHuecosLibresCabinaNull(){
        Cabina cabina = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabinaDia3 = huecoDAO.findHuecosLibresCabina(cabina,LocalDate.of(2024,12,3),LocalDate.of(2024,12,3));

        assertTrue(huecosLibresCabinaDia3.isEmpty());

    }

    @Test
    @Order(8)
    public void test_findHuecosLibresCabinaRango(){
        Cabina cabina = cabinaService.getCabina(1);
        List<Hueco> huecosLibresCabinaDia16 = huecoDAO.findHuecosLibresCabina(cabina,LocalDate.of(2024,12,16),LocalDate.of(2024,12,18));

        assertNotNull(huecosLibresCabinaDia16);
        assertEquals(27, huecosLibresCabinaDia16.size());
    }
}