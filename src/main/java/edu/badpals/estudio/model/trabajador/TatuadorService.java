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

    public Tatuador getTatuadorWithDiseños(Integer id){
        Optional<Tatuador> tatuador = tatuadorDAO.findWithDiseños(id);
        return tatuador.isPresent() ? tatuador.get() : null;
    }

    public void createTatuador(String nif, String name, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String mail, Float comision, Set<String> telefonos) throws IllegalArgumentException {
        if (!hasCamposObligatorios(nif, name, nss, fechaNacimiento, fechaAlta, mail)) {
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar");
        } else if (isPresentNifNss(nif, nss)) {
            throw new IllegalArgumentException("El nif o el nss ya existen en el registro");
        } else {
            Tatuador tatuador = new Tatuador(nif, name, nss, fechaNacimiento, fechaAlta, salario, mail, comision);
            if(telefonos != null){
                tatuador.setTelefonos(telefonos);
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

    public void updateTatuador(Tatuador tatuador,String newNif , String newNombre, String newNss, LocalDate newFechaNacimiento, LocalDate newFechaAlta, Float newSalario, String newMail,Set<String> newTelefonos ,Float newComision){
        if(!tatuadorDAO.findById(tatuador.getId()).isPresent()){
            throw new IllegalArgumentException("El tatuador no existe en el registro");
        }else{
            if(newNif != null && !tatuadorDAO.findByNif(newNif).isPresent()){
                tatuador.setNif(newNif);
            }else if(tatuadorDAO.findByNif(newNif).isPresent() && !newNif.equals(tatuador.getNif())){
                throw new IllegalArgumentException("El nuevo nif ya existe en el registro");
            }
            if(newNss != null && !tatuadorDAO.findByNss(newNss).isPresent()){
                tatuador.setNss(newNss);
            }else if(tatuadorDAO.findByNss(newNss).isPresent() && newNss != tatuador.getNss()){
                throw new IllegalArgumentException("El nuevo nss ya existe en el registro");
            }
            if(newNombre != null && !newNombre.isEmpty()){
                tatuador.setNombre(newNombre);
            }
            if(newFechaNacimiento != null){
                tatuador.setFechaNacimiento(newFechaNacimiento);
            }
            if(newFechaAlta != null){
                tatuador.setFechaAlta(newFechaAlta);
            }
            tatuador.setSalario(newSalario);
            if(newMail != null && !newMail.isEmpty()){
                tatuador.setEmail(newMail);
            }
            if(newTelefonos != null){
                tatuador.setTelefonos(newTelefonos);
            }
            if(newComision!= null){
                tatuador.setComision(newComision);
            }
            tatuadorDAO.update(tatuador);
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

    public void addDiseño(Integer id, String tag, byte[] diseño){
        if(id != null){
            Tatuador tatuador = getTatuadorWithDiseños(id);
            if(tatuador != null){
                tatuador.addDiseño(tag,diseño);
                tatuadorDAO.update(tatuador);
            }
        }
    }

    public void removeDiseño(Integer id, String tag){
        if(id != null){
            Tatuador tatuador = getTatuadorWithDiseños(id);
            if(tatuador != null){
                tatuador.removeDiseño(tag);
                tatuadorDAO.update(tatuador);
            }
        }
    }

    public void addDiseños(Integer id, Map<String,byte[]> diseños){
        if(id != null && diseños != null && !diseños.isEmpty()){
            Tatuador tatuador = getTatuadorWithDiseños(id);
            if(tatuador != null){
                for(Map.Entry<String,byte[]> entry : diseños.entrySet()){
                    tatuador.addDiseño(entry.getKey(),entry.getValue());
                }
                tatuadorDAO.update(tatuador);
            }
        }
    }

    public void removeDiseños(Integer id, String[] tags){
        if(id != null && tags != null){
            Tatuador tatuador = getTatuadorWithDiseños(id);
            if(tatuador != null){
                for(String tag : tags){
                    tatuador.removeDiseño(tag);
                }
                tatuadorDAO.update(tatuador);
            }
        }
    }
}
