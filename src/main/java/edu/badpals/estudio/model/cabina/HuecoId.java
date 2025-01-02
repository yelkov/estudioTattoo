package edu.badpals.estudio.model.cabina;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class HuecoId implements java.io.Serializable {
    private static final long serialVersionUID = 3978878909613849686L;
    @Column(name = "NUMERO", columnDefinition = "int UNSIGNED not null")
    private Integer numero;

    @Column(name = "CABINA", columnDefinition = "int UNSIGNED not null")
    private Integer cabina;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCabina() {
        return cabina;
    }

    public void setCabina(Integer cabina) {
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