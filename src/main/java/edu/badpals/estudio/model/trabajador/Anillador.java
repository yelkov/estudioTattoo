package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.entities.Cita;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "ANILLADORES")
@PrimaryKeyJoinColumn(name = "TRABAJADOR")
public class Anillador extends Trabajador {

    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Float comision;

    @OneToMany(mappedBy = "anillador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cita> citas = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ANILLADORES_PIEZAS",
            joinColumns = @JoinColumn(name = "ANILLADOR", foreignKey = @ForeignKey(name = "FK_ANILLADOR_PIEZA")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ANILLADOR","PIEZA_PROPIA"},name = "uniq_anillador_pieza"))
    @MapKeyColumn(name = "PIEZA_PROPIA", nullable = false, length = 50)
    @Column(name = "CANTIDAD", nullable = false)
    private Map<String,Integer> piezas_propias = new HashMap<>();

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

    public Map<String, Integer> getPiezas_propias() {
        return piezas_propias;
    }

    public void setPiezas_propias(Map<String, Integer> piezas_propias) {
        this.piezas_propias = piezas_propias;
    }

    public void addTinta(String pieza){
        piezas_propias.putIfAbsent(pieza, 1);
    }

    public void removeTinta(String pieza){
        piezas_propias.remove(pieza);
    }

    public void sumCantidadTinta(String pieza, Integer sumaCantidad){
        piezas_propias.computeIfPresent(pieza,(key, cantidadActual) -> cantidadActual + sumaCantidad);
    }

    public void restaCantidadTinta(String pieza, Integer restaCantidad) {
        piezas_propias.computeIfPresent(pieza, (key, cantidadActual) -> {
            int nuevaCantidad = cantidadActual - restaCantidad;
            return nuevaCantidad > 0 ? nuevaCantidad : 0;
        });
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