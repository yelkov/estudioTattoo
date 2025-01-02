package edu.badpals.estudio.model.cita;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CitaAgujaId implements java.io.Serializable {
    private static final long serialVersionUID = -8561492478595670768L;
    @Column(name = "AGUJA", columnDefinition = "int UNSIGNED not null")
    private Integer aguja;

    @Column(name = "CITA", columnDefinition = "int UNSIGNED not null")
    private Integer cita;

    public Integer getAguja() {
        return aguja;
    }

    public void setAguja(Integer aguja) {
        this.aguja = aguja;
    }

    public Integer getCita() {
        return cita;
    }

    public void setCita(Integer cita) {
        this.cita = cita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CitaAgujaId entity = (CitaAgujaId) o;
        return Objects.equals(this.aguja, entity.aguja) &&
                Objects.equals(this.cita, entity.cita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aguja, cita);
    }

}