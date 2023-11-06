package net.ausiasmarch.equipo_futbol.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "miembroCuerpoTecnico")
public class MiembroCuerpoTecnicoEntity {

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

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fecha_nacimiento;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String nacionalidad;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private EquipoEntity equipo;

    public MiembroCuerpoTecnicoEntity() {
    }

    public MiembroCuerpoTecnicoEntity(Long id, String nombre, String apellido, Date fecha_nacimiento,
            String nacionalidad, String titulo, EquipoEntity equipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.titulo = titulo;
        this.equipo = equipo;
    }

    public MiembroCuerpoTecnicoEntity(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad,
            String titulo, EquipoEntity equipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.titulo = titulo;
        this.equipo = equipo;
    }

        public MiembroCuerpoTecnicoEntity(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad,
            String titulo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.titulo = titulo;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EquipoEntity getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }

}