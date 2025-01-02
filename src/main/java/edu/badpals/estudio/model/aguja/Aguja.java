package edu.badpals.estudio.model.aguja;

import jakarta.persistence.*;

@Entity
@Table(name = "AGUJAS", schema = "estudio_test")
public class Aguja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGUJA", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "CANTIDAD", columnDefinition = "int UNSIGNED not null")
    private Long cantidad;

    public Aguja(Integer id, String tipo, Long cantidad) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Aguja(String tipo, Long cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Aguja() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

}