package edu.badpals.estudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CitasAgujaId implements java.io.Serializable {
    private static final long serialVersionUID = -8561492478595670768L;
    @Column(name = "AGUJA", columnDefinition = "int UNSIGNED not null")
    private Long aguja;

    @Column(name = "CITA", columnDefinition = "int UNSIGNED not null")
    private Long cita;

    public Long getAguja() {
        return aguja;
    }

    public void setAguja(Long aguja) {
        this.aguja = aguja;
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
        CitasAgujaId entity = (CitasAgujaId) o;
        return Objects.equals(this.aguja, entity.aguja) &&
                Objects.equals(this.cita, entity.cita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aguja, cita);
    }

}