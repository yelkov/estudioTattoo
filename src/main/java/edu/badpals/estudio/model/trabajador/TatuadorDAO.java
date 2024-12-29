package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.InterfaceDAO;
import java.util.List;
import java.util.Optional;

public class TatuadorDAO implements InterfaceDAO<Tatuador> {
    private final TrabajadorGenericoDAO generico;

    public TatuadorDAO() {
        this.generico = new TrabajadorGenericoDAO(Tatuador.class);
    }

    @Override
    public void create(Tatuador tatuador) {
        generico.create(tatuador);
    }

    @Override
    public void update(Tatuador tatuador) {
        generico.update(tatuador);
    }

    @Override
    public void delete(Tatuador tatuador) {
        generico.delete(tatuador);
    }

    @Override
    public Optional<Tatuador> findById(int id) {
        return generico.findById(id);
    }

    public List<Tatuador> findAll() {
        return generico.findAll();
    }

    public Optional<Tatuador> findByNif(String nif) {
        return generico.findByAttribute("nif",nif);
    }

    public Optional<Tatuador> findByNss(String nss) {
        return generico.findByAttribute("nss", nss);
    }
    public List<Tatuador> findStartByName(String nombre){
        return generico.findStartByName(nombre);
    }

    public List<Tatuador> findByNameContaining(String nombre){
        return generico.findByNameContaining(nombre);
    }
}
