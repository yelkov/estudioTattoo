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
        if (superficie == null || superficie < 0.0f) {
            throw new IllegalArgumentException();
        }
        Cabina nuevaCabina = new Cabina(ubicacion,superficie,puedeHacerPiercing);
        cabinaDAO.create(nuevaCabina);
    }

}
