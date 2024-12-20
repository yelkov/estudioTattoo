package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "TELEFONOS_TRABAJADORES")
public class TelefonosTrabajadore {
    @EmbeddedId
    private TelefonosTrabajadoreId id;

    @MapsId("trabajador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TRABAJADOR", nullable = false)
    private edu.badpals.estudio.model.entities.Trabajadore trabajador;

    public TelefonosTrabajadoreId getId() {
        return id;
    }

    public void setId(TelefonosTrabajadoreId id) {
        this.id = id;
    }

    public edu.badpals.estudio.model.entities.Trabajadore getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(edu.badpals.estudio.model.entities.Trabajadore trabajador) {
        this.trabajador = trabajador;
    }

}