package edu.badpals.estudio.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CITAS")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Lob
    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "DESCRIPCION", nullable = false, length = 60)
    private String descripcion;

    @ColumnDefault("'RESERVADA'")
    @Lob
    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TATUADOR")
    private edu.badpals.estudio.entities.Tatuadore tatuador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANILLADOR")
    private Anilladore anillador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CABINA", nullable = false)
    private Cabina cabina;
    @ManyToMany(mappedBy = "citas")
    private Set<edu.badpals.estudio.entities.Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cita")
    private Set<edu.badpals.estudio.entities.CitasAguja> citasAgujas = new LinkedHashSet<>();
    @OneToMany(mappedBy = "cita")
    private Set<edu.badpals.estudio.entities.Hueco> huecos = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public edu.badpals.estudio.entities.Tatuadore getTatuador() {
        return tatuador;
    }

    public void setTatuador(edu.badpals.estudio.entities.Tatuadore tatuador) {
        this.tatuador = tatuador;
    }

    public Anilladore getAnillador() {
        return anillador;
    }

    public void setAnillador(Anilladore anillador) {
        this.anillador = anillador;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public Set<edu.badpals.estudio.entities.CitasAguja> getCitasAgujas() {
        return citasAgujas;
    }

    public void setCitasAgujas(Set<edu.badpals.estudio.entities.CitasAguja> citasAgujas) {
        this.citasAgujas = citasAgujas;
    }

    public Set<edu.badpals.estudio.entities.Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<edu.badpals.estudio.entities.Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<edu.badpals.estudio.entities.Hueco> getHuecos() {
        return huecos;
    }

    public void setHuecos(Set<edu.badpals.estudio.entities.Hueco> huecos) {
        this.huecos = huecos;
    }

/*
 TODO [Reverse Engineering] create field to map the '`SEÑAL`' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'0'")
    @Column(name = "`SEÑAL`", columnDefinition = "float UNSIGNED not null")
    private Object señal;
*/
/*
 TODO [Reverse Engineering] create field to map the 'PRECIO' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "PRECIO", columnDefinition = "float UNSIGNED not null")
    private Object precio;
*/
}