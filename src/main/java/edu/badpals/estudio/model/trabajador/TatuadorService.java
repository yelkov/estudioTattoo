package edu.badpals.estudio.model.trabajador;

import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    public void createTatuador(String nif, String name, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String mail, Float comision, Set<String> telefonos, Map<String, Integer> tintas_propias) throws IllegalArgumentException {
        if (!hasCamposObligatorios(nif, name, nss, fechaNacimiento, fechaAlta, mail)) {
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar");
        } else if (isPresentNifNss(nif, nss)) {
            throw new IllegalArgumentException("El nif o el nss ya existen en el registro");
        } else {
            Tatuador tatuador = new Tatuador(nif, name, nss, fechaNacimiento, fechaAlta, salario, mail, comision);
            if(telefonos != null){
                tatuador.setTelefonos(telefonos);
            }
            if(tintas_propias != null){
                tatuador.setTintas_propias(tintas_propias);
            }
            tatuadorDAO.create(tatuador);
        }
    }
    

    private Boolean hasCamposObligatorios (String nif, String name, String nss, LocalDate fechaNacimiento, LocalDate
            fechaAlta, String mail){
        if (nif == null || name == null || nss == null || fechaNacimiento == null || fechaAlta == null || mail == null) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean isPresentNifNss (String nif, String nss){
        if (tatuadorDAO.findByNif(nif).isPresent()) {
            return true;
        } else if (tatuadorDAO.findByNss(nss).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteTatuador(Integer id, String nif, String nss){
        if (id != null && tatuadorDAO.findById(id).isPresent()) {
            tatuadorDAO.delete(tatuadorDAO.findById(id).get());
        }else if(nif != null && tatuadorDAO.findByNif(nif).isPresent()){
            tatuadorDAO.delete(tatuadorDAO.findByNif(nif).get());
        }else if(nss != null && tatuadorDAO.findByNss(nss).isPresent()){
            tatuadorDAO.delete(tatuadorDAO.findByNss(nss).get());
        }else{
            throw new IllegalArgumentException("No se encuentra el tatuador en el registro");
        }
    }
}
