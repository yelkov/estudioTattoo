package edu.badpals.estudio.model.cita;


import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.CabinaDAO;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.AnilladorDAO;
import edu.badpals.estudio.model.trabajador.Tatuador;
import edu.badpals.estudio.model.trabajador.TatuadorDAO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CitaService {

    private final CitaDAO citaDAO;
    private final TatuadorDAO tatuadorDAO;
    private final AnilladorDAO anilladorDAO;
    private final CabinaDAO cabinaDAO;

    public CitaService() {
        this.citaDAO = new CitaDAO();
        this.tatuadorDAO = new TatuadorDAO();
        this.anilladorDAO = new AnilladorDAO();
        this.cabinaDAO = new CabinaDAO();
    }

    public void crearCita(Tipo tipo, String descripcion, Float precio, Float señal, Estado estado, Tatuador tatuador, Anillador anillador, Cabina cabina, List<Hueco> huecos, Set<Cliente> clientes)throws IllegalArgumentException {

        if(!hasCamposObligatorios(tipo,descripcion,precio,señal,estado,cabina,clientes)){
            throw new IllegalArgumentException("Hay campos obligatorios sin rellenar o no válidos.");

        }else if(tipo == Tipo.TATUAJE && (tatuador == null || !tatuadorDAO.findById(tatuador.getId()).isPresent())){
            throw new IllegalArgumentException("No existe el tatuador o no se ha indicado para una cita de tatuaje.");

        }else if(tipo == Tipo.PIERCING && (anillador == null || !anilladorDAO.findById(anillador.getId()).isPresent())){
            throw new IllegalArgumentException("No existe el anillador o no se ha indicado para una cita de piercing.");

        }else if(cabina == null || !cabinaDAO.findById(cabina.getId()).isPresent()){
            throw new IllegalArgumentException("No se ha indicado o no existe la cabina especificada en el registro.");

        }else if(tipo == Tipo.PIERCING && !cabina.getPuedeHacerPiercing()){
            throw new IllegalArgumentException("No es posible crear una cita de tipo piercing en una cabina no habilitada para ello.");

        }else if(!hasHuecosLibres(huecos)){
            throw new IllegalArgumentException("Alguno de los huecos indicados está ocupado o no existe en el registro.");

        }else if(!hasClientes(clientes)){
            throw new IllegalArgumentException("Alguno de los clientes indicados no existe en el registro.");
        }else{
            Cita cita = new Cita(tipo,descripcion,precio,señal,estado,tatuador,anillador,cabina,huecos,clientes);
            citaDAO.create(cita);
            citaDAO.reservarHuecos(cita,huecos);
        }
    }

    private Boolean hasCamposObligatorios(Tipo tipo, String descripcion, Float precio, Float señal, Estado estado, Cabina cabina, Set<Cliente> clientes) {
        if(tipo == null || descripcion == null || descripcion.isEmpty() || precio == null || precio < 0.0 || señal == null || señal < 0.0 || estado == null || cabina == null || !cabinaDAO.findById(cabina.getId()).isPresent()){
            return false;
        }
        return true;
    }

    private Boolean hasHuecosLibres(List<Hueco> huecos){
        for (Hueco hueco : huecos) {
            if(hueco == null || hueco.getCita() != null){
                return false;
            }
        }
        return true;
    }

    private Boolean hasClientes(Set<Cliente> clientes){
        for (Cliente cliente : clientes) {
            if (cliente == null){
                return false;
            }
        }
        return true;
    }

    public void deleteCita(Cita cita) {
        citaDAO.delete(cita);
    }

    public void updateCita(Cita cita, Tipo newTipo, String newDescripcion, Float newPrecio, Float newSeñal, Estado newEstado, Tatuador newTatuador, Anillador newAnillador, Cabina newCabina, List<Hueco> newHuecos, Set<Cliente> newClientes) {

        if(newTipo != null){
            cita.setTipo(newTipo);
        }

        if(newDescripcion != null && !newDescripcion.isEmpty()){
            cita.setDescripcion(newDescripcion);
        }

        if(newPrecio != null && newPrecio >= 0.0){
            cita.setPrecio(newPrecio);
        }

        if(newSeñal != null && newSeñal >= 0.0){
            cita.setSeñal(newSeñal);
        }

        if(newEstado != null){
            cita.setEstado(newEstado);
        }

        if(newTipo == Tipo.TATUAJE && (newTatuador == null || !tatuadorDAO.findById(newTatuador.getId()).isPresent())){
            throw new IllegalArgumentException("No existe el tatuador o no se ha indicado para actualizar la cita de tatuaje.");
        }else{
            cita.setTatuador(newTatuador);
        }

        if(newTipo == Tipo.PIERCING && (newAnillador == null || !anilladorDAO.findById(newAnillador.getId()).isPresent())){
            throw new IllegalArgumentException("No existe el anillador o no se ha indicado para actualizar la cita de piercing.");
        }else{
            cita.setAnillador(newAnillador);
        }

        if(newTipo == Tipo.PIERCING && !newCabina.getPuedeHacerPiercing()){
            throw new IllegalArgumentException("No es posible crear una cita de tipo piercing en una cabina no habilitada para ello.");
        }else if(newCabina != null && !cabinaDAO.findById(newCabina.getId()).isPresent()){
            throw new IllegalArgumentException("No se ha indicado o no existe la cabina especificada en el registro.");
        }else if(newCabina != null){
            cita.setCabina(newCabina);
        }
        if(newHuecos != null && !newHuecos.isEmpty()){
            if(!hasHuecosLibres(newHuecos)){
                throw new IllegalArgumentException("Alguno de los huecos indicados está ocupado o no existe en el registro.");
            }else {
                cita.setHuecos(newHuecos);
            }
        }
        if(newClientes != null && !newClientes.isEmpty()){
            if(!hasClientes(newClientes)){
                throw new IllegalArgumentException("Alguno de los clientes indicados no existe en el registro.");
            }else{
                cita.setClientes(newClientes);
            }
        }

        citaDAO.update(cita);
    }

    public Cita getCita(int i) {
        Optional<Cita> cita = citaDAO.findById(i);
        return cita.isPresent() ? cita.get() : null;
    }

    public Cita getCitaByDescripcionCabina(String descripcion, int idCabina) {
        Optional<Cita> cita = citaDAO.findByDescripcionCabina(descripcion,idCabina);
        return cita.isPresent() ? cita.get() : null;
    }

    public List<Cita> getAllCitas() {
        return citaDAO.findAll();
    }
    public List<Cita> getCitasByTipo(Tipo tipo) {
        return citaDAO.findByTipo(tipo);
    }
    public List<Cita> getCitasByAnillador(int idAnillador) {
        return citaDAO.findByAnillador(idAnillador);
    }
    public List<Cita> getCitasByTatuador(int idTatuador) {
        return citaDAO.findByTatuador(idTatuador);
    }
    public List<Cita> getCitasByCabina(int idCabina) {
            return citaDAO.findByCabina(idCabina);
    }
    public List<Cita> getCitasByEstado(Estado estado) {
        return citaDAO.findByEstado(estado);
    }
}
