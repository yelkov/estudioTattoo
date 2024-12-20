package edu.badpals.estudio.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TRABAJADORES")
public class Trabajadore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRABAJADOR", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "NIF", nullable = false, length = 9)
    private String nif;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "NSS", nullable = false, length = 12)
    private String nss;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "FECHA_ALTA", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;

    @OneToOne(mappedBy = "trabajador")
    private Anilladore anilladore;

    @OneToOne(mappedBy = "trabajador")
    private Tatuadore tatuadore;
    @OneToMany(mappedBy = "trabajador")
    private Set<TelefonosTrabajadore> telefonosTrabajadores = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Anilladore getAnilladore() {
        return anilladore;
    }

    public void setAnilladore(Anilladore anilladore) {
        this.anilladore = anilladore;
    }

    public Tatuadore getTatuadore() {
        return tatuadore;
    }

    public void setTatuadore(Tatuadore tatuadore) {
        this.tatuadore = tatuadore;
    }

    public Set<TelefonosTrabajadore> getTelefonosTrabajadores() {
        return telefonosTrabajadores;
    }

    public void setTelefonosTrabajadores(Set<TelefonosTrabajadore> telefonosTrabajadores) {
        this.telefonosTrabajadores = telefonosTrabajadores;
    }

/*
 TODO [Reverse Engineering] create field to map the 'SALARIO' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "SALARIO", columnDefinition = "float UNSIGNED")
    private Object salario;
*/
}