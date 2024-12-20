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
    private Long id;

    @Lob
    @Column(name = "DIAMETRO", nullable = false)
    private String diametro;

    @Lob
    @Column(name = "NUMERO_AGUJAS", nullable = false)
    private String numeroAgujas;

    @Lob
    @Column(name = "CONFIGURACION", nullable = false)
    private String configuracion;

    @Lob
    @Column(name = "TAPER", nullable = false)
    private String taper;

    @Column(name = "CANTIDAD", columnDefinition = "int UNSIGNED not null")
    private Long cantidad;

    @OneToMany(mappedBy = "aguja")
    private Set<CitaAguja> citaAgujas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getNumeroAgujas() {
        return numeroAgujas;
    }

    public void setNumeroAgujas(String numeroAgujas) {
        this.numeroAgujas = numeroAgujas;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public String getTaper() {
        return taper;
    }

    public void setTaper(String taper) {
        this.taper = taper;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Set<CitaAguja> getCitasAgujas() {
        return citaAgujas;
    }

    public void setCitasAgujas(Set<CitaAguja> citaAgujas) {
        this.citaAgujas = citaAgujas;
    }

}