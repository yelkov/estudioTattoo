package edu.badpals.estudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class AlergiasClienteId implements java.io.Serializable {
    private static final long serialVersionUID = 7317609417460629753L;
    @Column(name = "CLIENTE", columnDefinition = "int UNSIGNED not null")
    private Long cliente;

    @Column(name = "ALERGIA", nullable = false, length = 30)
    private String alergia;

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AlergiasClienteId entity = (AlergiasClienteId) o;
        return Objects.equals(this.cliente, entity.cliente) &&
                Objects.equals(this.alergia, entity.alergia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, alergia);
    }

}