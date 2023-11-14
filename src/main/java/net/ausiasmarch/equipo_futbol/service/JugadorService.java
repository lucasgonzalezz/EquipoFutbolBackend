package net.ausiasmarch.equipo_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.entity.JugadorEntity;
import net.ausiasmarch.equipo_futbol.helper.DataGenerationHelper;
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.repository.JugadorRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;

@Service
public class JugadorService {

    @Autowired
    JugadorRepository oJugadorRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    EquipoRepository oEquipoRepository;

    @Autowired
    EquipoService oEquipoService;

    @Autowired
    SessionService oSessionService;

    public JugadorEntity get(Long id) {
        return oJugadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador not found"));
    }

    public Page<JugadorEntity> getPage(Pageable oPageable, Long equipoId) {
        if (equipoId == 0) {
            return oJugadorRepository.findAll(oPageable);
        } else {
            return oJugadorRepository.findByEquipoId(equipoId, oPageable);
        }
    }

    public Long create(JugadorEntity oJugadorEntity) {
        oJugadorEntity.setId(null);
        oSessionService.onlyAdminsOrUsers();
        if (oSessionService.isUser()) {
            oJugadorEntity.setEquipo(oSessionService.getSessionUser());
            return oJugadorRepository.save(oJugadorEntity).getId();
        } else {
            return oJugadorRepository.save(oJugadorEntity).getId();
        }
    }

    public JugadorEntity update(JugadorEntity oJugadorEntityToSet) {
        JugadorEntity oJugadorEntityFromDatabase = this.get(oJugadorEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oJugadorEntityFromDatabase.getEquipo().getId());
        if (oSessionService.isUser()) {
            if (oJugadorEntityToSet.getEquipo().getId().equals(oSessionService.getSessionUser().getId())) {
                return oJugadorRepository.save(oJugadorEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oJugadorRepository.save(oJugadorEntityToSet);
        }
    }

    public Long delete(Long id) {
        JugadorEntity oJugadorEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oJugadorEntityFromDatabase.getEquipo().getId());
        oJugadorRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String nombre = DataGenerationHelper.getRadomPlayerName();
            String apellido = DataGenerationHelper.getRadomPlayerSurname();
            LocalDate fechaNacimiento = DataGenerationHelper.getRandomYear();
            String posicion = DataGenerationHelper.getRadomPlayerPosition();
            String nacionalidad = DataGenerationHelper.getRadomCountry();
            EquipoEntity equipo = oEquipoService.getOneRandom();
            oJugadorRepository
                    .save(new JugadorEntity(nombre, apellido, fechaNacimiento, posicion, nacionalidad, equipo));
        }
        return oEquipoRepository.count();
    }

    public JugadorEntity getOneRandom() {
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oJugadorRepository.count()), 1);
        return oJugadorRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oJugadorRepository.deleteAll();
        oJugadorRepository.resetAutoIncrement();
        oJugadorRepository.flush();
        return oJugadorRepository.count();
    }

}