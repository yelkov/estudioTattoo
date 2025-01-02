package edu.badpals.estudio.model.cabina;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CabinaService {
    private final CabinaDAO cabinaDAO;

    public CabinaService(CabinaDAO cabinaDAO) {
        this.cabinaDAO = cabinaDAO;
    }

    public Cabina getCabina(int id){
        Optional<Cabina> cabina = cabinaDAO.findById(id);
        return cabina.isPresent()? cabina.get() : null;
    }

    public Cabina getCabinaByUbicacion(String ubicacion){
        Optional<Cabina> cabina = cabinaDAO.findByUbicacion(ubicacion);
        return cabina.isPresent()? cabina.get() : null;
    }

    public List<Cabina> getAllCabinas(){
        return cabinaDAO.findAll();
    }

    public void createCabina(String ubicacion, Float superficie, Boolean puedeHacerPiercing){
        if (ubicacion == null || cabinaDAO.findByUbicacion(ubicacion).isPresent()) {
            throw new IllegalArgumentException();
        }
        else if (superficie == null || superficie < 0.0f) {
            throw new IllegalArgumentException();
        }else{
            Cabina nuevaCabina = new Cabina(ubicacion,superficie,puedeHacerPiercing);
            cabinaDAO.create(nuevaCabina);
        }
    }

    public void updateCabina(Cabina cabina, String newUbicacion, Float newSuperficie, Boolean newPuedeHacerPiercing){
        if (!cabinaDAO.findById(cabina.getId()).isPresent()) {
            throw new IllegalArgumentException("No se encuentra la cabina especificada.");
        }else{
            if(newUbicacion != null && !cabinaDAO.findByUbicacion(newUbicacion).isPresent()){
                cabina.setUbicacion(newUbicacion);
            } else if (cabinaDAO.findByUbicacion(newUbicacion).isPresent()) {
                throw new IllegalArgumentException("Ubicación ya en uso.");
            }
            if(newSuperficie != null){
                cabina.setSuperficie(newSuperficie);
            }
            if(newPuedeHacerPiercing != null){
                cabina.setPuedeHacerPiercing(newPuedeHacerPiercing);
            }
            cabinaDAO.update(cabina);
        }
    }

    public void deleteCabina(int id){
        Optional<Cabina> cabina = cabinaDAO.findById(id);
        if (!cabina.isPresent()) {
            throw new IllegalArgumentException();
        }else{
            cabinaDAO.delete(cabina.get());
        }
    }

    public void deleteCabina(String ubicacion){
        Optional<Cabina> cabinaOptional = cabinaDAO.findByUbicacion(ubicacion);
        if (!cabinaOptional.isPresent()) {
            throw new IllegalArgumentException();
        }else {
            cabinaDAO.delete(cabinaOptional.get());
        }
    }

    public long countCabinas(){
        return cabinaDAO.count();
    }

    public void crearHuecosSemanales(LocalDate lunesInicio,Integer numSemanas){
        if(lunesInicio == null ){
            throw new IllegalArgumentException("Se debe indicar lunes de inicio.");
        }
        if(lunesInicio.getDayOfWeek() != DayOfWeek.MONDAY){
            throw new IllegalArgumentException("Día de inicio debe ser un lunes.");
        }
        if(numSemanas == null || numSemanas <= 0 ){
            throw new IllegalArgumentException("El número de semanas debe ser mayor que 0.");
        }

        cabinaDAO.crearHuecosSemanales(lunesInicio,numSemanas);
    }

    public void eliminarHuecosVacios(){
        cabinaDAO.eliminarHuecosVacios();
    }


}
