package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.cita.Cita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HuecoService {

    private final HuecoDAO huecoDAO;

    public HuecoService(){
        this.huecoDAO = new HuecoDAO();
    }

    public Hueco getHueco(int numeroHueco,int idCabina){
        HuecoId id = new HuecoId(numeroHueco,idCabina);
        Optional<Hueco> hueco = huecoDAO.findById(id);
        return hueco.isPresent() ? hueco.get() : null;
    }

    public List<Hueco> getAllHuecos(){
        return huecoDAO.findAll();
    }

    public List<Hueco> getAllFromCabina(Cabina cabina) throws IllegalArgumentException{
        if(cabina == null){
            throw new IllegalArgumentException("No existe la cabina indicada.");
        }else{
            return huecoDAO.findAllFromCabina( cabina );
        }
    }

    public List<Hueco> getHuecosLibres(LocalDate diaInicio,LocalDate diaFin){
        if(diaInicio == null){
            throw new IllegalArgumentException("Es necesario indicar fecha de inicio.");
        }else if(diaFin == null){
            diaFin = diaInicio;
            return huecoDAO.findHuecosLibres(diaInicio,diaFin);
        }else{
            return huecoDAO.findHuecosLibres(diaInicio,diaFin);
        }
    }

    public List<Hueco> getHuecosLibresCabina(Cabina cabina, LocalDate diaInicio,LocalDate diaFin){
        if(cabina == null){
            throw new IllegalArgumentException("No existe la cabina indicada.");
        }else if(diaInicio == null){
            throw new IllegalArgumentException("Es necesario indicar fecha de inicio.");
        }else if(diaFin == null){
            diaFin = diaInicio;
            return huecoDAO.findHuecosLibresCabina(cabina,diaInicio,diaFin);
        }else{
            return huecoDAO.findHuecosLibresCabina(cabina,diaInicio,diaFin);
        }
    }

    public void addCita(Hueco hueco, Cita cita){
        if(hueco == null){
            throw new IllegalArgumentException("No existe el hueco indicado.");
        }else if(cita == null){
            throw new IllegalArgumentException("No existe la cita indicada.");
        }
        hueco.setCita(cita);
        huecoDAO.update(hueco);
    }

    public void removeCita(Hueco hueco){
        if(hueco == null){
            throw new IllegalArgumentException("No existe el hueco indicado.");
        }
        hueco.setCita(null);
        huecoDAO.update(hueco);
    }

}
