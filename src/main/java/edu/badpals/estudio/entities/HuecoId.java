package edu.badpals.estudio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class HuecoId implements java.io.Serializable {
    private static final long serialVersionUID = 3978878909613849686L;
    @Column(name = "NUMERO", columnDefinition = "int UNSIGNED not null")
    private Long numero;

    @Column(name = "CABINA", columnDefinition = "int UNSIGNED not null")
    private Long cabina;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getCabina() {
        return cabina;
    }

    public void setCabina(Long cabina) {
        this.cabina = cabina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HuecoId entity = (HuecoId) o;
        return Objects.equals(this.numero, entity.numero) &&
                Objects.equals(this.cabina, entity.cabina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, cabina);
    }

}