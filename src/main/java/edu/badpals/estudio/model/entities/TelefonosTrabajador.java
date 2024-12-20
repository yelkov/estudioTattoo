package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "TELEFONOS_TRABAJADORES")
public class TelefonosTrabajador {
    @EmbeddedId
    private TelefonosTrabajadorId id;

    @MapsId("trabajador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TRABAJADOR", nullable = false)
    private Trabajador trabajador;

    public TelefonosTrabajadorId getId() {
        return id;
    }

    public void setId(TelefonosTrabajadorId id) {
        this.id = id;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

}