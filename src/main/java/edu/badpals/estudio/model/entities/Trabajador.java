package edu.badpals.estudio.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TRABAJADORES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRABAJADOR", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "NIF", nullable = false, length = 9, columnDefinition = "CHAR(9)")
    private String nif;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "NSS", nullable = false, length = 12, columnDefinition = "CHAR(12)")
    private String nss;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "FECHA_ALTA", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "SALARIO", columnDefinition = "float UNSIGNED")
    private Float salario;

    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;

    @ElementCollection
    @CollectionTable(name = "TELEFONOS_TRABAJADORES",
            joinColumns = @JoinColumn(name = "TRABAJADOR", foreignKey = @ForeignKey(name = "FK_TRABAJADOR_TELEFONO")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"TRABAJADOR","TELEFONO"},name = "uniq_trabajador_telefono"))
    @Column(name = "TELEFONO", nullable = false, length = 15)
    private Set<String> telefonos = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<String> telefonos) {
        this.telefonos = telefonos;
    }

    public void addTelefono(String telefono){
        this.telefonos.add(telefono);
    }

    public void removeTelefono(String telefono){
        this.telefonos.remove(telefono);
    }
}