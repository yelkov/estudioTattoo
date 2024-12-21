package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ANILLADORES")
@PrimaryKeyJoinColumn(name = "TRABAJADOR")
public class Anillador extends Trabajador{

    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Float comision;

    @OneToMany(mappedBy = "anillador")
    private Set<Cita> citas = new LinkedHashSet<>();

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }
}