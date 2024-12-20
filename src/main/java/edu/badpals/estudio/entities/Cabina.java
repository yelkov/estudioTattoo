package edu.badpals.estudio.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CABINAS")
public class Cabina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CABINA", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "UBICACION", nullable = false, length = 30)
    private String ubicacion;

    @Column(name = "PUEDE_HACER_PIERCING", nullable = false)
    private Boolean puedeHacerPiercing = false;

    @OneToMany(mappedBy = "cabina")
    private Set<edu.badpals.estudio.entities.Cita> citas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cabina")
    private Set<edu.badpals.estudio.entities.Hueco> huecos = new LinkedHashSet<>();
    @OneToMany(mappedBy = "cabina")
    private Set<edu.badpals.estudio.entities.Stock> stocks = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getPuedeHacerPiercing() {
        return puedeHacerPiercing;
    }

    public void setPuedeHacerPiercing(Boolean puedeHacerPiercing) {
        this.puedeHacerPiercing = puedeHacerPiercing;
    }

    public Set<edu.badpals.estudio.entities.Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<edu.badpals.estudio.entities.Cita> citas) {
        this.citas = citas;
    }

    public Set<edu.badpals.estudio.entities.Hueco> getHuecos() {
        return huecos;
    }

    public void setHuecos(Set<edu.badpals.estudio.entities.Hueco> huecos) {
        this.huecos = huecos;
    }

    public Set<edu.badpals.estudio.entities.Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<edu.badpals.estudio.entities.Stock> stocks) {
        this.stocks = stocks;
    }

/*
 TODO [Reverse Engineering] create field to map the 'SUPERFICIE' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "SUPERFICIE", columnDefinition = "float UNSIGNED not null")
    private Object superficie;
*/
}