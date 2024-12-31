package edu.badpals.estudio.model.trabajador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AnilladorService {
    private final AnilladorDAO anilladorDAO;

    public AnilladorService(){
        this.anilladorDAO = new AnilladorDAO();
    }

    public Anillador getAnillador(Integer id){
        Optional<Anillador> anillador = anilladorDAO.findById(id);
        return anillador.isPresent() ? anillador.get() : null;
    }

    public Anillador getAnilladorByNif(String nif){
        Optional<Anillador> anillador = anilladorDAO.findByNif(nif);
        return anillador.isPresent() ? anillador.get() : null;
    }

    public Anillador getAnilladorByNss(String nss){
        Optional<Anillador> anillador = anilladorDAO.findByNss(nss);
        return anillador.isPresent() ? anillador.get() : null;
    }

    public List<Anillador> getAllAnilladores(){
        return anilladorDAO.findAll();
    }

    public List<Anillador> getAnilladoresStartByName(String name){
        return anilladorDAO.findStartByName(name);
    }

    public List<Anillador> getAnilladoresByNameContaining(String name){
        return anilladorDAO.findByNameContaining(name);
    }

    public void createAnillador(String nif, String name, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String mail, Float comision, Set<String> telefonos, Map<String, Integer> piezas_propias) throws IllegalArgumentException {
        if (!hasCamposObligatorios(nif, name, nss, fechaNacimiento, fechaAlta, mail)) {
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar");
        } else if (isPresentNifNss(nif, nss)) {
            throw new IllegalArgumentException("El nif o el nss ya existen en el registro");
        } else {
            Anillador anillador = new Anillador(nif, name, nss, fechaNacimiento, fechaAlta, salario, mail, comision);
            if(telefonos != null){
                anillador.setTelefonos(telefonos);
            }
            if(piezas_propias != null){
                anillador.setPiezas_propias(piezas_propias);
            }
            anilladorDAO.create(anillador);
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
        if (anilladorDAO.findByNif(nif).isPresent()) {
            return true;
        } else if (anilladorDAO.findByNss(nss).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteAnillador(Integer id, String nif, String nss){
        if (id != null && anilladorDAO.findById(id).isPresent()) {
            anilladorDAO.delete(anilladorDAO.findById(id).get());
        }else if(nif != null && anilladorDAO.findByNif(nif).isPresent()){
            anilladorDAO.delete(anilladorDAO.findByNif(nif).get());
        }else if(nss != null && anilladorDAO.findByNss(nss).isPresent()){
            anilladorDAO.delete(anilladorDAO.findByNss(nss).get());
        }else{
            throw new IllegalArgumentException("No se encuentra el anillador en el registro");
        }
    }

}
