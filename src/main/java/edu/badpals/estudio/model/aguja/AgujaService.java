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

    public void crearAguja(String tipo, long cantidad){
        if(tipo == null || tipo.isEmpty()){
            throw new IllegalArgumentException("Es necesario indicar el tipo de aguja.");
        }else if(agujaDAO.findByTipo(tipo).isPresent()){
            throw new IllegalArgumentException("El tipo de aguja ya existe.");
        }else if (cantidad < 0){
            throw new IllegalArgumentException("La cantidad de agujas debe ser igual o mayor que 0.");
        }

        Aguja nuevaAguja = new Aguja(tipo, cantidad);
        agujaDAO.create(nuevaAguja);

    }

    public void updateAguja(Aguja aguja, String nuevoTipo, Long nuevaCantidad){

        if (!agujaDAO.findById(aguja.getId()).isPresent()){
            throw new IllegalArgumentException("La aguja indicada no existe.");
        }
        if(nuevoTipo != null && !agujaDAO.findByTipo(nuevoTipo).isPresent()){
            aguja.setTipo(nuevoTipo);
        }else if(agujaDAO.findByTipo(nuevoTipo).isPresent() && !nuevoTipo.equals(aguja.getTipo())){
            throw new IllegalArgumentException("El tipo de aguja ya existe.");
        }
        if (nuevaCantidad < 0){
            throw new IllegalArgumentException("La cantidad de agujas debe ser mayor o igual que 0.");
        }else if(nuevaCantidad != null){
            aguja.setCantidad(nuevaCantidad);
        }
        agujaDAO.update(aguja);
    }

    public void deleteAguja(Integer id, String tipo){
        if(id != null && agujaDAO.findById(id).isPresent()){
            agujaDAO.delete(agujaDAO.findById(id).get());
        }else if(tipo != null && agujaDAO.findByTipo(tipo).isPresent()){
            agujaDAO.delete(agujaDAO.findByTipo(tipo).get());
        }else{
            throw new IllegalArgumentException("La aguja indicada no existe.");
        }
    }

}
