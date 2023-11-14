package net.ausiasmarch.equipo_futbol.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "jugador")
public class JugadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String apellido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_nacimiento;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String posicion;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private EquipoEntity equipo;

    public JugadorEntity() {
    }

    public JugadorEntity(Long id, String nombre, String apellido, LocalDate fecha_nacimiento, String posicion,
            String nacionalidad, EquipoEntity equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = LocalDate.now();
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }

    public JugadorEntity(String nombre, String apellido, LocalDate fecha_nacimiento, String posicion, String nacionalidad,
            EquipoEntity equipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = LocalDate.now();
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }

    public JugadorEntity(String nombre, String apellido, LocalDate fecha_nacimiento, String posicion, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = LocalDate.now();
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public EquipoEntity getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }

}