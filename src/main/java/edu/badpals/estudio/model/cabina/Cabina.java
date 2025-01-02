package edu.badpals.estudio.model.cabina;

import edu.badpals.estudio.model.Stock.Stock;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CABINAS")
public class Cabina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CABINA", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "UBICACION", nullable = false, length = 30)
    private String ubicacion;

    @Column(name = "SUPERFICIE", columnDefinition = "float UNSIGNED not null")
    private Float superficie;

    @Column(name = "PUEDE_HACER_PIERCING", nullable = false)
    private Boolean puedeHacerPiercing = false;

    @OneToMany(mappedBy = "cabina")
    private Set<edu.badpals.estudio.model.entities.Cita> citas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cabina")
    private Set<edu.badpals.estudio.model.entities.Hueco> huecos = new LinkedHashSet<>();
    @OneToMany(mappedBy = "cabina")
    private Set<Stock> stocks = new LinkedHashSet<>();

    public Cabina() {
    }

    public Cabina(String ubicacion, Float superficie, Boolean puedeHacerPiercing) {
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.puedeHacerPiercing = puedeHacerPiercing;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Float superficie) {
        this.superficie = superficie;
    }

    public Boolean getPuedeHacerPiercing() {
        return puedeHacerPiercing;
    }

    public void setPuedeHacerPiercing(Boolean puedeHacerPiercing) {
        this.puedeHacerPiercing = puedeHacerPiercing;
    }

    public Set<edu.badpals.estudio.model.entities.Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<edu.badpals.estudio.model.entities.Cita> citas) {
        this.citas = citas;
    }

    public Set<edu.badpals.estudio.model.entities.Hueco> getHuecos() {
        return huecos;
    }

    public void setHuecos(Set<edu.badpals.estudio.model.entities.Hueco> huecos) {
        this.huecos = huecos;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cabina cabina)) return false;
        return Objects.equals(id, cabina.id) && Objects.equals(ubicacion, cabina.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ubicacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CABINA: ").append("\n");
        sb.append("\tId: ").append(id).append("\n");
        sb.append("\tUbicacion: ").append(ubicacion).append("\n");
        sb.append("\tSuperficie: ").append(superficie).append("\n");
        sb.append("\tPuede hacer piercing?: ").append(puedeHacerPiercing == true? "Sí":"No").append("\n");
        return sb.toString();
    }
}