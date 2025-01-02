package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
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

}