package edu.badpals.estudio.model.cliente;

import edu.badpals.estudio.model.cita.Cita;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CLIENTES")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "DNI", nullable = false, length = 9, columnDefinition = "CHAR(9)")
    private String dni;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;

    @Column(name = "`DIRECCIÓN`", nullable = false, length = 60)
    private String dirección;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "INSTAGRAM", length = 30)
    private String instagram;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "TUTOR")
    private Cliente tutor;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARENTESCO")
    private Parentesco parentesco;

    @ElementCollection
    @CollectionTable(name = "ALERGIAS_CLIENTES",
            joinColumns = @JoinColumn(name = "CLIENTE", foreignKey = @ForeignKey(name = "FK_CLIENTE_ALERGIA")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"CLIENTE","ALERGIA"},name = "uniq_cliente_alergia"))
    @Column(name = "ALERGIA", nullable = false, length = 30)
    private Set<String> alergias = new HashSet<>();

    @ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
    private Set<Cita> citas = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String telefono, String dirección, LocalDate fechaNacimiento, String instagram, String email, Cliente tutor, Parentesco parentesco,  Set<String> alergias) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dirección = dirección;
        this.fechaNacimiento = fechaNacimiento;
        this.instagram = instagram;
        this.email = email;
        this.parentesco = parentesco;
        this.tutor = tutor;
        this.alergias = alergias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getTutor() {
        return tutor;
    }

    public void setTutor(Cliente tutor) {
        this.tutor = tutor;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Set<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(Set<String> alergias) {
        this.alergias = alergias;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public void addAlergias(String alergia){
        this.alergias.add(alergia);
    }

    public void removeAlergias(String alergia){
        this.alergias.remove(alergia);
    }

    public void addCita(Cita cita){
        this.citas.add(cita);
    }

    public void removeCita(Cita cita){
        this.citas.remove(cita);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, telefono);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CLIENTE: ").append("\n");
        sb.append("\tId: ").append(id).append("\n");
        sb.append("\tDNI: ").append(dni).append("\n");
        sb.append("\tNOMBRE: ").append(nombre).append("\n");
        sb.append("\tTELEFONO: ").append(telefono).append("\n");
        sb.append("\tFECHA NACIMIENTO: ").append(fechaNacimiento).append("\n");
        sb.append("\tDIRECCION: ").append(dirección).append("\n");
        sb.append("\tEMAIL: ").append(email != null? email : " - ").append("\n");
        sb.append("\tINSTAGRAM: ").append(instagram != null? instagram : " - ").append("\n");
        sb.append("\tTUTOR: ").append(tutor != null? tutor.getNombre() : " - ").append("\n");
        sb.append("\tPARENTESCO: ").append(parentesco != null? parentesco : " - ").append("\n");
        sb.append("\tALERGIAS: ").append(String.join(", ",alergias)).append("\n");
        return sb.toString();
    }
}