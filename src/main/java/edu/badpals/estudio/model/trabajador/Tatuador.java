package edu.badpals.estudio.model.trabajador;

import edu.badpals.estudio.model.entities.Cita;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "TATUADORES")
@PrimaryKeyJoinColumn(name = "TRABAJADOR")
public class Tatuador extends Trabajador {

    @Column(name = "COMISION", columnDefinition = "float UNSIGNED not null")
    private Float comision;

    @OneToMany(mappedBy = "tatuador",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cita> citas = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "TATUADORES_TINTAS",
            joinColumns = @JoinColumn(name = "TATUADOR", foreignKey = @ForeignKey(name = "FK_TATUADOR_TINTA")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"TATUADOR","TINTA_PROPIA"},name = "uniq_tatuador_tinta"))
    @MapKeyColumn(name = "TINTA_PROPIA", nullable = false, length = 50)
    @Column(name = "CANTIDAD", nullable = false)
    private Map<String,Integer> tintas_propias = new HashMap<>();

    public Tatuador() {
        super();
    }

    public Tatuador(String nif, String nombre, String nss, LocalDate fechaNacimiento, LocalDate fechaAlta, Float salario, String email, Float comision) {
        super(nif, nombre, nss, fechaNacimiento, fechaAlta, salario, email);
        this.comision = comision;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public void addCita(Cita cita){
        citas.add(cita);
    }

    public void removeCita(Cita cita){
        citas.remove(cita);
    }

    public Map<String, Integer> getTintas_propias() {
        return tintas_propias;
    }

    public void setTintas_propias(Map<String, Integer> tintas_propias) {
        this.tintas_propias = tintas_propias;
    }

    public void addTinta(String tinta){
        tintas_propias.putIfAbsent(tinta, 1);
    }

    public void removeTinta(String tinta){
        tintas_propias.remove(tinta);
    }

    public void sumCantidadTinta(String tinta, Integer sumaCantidad){
        tintas_propias.computeIfPresent(tinta,(key, cantidadActual) -> cantidadActual + sumaCantidad);
    }

    public void restaCantidadTinta(String tinta, Integer restaCantidad) {
        tintas_propias.computeIfPresent(tinta, (key, cantidadActual) -> {
            int nuevaCantidad = cantidadActual - restaCantidad;
            return nuevaCantidad > 0 ? nuevaCantidad : 0;
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TATUADOR: ").append("\n");
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