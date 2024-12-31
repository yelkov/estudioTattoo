package edu.badpals.estudio.model.trabajador;

import java.util.List;
import java.util.Optional;

public class TatuadorService {
    private final TatuadorDAO tatuadorDAO;

    public TatuadorService(){
        tatuadorDAO = new TatuadorDAO();
    }

    public Tatuador getTatuador(Integer id){
        Optional<Tatuador> tatuador = tatuadorDAO.findById(id);
        return tatuador.isPresent() ? tatuador.get() : null;
    }

    public Tatuador getTatuadorByNif(String nif){
        Optional<Tatuador> tatuador = tatuadorDAO.findByNif(nif);
        return tatuador.isPresent() ? tatuador.get() : null;
    }

    public Tatuador getTatuadorByNss(String nss){
        Optional<Tatuador> tatuador = tatuadorDAO.findByNss(nss);
        return tatuador.isPresent() ? tatuador.get() : null;
    }

    public List<Tatuador> getAllTatuadores(){
        return tatuadorDAO.findAll();
    }

    public List<Tatuador> getTatuadoresStartByName(String name){
        return tatuadorDAO.findStartByName(name);
    }

    public List<Tatuador> getTatuadoresByNameContaining(String name){
        return tatuadorDAO.findByNameContaining(name);
    }
}
