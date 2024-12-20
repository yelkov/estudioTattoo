package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TRABAJADORES")
public class Trabajador {
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

    @Column(name = "SALARIO", columnDefinition = "float UNSIGNED")
    private Float salario;

    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;

    @OneToOne(mappedBy = "trabajador")
    private Anillador anillador;

    @OneToOne(mappedBy = "trabajador")
    private Tatuador tatuador;
    @OneToMany(mappedBy = "trabajador")
    private Set<TelefonosTrabajador> telefonosTrabajadors = new LinkedHashSet<>();

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

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Anillador getAnilladore() {
        return anillador;
    }

    public void setAnilladore(Anillador anillador) {
        this.anillador = anillador;
    }

    public Tatuador getTatuadore() {
        return tatuador;
    }

    public void setTatuadore(Tatuador tatuador) {
        this.tatuador = tatuador;
    }

    public Set<TelefonosTrabajador> getTelefonosTrabajadores() {
        return telefonosTrabajadors;
    }

    public void setTelefonosTrabajadores(Set<TelefonosTrabajador> telefonosTrabajadors) {
        this.telefonosTrabajadors = telefonosTrabajadors;
    }
}