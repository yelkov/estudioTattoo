package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ANILLADORES")
public class Anilladore {
    @Id
    @Column(name = "TRABAJADOR", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TRABAJADOR", nullable = false)
    private edu.badpals.estudio.model.entities.Trabajadore trabajadores;

    @OneToMany(mappedBy = "anillador")
    private Set<edu.badpals.estudio.model.entities.Cita> citas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public edu.badpals.estudio.model.entities.Trabajadore getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(edu.badpals.estudio.model.entities.Trabajadore trabajadores) {
        this.trabajadores = trabajadores;
    }

    public Set<edu.badpals.estudio.model.entities.Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<edu.badpals.estudio.model.entities.Cita> citas) {
        this.citas = citas;
    }

/*
 TODO [Reverse Engineering] create field to map the 'COMISION' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Object comision;
*/
}