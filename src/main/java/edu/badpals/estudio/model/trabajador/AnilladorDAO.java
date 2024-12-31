package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;

import java.util.List;
import java.util.Optional;

public class AnilladorDAO implements InterfaceDAO<Anillador> {
    private final TrabajadorGenericoDAO genericoDAO;

    public AnilladorDAO() {
        this.genericoDAO = new TrabajadorGenericoDAO<>(Anillador.class);
    }


    @Override
    public void create(Anillador anillador) {
        genericoDAO.create(anillador);
    }

    @Override
    public void update(Anillador anillador) {
        genericoDAO.update(anillador);
    }

    @Override
    public void delete(Anillador anillador) {
        genericoDAO.delete(anillador);
    }

    @Override
    public Optional<Anillador> findById(int id) {
        return genericoDAO.findById(id);
    }

    public List<Anillador> findAll() {
        return genericoDAO.findAll();
    }

    public Optional<Anillador> findByNif(String nif) {
        return genericoDAO.findByAttribute("nif", nif);
    }

    public Optional<Anillador> findByNss(String nss) {
        return genericoDAO.findByAttribute("nss", nss);
    }

    public List<Anillador> findStartByName(String nombre){
        return genericoDAO.findStartByName(nombre);
    }

    public List<Anillador> findByNameContaining(String nombre){
        return genericoDAO.findByNameContaining(nombre);
    }

}
