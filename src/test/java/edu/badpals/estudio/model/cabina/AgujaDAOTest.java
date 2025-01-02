package edu.badpals.estudio.model.cabina;
import edu.badpals.estudio.model.aguja.Aguja;
import edu.badpals.estudio.model.aguja.AgujaDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgujaDAOTest {

    private static AgujaDAO agujaDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        agujaDAO = new AgujaDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_find(){

        Optional<Aguja> optional_aguja1 = agujaDAO.findById(1);
        if(optional_aguja1.isPresent()){
            Aguja aguja1 = optional_aguja1.get();
            assertNotNull(aguja1);
            assertEquals(aguja1.getId(),1);
        }
    }
}
