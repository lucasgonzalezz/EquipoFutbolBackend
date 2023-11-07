package net.ausiasmarch.equipo_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.entity.JugadorEntity;
import net.ausiasmarch.equipo_futbol.repository.JugadorRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;

@Service
public class JugadorService {
    
    @Autowired
    JugadorRepository oJugadorRepository;

    @Autowired
    EquipoService oEquipoService;

    public JugadorEntity get(Long id) {
        return oJugadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador not found"));
    }

    public Long create(JugadorEntity oJugadorEntity) {
        oJugadorEntity.setId(null);
        return oJugadorRepository.save(oJugadorEntity).getId();
    }

    public JugadorEntity update(JugadorEntity oJugadorEntity) {
        return oJugadorRepository.save(oJugadorEntity);
    }

    public Long delete(Long id) {
        oJugadorRepository.deleteById(id);
        return id;
    }

    public Page<JugadorEntity> getPage(Pageable oPageable) {
        return oJugadorRepository.findAll(oPageable);
    }

     public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            String nombre = "Nombre" + i;
            String apellido = "Apellido" + i;
            Date fechaNacimiento = new Date(); // Aquí debes definir una fecha de nacimiento adecuada
            String posicion = "Posición" + i;
            String nacionalidad = "Nacionalidad" + i;
            EquipoEntity equipo = oEquipoService.getOneRandom(); // Debes definir cómo obtener un equipo aleatorio

            JugadorEntity jugador = new JugadorEntity(nombre, apellido, fechaNacimiento, posicion, nacionalidad, equipo);
            oJugadorRepository.save(jugador);
        }
        return oJugadorRepository.count();
    }

}
