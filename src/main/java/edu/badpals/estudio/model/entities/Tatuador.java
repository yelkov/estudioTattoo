package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TATUADORES")
@PrimaryKeyJoinColumn(name = "TRABAJADOR")
public class Tatuador extends Trabajador{

    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Float comision;


    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

}