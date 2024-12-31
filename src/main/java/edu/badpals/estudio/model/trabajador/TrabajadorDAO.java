package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;

import java.util.List;
import java.util.Optional;

public class TrabajadorDAO implements InterfaceDAO<Trabajador> {
    private final TrabajadorGenericoDAO genericoDAO;

    public TrabajadorDAO() {
        this.genericoDAO = new TrabajadorGenericoDAO(Trabajador.class);
    }


    @Override
    public void create(Trabajador trabajador) {
        genericoDAO.create(trabajador);
    }

    @Override
    public void update(Trabajador trabajador) {
        genericoDAO.update(trabajador);
    }

    @Override
    public void delete(Trabajador trabajador) {
        genericoDAO.delete(trabajador);
    }

    @Override
    public Optional<Trabajador> findById(int id) {
        return genericoDAO.findById(id);
    }

    public List<Trabajador> findAll() {
        return genericoDAO.findAll();
    }

    public Optional<Trabajador> findByNif(String nif) {
        return genericoDAO.findByAttribute("nif", nif);
    }

    public Optional<Trabajador> findByNss(String nss) {
        return genericoDAO.findByAttribute("nss", nss);
    }

    public List<Trabajador> findStartByName(String nombre){
        return genericoDAO.findStartByName(nombre);
    }

    public List<Trabajador> findByNameContaining(String nombre){
        return genericoDAO.findByNameContaining(nombre);
    }
}
