package edu.badpals.estudio.model.cita;


import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.Tatuador;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CitaService {

    private final CitaDAO citaDAO;

    public CitaService(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }

    public void create(Tipo tipo, String descripcion, Float precio, Float señal, Estado estado, Tatuador tatuador, Anillador anillador, Cabina cabina, Set<Hueco> huecos, Set<Cliente> clientes) {

        if (citaDAO.findTatuador(tatuador.getId()).isPresent() && citaDAO.findAnillador(anillador.getId()).isPresent() && citaDAO.findCabina(cabina.getId()).isPresent()) {

            Cita nuevaCita = new Cita(tipo, descripcion, precio, señal, estado, tatuador, anillador, cabina, huecos, clientes);

            citaDAO.create(nuevaCita);

        } else {
            throw new IllegalArgumentException("No se encuentra el tatuador, anillador o cabina especificados.");
        }


    }

    public void delete(Cita cita) {
        citaDAO.delete(cita);
    }

    public void update(Cita cita, Tipo tipo, String descripcion, Float precio, Float señal, Estado estado, Tatuador tatuador, Anillador anillador, Cabina cabina, Set<Hueco> huecos, Set<Cliente> clientes) {

        if (citaDAO.findTatuador(tatuador.getId()).isPresent() && citaDAO.findAnillador(anillador.getId()).isPresent() && citaDAO.findCabina(cabina.getId()).isPresent()) {

            cita.setTipo(tipo);
            cita.setDescripcion(descripcion);
            cita.setPrecio(precio);
            cita.setSeñal(señal);
            cita.setEstado(estado);
            cita.setTatuador(tatuador);
            cita.setAnillador(anillador);
            cita.setCabina(cabina);
            cita.setHuecos(huecos);
            cita.setClientes(clientes);

            citaDAO.update(cita);

        } else {
            throw new IllegalArgumentException("No se encuentra el tatuador, anillador o cabina especificados, no se puede actualizar la cita.");
        }


    }

    public Optional<Cita> findById(int i) {
        return citaDAO.findById(i);
    }

    public List<Cita> findAll() {
        return citaDAO.findAll();
    }

    public List<Cita> findByTipo(Tipo tipo) {
        return citaDAO.findByTipo(tipo);
    }

    public List<Cita> findByAnillador(int id) {

        if (citaDAO.findAnillador(id).isPresent()) {
            return citaDAO.findByAnillador(id);
        } else {
            throw new IllegalArgumentException("No se encuentra el anillador especificado.");
        }

    }

    public List<Cita> findByTatuador(int id) {

        if (citaDAO.findTatuador(id).isPresent()) {
            return citaDAO.findByTatuador(id);
        } else {
            throw new IllegalArgumentException("No se encuentra el tatuador especificado.");
        }
    }



}
