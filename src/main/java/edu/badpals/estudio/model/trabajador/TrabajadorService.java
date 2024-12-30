package edu.badpals.estudio.model.trabajador;

import java.util.Optional;

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
}
