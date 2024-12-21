package edu.badpals.estudio.model.cabina;

import java.util.Optional;

public class CabinaService {
    private final CabinaDAO cabinaDAO;

    public CabinaService(CabinaDAO cabinaDAO) {
        this.cabinaDAO = cabinaDAO;
    }

    public Cabina getCabina(int id){
        Optional<Cabina> cabina = cabinaDAO.findById(id);
        return cabina.isPresent()? cabina.get() : new Cabina();
    }

    public Cabina getCabinaByUbicacion(String ubicacion){
        Optional<Cabina> cabina = cabinaDAO.findByUbicacion(ubicacion);
        return cabina.isPresent()? cabina.get() : new Cabina();
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

}
