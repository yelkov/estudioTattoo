package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;
import edu.badpals.estudio.model.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrabajadorDAO implements InterfaceDAO<Trabajador> {
    private final TrabajadorGenericoDAO generico;

    public TrabajadorDAO() {
        this.generico = new TrabajadorGenericoDAO(Trabajador.class);
    }


    @Override
    public void create(Trabajador trabajador) {
        generico.create(trabajador);
    }

    @Override
    public void update(Trabajador trabajador) {
        generico.update(trabajador);
    }

    @Override
    public void delete(Trabajador trabajador) {
        generico.delete(trabajador);
    }

    @Override
    public Optional<Trabajador> findById(int id) {
        return generico.findById(id);
    }

    public List<Trabajador> findAll() {
        return generico.findAll();
    }

    public Optional<Trabajador> findByNif(String nif) {
        return generico.findByAttribute("nif", nif);
    }

    public Optional<Trabajador> findByNss(String nss) {
        return generico.findByAttribute("nss", nss);
    }

    public List<Trabajador> findStartByName(String nombre){
        return generico.findStartByName(nombre);
    }

    public List<Trabajador> findByNameContaining(String nombre){
        return generico.findByNameContaining(nombre);
    }
}
