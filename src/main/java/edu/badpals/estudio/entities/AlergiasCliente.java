package edu.badpals.estudio.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ALERGIAS_CLIENTES")
public class AlergiasCliente {
    @EmbeddedId
    private AlergiasClienteId id;

    @MapsId("cliente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CLIENTE", nullable = false)
    private edu.badpals.estudio.entities.Cliente cliente;

    public AlergiasClienteId getId() {
        return id;
    }

    public void setId(AlergiasClienteId id) {
        this.id = id;
    }

    public edu.badpals.estudio.entities.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(edu.badpals.estudio.entities.Cliente cliente) {
        this.cliente = cliente;
    }

}