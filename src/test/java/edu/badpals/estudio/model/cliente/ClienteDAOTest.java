package edu.badpals.estudio.model.cliente;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteDAOTest {
    private static ClienteDAO clienteDAO;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        clienteDAO = new ClienteDAO();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_findById(){
        Optional<Cliente> optionalCliente = clienteDAO.findById(1);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            assertEquals("Mipri Mer Cliente", cliente.getNombre());
            assertEquals("99228844M", cliente.getDni());
            assertEquals("611454545", cliente.getTelefono());
            assertEquals("Calle Larga 32", cliente.getDirección());
            assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
            assertNull(cliente.getInstagram());
            assertEquals("mipri.mc@gmail.com", cliente.getEmail());
        }
    }

    @Test
    @Order(2)
    public void test_findEmpty(){
        Optional<Cliente> optionalCliente = clienteDAO.findById(1000);
        assertTrue(optionalCliente.isEmpty());
    }

    @Test
    @Order(3)
    public void test_findByDni(){
        Optional<Cliente> optionalCliente = clienteDAO.findByDni("99228844M");
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            assertEquals("99228844M", cliente.getDni());
            assertEquals("Mipri Mer Cliente", cliente.getNombre());
            assertEquals("611454545", cliente.getTelefono());
            assertEquals("Calle Larga 32", cliente.getDirección());
            assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
            assertNull(cliente.getInstagram());
            assertEquals("mipri.mc@gmail.com", cliente.getEmail());
        }
    }

    @Test
    @Order(4)
    public void test_findByDniEmpty(){
        Optional<Cliente> optionalCliente = clienteDAO.findByDni("ABAJAJSJDH");
        assertTrue(optionalCliente.isEmpty());
    }

    @Test
    @Order(5)
    public void test_findByTelefono(){
        Optional<Cliente> optionalCliente = clienteDAO.findByTelefono("611454545");
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            assertEquals("99228844M", cliente.getDni());
            assertEquals("Mipri Mer Cliente", cliente.getNombre());
            assertEquals("611454545", cliente.getTelefono());
            assertEquals("Calle Larga 32", cliente.getDirección());
            assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
            assertNull(cliente.getInstagram());
            assertEquals("mipri.mc@gmail.com", cliente.getEmail());
        }
    }

    @Test
    @Order(6)
    public void test_findByTelefonoEmpty(){
        Optional<Cliente> optionalCliente = clienteDAO.findByTelefono("611111111");
        assertTrue(optionalCliente.isEmpty());
    }

    @Test
    @Order(7)
    public void test_findByNameContaining(){
        List<Cliente> barrosos = clienteDAO.findByNameContaining("Barroso");
        assertTrue(!barrosos.isEmpty());
        assertEquals(2, barrosos.size());
        assertEquals("Enric Barroso Prieto", barrosos.get(0).getNombre());
        assertEquals("Enriqueta Barroso Vazquez", barrosos.get(1).getNombre());
    }

    @Test
    @Order(8)
    public void test_findAll(){
        List<Cliente> clientes = clienteDAO.findAll();
        assertNotNull(clientes);
        assertEquals(14, clientes.size());
        assertEquals("Mipri Mer Cliente", clientes.get(0).getNombre());
    }

    @Test
    @Order(9)
    public void test_crearCliente(){
        Cliente cliente = new Cliente("42760148C","Antonio Delgado Zamora","768396885","Pedralbes 97, 34245, Carrión De Los Condes(palencia)",LocalDate.of(1974,10,21),null,"sm2c8i8uhs@yahoo.es",null,null,null);
        clienteDAO.create(cliente);

        Optional<Cliente> clienteOptional = clienteDAO.findByDni("42760148C");
        if(clienteOptional.isPresent()){
            Cliente antonio = clienteOptional.get();
            assertEquals("42760148C", antonio.getDni());
            assertEquals("Antonio Delgado Zamora", antonio.getNombre());
            assertEquals("768396885", antonio.getTelefono());
            assertEquals("Pedralbes 97, 34245, Carrión De Los Condes(palencia)",antonio.getDirección());
        }
    }

    @Test
    @Order(10)
    public void test_updateCliente(){
        Cliente antonio = clienteDAO.findByDni("42760148C").get();
        antonio.setTelefono("619858703");
        antonio.setDirección("Casa de antonio, Vigo");
        clienteDAO.update(antonio);

        Cliente antonioRecuperado = clienteDAO.findByDni("42760148C").get();
        assertEquals("619858703", antonioRecuperado.getTelefono());
        assertEquals("Casa de antonio, Vigo", antonioRecuperado.getDirección());
    }

    @Test
    @Order(11)
    public void test_deleteCliente(){
        Cliente antonio = clienteDAO.findByDni("42760148C").get();
        clienteDAO.delete(antonio);

        Optional<Cliente> clienteOptional = clienteDAO.findByDni("42760148C");
        assertTrue(clienteOptional.isEmpty());
    }


}