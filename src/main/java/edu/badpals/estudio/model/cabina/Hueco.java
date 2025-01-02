package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.cita.Cita;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "HUECOS")
public class Hueco {
    @EmbeddedId
    private HuecoId id;

    @MapsId("cabina")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CABINA", nullable = false)
    private Cabina cabina;

    @Column(name = "HORA", nullable = false)
    private LocalTime hora;

    @Column(name = "DIA", nullable = false)
    private LocalDate dia;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "CITA")
    private Cita cita;

    public HuecoId getId() {
        return id;
    }

    public void setId(HuecoId id) {
        this.id = id;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hueco hueco = (Hueco) o;
        return Objects.equals(id, hueco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Hueco{" +
                "id=" + id +
                ", cabina=" + cabina +
                ", hora=" + hora +
                ", dia=" + dia +
                ", cita=" + cita +
                '}';
    }
}