package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.entities.Cita;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ANILLADORES")
@PrimaryKeyJoinColumn(name = "TRABAJADOR")
public class Anillador extends Trabajador {

    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Float comision;

    @OneToMany(mappedBy = "anillador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cita> citas = new HashSet<>();

    public Anillador() {
        super();
    }

    public Anillador(String nif, String nombre, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String email, Float comision) {
        super(nif, nombre, nss, fechaNacimiento, fechaAlta, salario, email);
        this.comision = comision;
    }

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

    public void addCita(Cita cita){
        citas.add(cita);
    }
    public void removeCita(Cita cita){
        citas.remove(cita);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ANILLADOR: ").append("\n");
        sb.append("\tId: ").append(super.getId()).append("\n");
        sb.append("\tNIF: ").append(super.getNif()).append("\n");
        sb.append("\tNOMBRE: ").append(super.getNombre()).append("\n");
        sb.append("\tNSS: ").append(super.getNss()).append("\n");
        sb.append("\tFECHA NACIMIENTO: ").append(super.getFechaNacimiento()).append("\n");
        sb.append("\tFECHA ALTA: ").append(super.getFechaAlta()).append("\n");
        sb.append("\tCOMISION: " + comision + "\n");
        sb.append("\tEMAIL: ").append(super.getEmail()).append("\n");
        sb.append("\tTELEFONOS: ").append(String.join(", ",super.getTelefonos())).append("\n");
        return sb.toString();
    }
}