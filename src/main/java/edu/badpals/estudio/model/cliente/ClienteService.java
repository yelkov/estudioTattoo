package edu.badpals.estudio.model.cliente;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService(){
        clienteDAO = new ClienteDAO();
    }

    public Cliente getCliente(Integer id){
        Optional<Cliente> cliente = clienteDAO.findById(id);
        return cliente.isPresent() ? cliente.get() : null;
    }

    public Cliente getClienteByDni(String dni){
        Optional<Cliente> cliente = clienteDAO.findByDni(dni);
        return cliente.isPresent() ? cliente.get() : null;
    }

    public Cliente getClienteByTelefono(String telefono){
        Optional<Cliente> cliente = clienteDAO.findByTelefono(telefono);
        return cliente.isPresent() ? cliente.get() : null;
    }

    public List<Cliente> getAllClientes(){
        return clienteDAO.findAll();
    }

    public List<Cliente> getClienteByNameContaining(String nombre){
        return clienteDAO.findByNameContaining(nombre);
    }

    public void crearCliente(String dni,String nombre,String telefono, String direccion, LocalDate fechaNacimiento, String instagram, String email, Cliente tutor, Parentesco parentesco, Set<String> alergias) throws IllegalArgumentException{
        if(!hasCamposObligatorios(dni,nombre,telefono,direccion,fechaNacimiento)){
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar.");
        }else if(isPresentDniTelefono(dni,telefono)){
            throw new IllegalArgumentException("El dni o el teléfono ya existen en el registro.");
        }else if(!esMayorEdad(fechaNacimiento) && (tutor == null || parentesco == null)){
            throw new IllegalArgumentException("Si el cliente es menor de edad es necesario seleccionar un tutor y su parentesco.");
        }else{
            Cliente cliente = new Cliente(dni,nombre,telefono,direccion,fechaNacimiento,instagram,email,tutor,parentesco,alergias);
            clienteDAO.create(cliente);
        }

    }

    private Boolean hasCamposObligatorios(String dni, String nombre, String telefono, String direccion, LocalDate fechaNacimiento){
        if (dni == null || nombre == null || telefono == null || direccion == null || fechaNacimiento == null){
            return false;
        }else {
            return true;
        }
    }

    private Boolean isPresentDniTelefono(String dni, String telefono){
        if(clienteDAO.findByDni(dni).isPresent()){
            return true;
        } else if (clienteDAO.findByTelefono(telefono).isPresent()) {
            return true;
        } else{
            return false;
        }
    }

    private Boolean esMayorEdad(LocalDate fechaNacimiento){
        return fechaNacimiento != null? Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18
                : true;
    }

    public void deleteCliente(Integer id, String dni, String telefono){
        if(id != null && clienteDAO.findById(id).isPresent()){
            clienteDAO.delete(clienteDAO.findById(id).get());
        }else if(dni != null && clienteDAO.findByDni(dni).isPresent()){
            clienteDAO.delete(clienteDAO.findByDni(dni).get());
        }else if(telefono != null && clienteDAO.findByTelefono(telefono).isPresent()){
            clienteDAO.delete(clienteDAO.findByTelefono(telefono).get());
        }else{
            throw new IllegalArgumentException("No se encuentra al cliente en el registro.");
        }
    }

    public void updateCliente(Cliente cliente, String newDni, String newNombre, String newTelefono, String newDireccion, LocalDate newFechaNacimiento, String newInstagram, String newEmail, Cliente newTutor, Parentesco newParentesco) throws IllegalArgumentException{
        if(!clienteDAO.findById(cliente.getId()).isPresent()){
            throw new IllegalArgumentException("El cliente no existe en el registro.");
        }else{
            if(newDni != null && !clienteDAO.findByDni(newDni).isPresent()){
                cliente.setDni(newDni);
            }else if(clienteDAO.findByDni(newDni).isPresent()){
                throw new IllegalArgumentException("El nuevo dni ya existe en el registro.");
            }
            if(newTelefono != null && !clienteDAO.findByTelefono(newTelefono).isPresent()){
                cliente.setTelefono(newTelefono);
            }else if(clienteDAO.findByTelefono(newTelefono).isPresent()){
                throw new IllegalArgumentException("El telefono ya existe en el registro.");
            }

            boolean clienteEsMenor = !esMayorEdad(cliente.getFechaNacimiento());
            boolean newFechaEsMenor = newFechaNacimiento != null && !esMayorEdad(newFechaNacimiento);
            boolean faltaTutor = newTutor == null || newParentesco == null;
            if((clienteEsMenor || newFechaEsMenor) && faltaTutor){
                throw new IllegalArgumentException("Si el cliente es menor de edad es necesario seleccionar un tutor y su parentesco.");
            }

            if(newNombre != null && !newNombre.isEmpty()){
                cliente.setNombre(newNombre);
            }
            if(newDireccion != null && !newDireccion.isEmpty()){
                cliente.setDirección(newDireccion);
            }
            if(newFechaNacimiento != null){
                cliente.setFechaNacimiento(newFechaNacimiento);
            }
            if(newInstagram != null && !newInstagram.isEmpty()){
                cliente.setInstagram(newInstagram);
            }
            if(newEmail != null && !newEmail.isEmpty()){
                cliente.setEmail(newEmail);
            }
            if(newTutor != null && newParentesco != null){
                cliente.setTutor(newTutor);
                cliente.setParentesco(newParentesco);
            }
            clienteDAO.update(cliente);
        }
    }
}
