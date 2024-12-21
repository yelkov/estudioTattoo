package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "AGUJAS")
public class Aguja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGUJA", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIAMETRO", nullable = false)
    private Diametro diametro;

    @Enumerated(EnumType.STRING)
    @Column(name = "NUMERO_AGUJAS", nullable = false)
    private NumeroAgujas numeroAgujas;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONFIGURACION", nullable = false)
    private Configuracion configuracion;

    @Enumerated(EnumType.STRING)
    @Column(name = "TAPER", nullable = false)
    private Taper taper;

    @Column(name = "CANTIDAD", columnDefinition = "int UNSIGNED not null")
    private Integer cantidad;

    @OneToMany(mappedBy = "aguja")
    private Set<CitaAguja> citaAgujas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Diametro getDiametro() {
        return diametro;
    }

    public void setDiametro(Diametro diametro) {
        this.diametro = diametro;
    }

    public NumeroAgujas getNumeroAgujas() {
        return numeroAgujas;
    }

    public void setNumeroAgujas(NumeroAgujas numeroAgujas) {
        this.numeroAgujas = numeroAgujas;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public Taper getTaper() {
        return taper;
    }

    public void setTaper(Taper taper) {
        this.taper = taper;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Set<CitaAguja> getCitasAgujas() {
        return citaAgujas;
    }

    public void setCitasAgujas(Set<CitaAguja> citaAgujas) {
        this.citaAgujas = citaAgujas;
    }

}