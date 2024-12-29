package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class TatuadorDAOTest {

    private static TatuadorDAO tatuadorDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        tatuadorDAO = new TatuadorDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

}