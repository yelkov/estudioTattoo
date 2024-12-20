package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CITAS_CLIENTES")
public class CitaCliente {
    @EmbeddedId
    private CitaClienteId id;

    @MapsId("cliente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLIENTE", nullable = false)
    private edu.badpals.estudio.model.entities.Cliente cliente;

    @MapsId("cita")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CITA", nullable = false)
    private Cita cita;

    public CitaClienteId getId() {
        return id;
    }

    public void setId(CitaClienteId id) {
        this.id = id;
    }

    public edu.badpals.estudio.model.entities.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(edu.badpals.estudio.model.entities.Cliente cliente) {
        this.cliente = cliente;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

}