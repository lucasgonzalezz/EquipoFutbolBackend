package net.ausiasmarch.equipo_futbol.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @NotNull
    @NotBlank
    @Size(min=6, max=15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;
   
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Size(min=64, max=64)
    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";        
   
    private Boolean role = false;

    @OneToMany(mappedBy = "equipo", fetch = jakarta.persistence.FetchType.LAZY)
    private List<JugadorEntity> jugadores;

    @OneToMany(mappedBy = "equipo", fetch = jakarta.persistence.FetchType.LAZY)
    private List<MiembroCuerpoTecnicoEntity> miembrosCuerpoTecnico;

    public EquipoEntity() {
        jugadores = new ArrayList<>();
        miembrosCuerpoTecnico = new ArrayList<>();
    }

    public EquipoEntity(Long id, String nombre, String ciudad, Date año_fundacion, String estadio, String liga, String username, String password,
    Boolean role) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.año_fundacion = año_fundacion;
        this.estadio = estadio;
        this.liga = liga;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public EquipoEntity(String nombre, String ciudad, Date año_fundacion, String estadio, String liga, String username, String password,
    Boolean role) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.año_fundacion = año_fundacion;
        this.estadio = estadio;
        this.liga = liga;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public EquipoEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public int getJugadores() {
        return jugadores.size();
    }

    public int getMiembrosCuerpoTecnico() {
        return miembrosCuerpoTecnico.size();
    }

}