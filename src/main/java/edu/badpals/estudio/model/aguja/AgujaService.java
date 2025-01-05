package edu.badpals.estudio.model.aguja;

import java.util.List;
import java.util.Optional;

public class AgujaService {

    private final AgujaDAO agujaDAO;

    public AgujaService() {
        this.agujaDAO = new AgujaDAO();
    }

    public Aguja getAguja(int id){

        Optional<Aguja> aguja = agujaDAO.findById(id);
        return aguja.isPresent()? aguja.get() : null;
    }

    public Aguja getAgujaByTipo(String tipo){
        Optional<Aguja> aguja = agujaDAO.findByTipo(tipo);
        return aguja.isPresent()? aguja.get() : null;
    }

    public List<Aguja> filtrarAgujaByTipo(String tipo){
        return agujaDAO.filtrarByTipo(tipo);
    }

    public List<Aguja> getAllAgujas(){
        return agujaDAO.findAll();
    }

    public void createAguja(String tipo, long cantidad){

        Aguja nuevaAguja = new Aguja(tipo, cantidad);
        agujaDAO.create(nuevaAguja);

    }

    public void updateAguja(Aguja aguja, String nuevoTipo, long nuevaCantidad){

        Aguja updatedAguja = aguja;

        if (!agujaDAO.findById(aguja.getId()).isPresent()) {
            throw new IllegalArgumentException("No se encuentra la aguja especificada.");

        } else {
            aguja.setTipo(nuevoTipo);
            aguja.setCantidad(nuevaCantidad);
            agujaDAO.update(aguja);
        }

    }

    public void deleteAguja(int id){
        Optional<Aguja> aguja = agujaDAO.findById(id);
        if (!aguja.isPresent()) {
            throw new IllegalArgumentException();
        }else{
            agujaDAO.delete(aguja.get());
        }
    }

}
