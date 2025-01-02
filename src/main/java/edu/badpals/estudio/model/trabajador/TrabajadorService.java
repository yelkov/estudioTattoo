package edu.badpals.estudio.model.trabajador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TrabajadorService {
    private final TrabajadorDAO trabajadorDAO;

    public TrabajadorService() {
        this.trabajadorDAO = new TrabajadorDAO();
    }

    public Trabajador getTrabajador(int id) {
        Optional<Trabajador> trabajador = trabajadorDAO.findById(id);
        return trabajador.isPresent() ? trabajador.get() : null;
    }

    public Trabajador getTrabajadorByNif(String nif) {
        Optional<Trabajador> trabajador = trabajadorDAO.findByNif(nif);
        return trabajador.isPresent() ? trabajador.get() : null;
    }

    public Trabajador getTrabajadorByNss(String nss) {
        Optional<Trabajador> trabajador = trabajadorDAO.findByNss(nss);
        return trabajador.isPresent() ? trabajador.get() : null;
    }

    public List<Trabajador> getAllTrabajadores(){
        return trabajadorDAO.findAll();
    }

    public List<Trabajador> getTrabajadoresStartByName(String name){
        return trabajadorDAO.findStartByName(name);
    }

    public List<Trabajador> getTrabajadoresByNameContaining(String name){
        return trabajadorDAO.findByNameContaining(name);
    }


    public void createTrabajador(String nif, String name, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String mail, List<String> telefonos) throws IllegalArgumentException {
        if (!hasCamposObligatorios(nif, name, nss, fechaNacimiento, fechaAlta, mail)) {
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar");
        } else if (isPresentNifNss(nif, nss)) {
            throw new IllegalArgumentException("El nif o el nss ya existen en el registro");
        } else {
            Trabajador trabajador = new Trabajador(nif, name, nss, fechaNacimiento, fechaAlta, salario, mail);
            addTelefonos(trabajador, telefonos);
            trabajadorDAO.create(trabajador);
        }
        }


        private void addTelefonos (Trabajador trabajador, List <String> telefonos){
            if (telefonos != null && !telefonos.isEmpty()) {
                for (String telefono : telefonos) {
                    trabajador.addTelefono(telefono);
                }
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
            if (trabajadorDAO.findByNif(nif).isPresent()) {
                return true;
            } else if (trabajadorDAO.findByNss(nss).isPresent()) {
                return true;
            } else {
                return false;
            }
        }

        public void deleteTrabajador(Integer id, String nif, String nss){
        if (id != null && trabajadorDAO.findById(id).isPresent()) {
            trabajadorDAO.delete(trabajadorDAO.findById(id).get());
        }else if(nif != null && trabajadorDAO.findByNif(nif).isPresent()){
            trabajadorDAO.delete(trabajadorDAO.findByNif(nif).get());
        }else if(nss != null && trabajadorDAO.findByNss(nss).isPresent()){
            trabajadorDAO.delete(trabajadorDAO.findByNss(nss).get());
        }else{
            throw new IllegalArgumentException("No se encuentra el trabajador en el registro");
        }
        }

        public void updateTrabajador(Trabajador trabajador, String newNif, String newNombre,String newNss, LocalDate newFechaNacimiento, LocalDate newFechaAlta,Float newSalario, String newMail, Set<String> newTelefonos) throws IllegalArgumentException {

            if(!trabajadorDAO.findById(trabajador.getId()).isPresent()){
                throw new IllegalArgumentException("El trabajador no existe en el registro");
            }else{
                if (newNif != null && !trabajadorDAO.findByNif(newNif).isPresent()){
                    trabajador.setNif(newNif);
                } else if (trabajadorDAO.findByNif(newNif).isPresent() && !newNif.equals(trabajador.getNif())) {
                    throw new IllegalArgumentException("El nuevo nif ya existe en el registro");
                }
                if(newNss != null && !trabajadorDAO.findByNss(newNss).isPresent()){
                    trabajador.setNss(newNss);
                }else if(trabajadorDAO.findByNss(newNss).isPresent()  && !newNss.equals(trabajador.getNss())){
                    throw new IllegalArgumentException("El nuevo nss ya existe en el registro");
                }
                if(newNombre != null && !newNombre.isEmpty()){
                    trabajador.setNombre(newNombre);
                }
                if(newFechaNacimiento != null){
                    trabajador.setFechaNacimiento(newFechaNacimiento);
                }
                if(newFechaAlta != null){
                    trabajador.setFechaAlta(newFechaAlta);
                }
                trabajador.setSalario(newSalario);

                if(newMail != null && !newMail.isEmpty()){
                    trabajador.setEmail(newMail);
                }
                if(newTelefonos != null){
                    trabajador.setTelefonos(newTelefonos);
                }
                trabajadorDAO.update(trabajador);
            }

        }
    }