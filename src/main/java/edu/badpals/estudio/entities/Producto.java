package edu.badpals.estudio.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @OneToMany(mappedBy = "producto")
    private Set<edu.badpals.estudio.entities.Stock> stocks = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<edu.badpals.estudio.entities.Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<edu.badpals.estudio.entities.Stock> stocks) {
        this.stocks = stocks;
    }

}