package net.ausiasmarch.equipo_futbol.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "equipo")
public class EquipoEntity {

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
    private String ciudad;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date año_fundacion;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String estadio;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String liga;

    @OneToMany(mappedBy = "equipo", fetch = jakarta.persistence.FetchType.LAZY)
    private List<JugadorEntity> jugadores;

    @OneToMany(mappedBy = "equipo", fetch = jakarta.persistence.FetchType.LAZY)
    private List<MiembroCuerpoTecnicoEntity> miembrosCuerpoTecnico;

    public EquipoEntity() {
        jugadores = new ArrayList<>();
        miembrosCuerpoTecnico = new ArrayList<>();
    }

    public EquipoEntity(Long id, String nombre, String ciudad, Date año_fundacion, String estadio, String liga) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.año_fundacion = año_fundacion;
        this.estadio = estadio;
        this.liga = liga;
    }

    public EquipoEntity(String nombre, String ciudad, Date año_fundacion, String estadio, String liga) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.año_fundacion = año_fundacion;
        this.estadio = estadio;
        this.liga = liga;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getAño_fundacion() {
        return año_fundacion;
    }

    public void setAño_fundacion(Date año_fundacion) {
        this.año_fundacion = año_fundacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

}
