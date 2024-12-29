package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;

import java.util.List;
import java.util.Optional;

public class AnilladorDAO implements InterfaceDAO<Anillador> {
    private final TrabajadorGenericoDAO generico;

    public AnilladorDAO() {
        this.generico = new TrabajadorGenericoDAO<>(Anillador.class);
    }


    @Override
    public void create(Anillador anillador) {
        generico.create(anillador);
    }

    @Override
    public void update(Anillador anillador) {
        generico.update(anillador);
    }

    @Override
    public void delete(Anillador anillador) {
        generico.delete(anillador);
    }

    @Override
    public Optional<Anillador> findById(int id) {
        return generico.findById(id);
    }

    public List<Anillador> findAll() {
        return generico.findAll();
    }

    public Optional<Anillador> findByNif(String nif) {
        return generico.findByAttribute("nif", nif);
    }

    public Optional<Anillador> findByNss(String nss) {
        return generico.findByAttribute("nss", nss);
    }

    public List<Anillador> findStartByName(String nombre){
        return generico.findStartByName(nombre);
    }

    public List<Anillador> findByNameContaining(String nombre){
        return generico.findByNameContaining(nombre);
    }

}
