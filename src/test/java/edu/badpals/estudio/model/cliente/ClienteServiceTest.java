package edu.badpals.estudio.model.cliente;

import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteServiceTest {
    private static ClienteService clienteService;

    @BeforeAll
    public static void setup(){
        EntityManagerFactoryProvider.initialize("test");
        clienteService = new ClienteService();
    }

    @AfterAll
    public static void close(){
        EntityManagerFactoryProvider.close();
    }

    @Test
    @Order(1)
    public void test_getCliente(){
        Cliente cliente = clienteService.getCliente(1);
        assertNotNull(cliente);
        assertEquals("Mipri Mer Cliente", cliente.getNombre());
        assertEquals("99228844M", cliente.getDni());
        assertEquals("611454545", cliente.getTelefono());
        assertEquals("Calle Larga 32", cliente.getDirección());
        assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
        assertNull(cliente.getInstagram());
        assertEquals("mipri.mc@gmail.com", cliente.getEmail());
    }

    @Test
    @Order(2)
    public void test_getClienteNull(){
        Cliente cliente = clienteService.getCliente(1000);
        assertNull(cliente);
    }

    @Test
    @Order(3)
    public void test_getClienteByDni(){
        Cliente cliente = clienteService.getClienteByDni("99228844M");
        assertNotNull(cliente);
        assertEquals("Mipri Mer Cliente", cliente.getNombre());
        assertEquals("99228844M", cliente.getDni());
        assertEquals("611454545", cliente.getTelefono());
        assertEquals("Calle Larga 32", cliente.getDirección());
        assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
        assertNull(cliente.getInstagram());
        assertEquals("mipri.mc@gmail.com", cliente.getEmail());
    }

    @Test
    @Order(4)
    public void test_getClienteByDniNull(){
        Cliente cliente = clienteService.getClienteByDni("ahusdfihadufih");
        assertNull(cliente);
    }

    @Test
    @Order(5)
    public void test_getClienteByTelefeno(){
        Cliente cliente = clienteService.getClienteByTelefono("611454545");
        assertNotNull(cliente);
        assertEquals("Mipri Mer Cliente", cliente.getNombre());
        assertEquals("99228844M", cliente.getDni());
        assertEquals("611454545", cliente.getTelefono());
        assertEquals("Calle Larga 32", cliente.getDirección());
        assertEquals(LocalDate.of(2000,7,7), cliente.getFechaNacimiento());
        assertNull(cliente.getInstagram());
        assertEquals("mipri.mc@gmail.com", cliente.getEmail());
    }

    @Test
    @Order(4)
    public void test_getClienteByTelefonoNull(){
        Cliente cliente = clienteService.getClienteByTelefono("ahusdfihadufih");
        assertNull(cliente);
    }

    @Test
    @Order(5)
    public void test_getAllClientes(){
        List<Cliente> clientes = clienteService.getAllClientes();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        assertEquals(14,clientes.size());
        assertEquals(clientes.get(0).getId(),1);
    }

    @Test
    @Order(6)
    public void test_getClientesByNameContaining(){
        List<Cliente> barrosos = clienteService.getClienteByNameContaining("Barroso");
        assertNotNull(barrosos);
        assertEquals(2, barrosos.size());
        assertEquals("Enric Barroso Prieto", barrosos.get(0).getNombre());
        assertEquals("Enriqueta Barroso Vazquez", barrosos.get(1).getNombre());
    }

    @Test
    @Order(7)
    public void test_crearCliente(){
        Cliente mipri = clienteService.getCliente(1);
        clienteService.crearCliente("42760148C","Antonio Delgado Zamora","768396885","Pedralbes 97, 34245, Carrión De Los Condes(palencia)",LocalDate.of(1974,10,21),null,"sm2c8i8uhs@yahoo.es",mipri,Parentesco.OTRO,null);

        Cliente antonio = clienteService.getClienteByDni("42760148C");
        assertEquals("42760148C", antonio.getDni());
        assertEquals("Antonio Delgado Zamora", antonio.getNombre());
        assertEquals("768396885", antonio.getTelefono());
        assertEquals("Pedralbes 97, 34245, Carrión De Los Condes(palencia)",antonio.getDirección());
    }

    @Test
    @Order(8)
    public void test_createClienteFalse(){
        Cliente mipri = clienteService.getCliente(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.crearCliente(null,"Antonio Delgado Zamora",null,"Pedralbes 97, 34245, Carrión De Los Condes(palencia)",LocalDate.of(1974,10,21),null,"sm2c8i8uhs@yahoo.es",mipri,Parentesco.OTRO,null));

        Assertions.assertEquals("Hay campos obligatorios sin rellenar.",exception.getMessage());
    }

    @Test
    @Order(9)
    public void test_createClienteFalse2(){
        Cliente mipri = clienteService.getCliente(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.crearCliente("42760148C","Antonio Delgado Zamora","600100100","Pedralbes 97, 34245, Carrión De Los Condes(palencia)",LocalDate.of(1974,10,21),null,"sm2c8i8uhs@yahoo.es",mipri,Parentesco.OTRO,null));

        Assertions.assertEquals("El dni o el teléfono ya existen en el registro.",exception.getMessage());
    }

    @Test
    @Order(10)
    public void test_createClienteFalse3(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.crearCliente("43604217N","Antonio Menor Edad","600100100","Pedralbes 97, 34245, Carrión De Los Condes(palencia)",LocalDate.of(2010,10,21),null,"sm2c8i8uhs@yahoo.es",null,null,null));

        Assertions.assertEquals("Si el cliente es menor de edad es necesario seleccionar un tutor y su parentesco.",exception.getMessage());
    }

    @Test
    @Order(11)
    public void test_updateCliente(){
        Cliente antonio = clienteService.getClienteByDni("42760148C");
        clienteService.updateCliente(antonio,null,"Antonio Cambiado", "700007007","Una calle nueva", null,null,null,null,null);

        Cliente antonioUpdated = clienteService.getClienteByDni("42760148C");
        assertEquals("Antonio Cambiado", antonioUpdated.getNombre());
        assertEquals("700007007", antonioUpdated.getTelefono());
        assertEquals("Una calle nueva", antonioUpdated.getDirección());
    }

    @Test
    @Order(12)
    public void test_deleteCliente(){
        clienteService.deleteCliente(null,"42760148C",null);

        Cliente antonio = clienteService.getClienteByDni("42760148C");
        assertNull(antonio);

    }
}