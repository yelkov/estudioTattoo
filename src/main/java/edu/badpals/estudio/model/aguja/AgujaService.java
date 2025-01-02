package edu.badpals.estudio.model.aguja;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaDAO;

import java.util.List;
import java.util.Optional;

public class AgujaService {

    private final AgujaDAO agujaDAO;

    public AgujaService(AgujaDAO agujaDAO) {
        this.agujaDAO = agujaDAO;
    }

    public Aguja getAguja(int id){

        Optional<Aguja> aguja = agujaDAO.findById(id);
        return aguja.isPresent()? aguja.get() : null;
    }

    public List<Aguja> getAllCabinas(){
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
