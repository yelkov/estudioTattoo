package edu.badpals.estudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CitasClienteId implements java.io.Serializable {
    private static final long serialVersionUID = -4639625860770588130L;
    @Column(name = "CLIENTE", columnDefinition = "int UNSIGNED not null")
    private Long cliente;

    @Column(name = "CITA", columnDefinition = "int UNSIGNED not null")
    private Long cita;

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getCita() {
        return cita;
    }

    public void setCita(Long cita) {
        this.cita = cita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CitasClienteId entity = (CitasClienteId) o;
        return Objects.equals(this.cliente, entity.cliente) &&
                Objects.equals(this.cita, entity.cita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, cita);
    }

}