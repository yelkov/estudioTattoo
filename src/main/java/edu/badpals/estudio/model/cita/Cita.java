package edu.badpals.estudio.model.cita;

import edu.badpals.estudio.model.cabina.Cabina;
import edu.badpals.estudio.model.cabina.Hueco;
import edu.badpals.estudio.model.cliente.Cliente;
import edu.badpals.estudio.model.trabajador.Anillador;
import edu.badpals.estudio.model.trabajador.Tatuador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CITAS")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private Tipo tipo;

    @Column(name = "DESCRIPCION", nullable = false, length = 60)
    private String descripcion;

    @Column(name = "PRECIO", columnDefinition = "float UNSIGNED not null")
    private Float precio;

    @ColumnDefault("'0.0'")
    @Column(name = "`SEÑAL`", columnDefinition = "float UNSIGNED not null")
    private Float señal;

    @ColumnDefault("'RESERVADA'")
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TATUADOR")
    private Tatuador tatuador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANILLADOR")
    private Anillador anillador;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CABINA", nullable = false)
    private Cabina cabina;

    @OneToMany(mappedBy = "cita",fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Hueco> huecos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "CITAS_CLIENTES",
            joinColumns = @JoinColumn(name = "CITA"),
            inverseJoinColumns = @JoinColumn(name = "CLIENTE")
    )
    private Set<Cliente> clientes;

    public Cita() {
    }

    public Cita( Tipo tipo, String descripcion, Float precio, Float señal, Estado estado,Tatuador tatuador, Anillador anillador, Cabina cabina, Set<Hueco> huecos, Set<Cliente> clientes) {
        this.estado = estado;
        this.señal = señal;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.tatuador = tatuador;
        this.anillador = anillador;
        this.cabina = cabina;
        this.huecos = huecos;
        this.clientes = clientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tatuador getTatuador() {
        return tatuador;
    }

    public void setTatuador(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public Anillador getAnillador() {
        return anillador;
    }

    public void setAnillador(Anillador anillador) {
        this.anillador = anillador;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getSeñal() {
        return señal;
    }

    public void setSeñal(Float señal) {
        this.señal = señal;
    }

    public Set<Hueco> getHuecos() {
        return huecos;
    }

    public void setHuecos(Set<Hueco> huecos) {
        this.huecos = huecos;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removeCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void addHueco(Hueco hueco) {
        this.huecos.add(hueco);
    }

    public void removeHueco(Hueco hueco) {
        this.huecos.remove(hueco);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(id, cita.id) && Objects.equals(descripcion, cita.descripcion) && Objects.equals(cabina, cita.cabina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, cabina);
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", señal=" + señal +
                ", estado=" + estado +
                ", tatuador=" + tatuador +
                ", anillador=" + anillador +
                ", cabina=" + cabina +
                ", clientes=" + clientes +
                '}';
    }
}