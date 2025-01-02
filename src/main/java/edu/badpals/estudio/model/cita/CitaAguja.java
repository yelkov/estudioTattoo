package edu.badpals.estudio.model.cita;

import edu.badpals.estudio.model.aguja.Aguja;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CITAS_AGUJAS")
public class CitaAguja {
    @EmbeddedId
    private CitaAgujaId id;

    @MapsId("aguja")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGUJA", nullable = false)
    private Aguja aguja;

    @MapsId("cita")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CITA", nullable = false)
    private Cita cita;

    @Column(name = "CANTIDAD_USADA", columnDefinition = "int UNSIGNED not null")
    private Integer cantidadUsada;

    public CitaAgujaId getId() {
        return id;
    }

    public void setId(CitaAgujaId id) {
        this.id = id;
    }

    public Aguja getAguja() {
        return aguja;
    }

    public void setAguja(Aguja aguja) {
        this.aguja = aguja;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

}