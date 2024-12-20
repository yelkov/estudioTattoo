package edu.badpals.estudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class TelefonosTrabajadoreId implements java.io.Serializable {
    private static final long serialVersionUID = -1484277853476983362L;
    @Column(name = "TRABAJADOR", columnDefinition = "int UNSIGNED not null")
    private Long trabajador;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;

    public Long getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Long trabajador) {
        this.trabajador = trabajador;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TelefonosTrabajadoreId entity = (TelefonosTrabajadoreId) o;
        return Objects.equals(this.trabajador, entity.trabajador) &&
                Objects.equals(this.telefono, entity.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trabajador, telefono);
    }

}