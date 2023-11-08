package net.ausiasmarch.equipo_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            oJugadorEntity.setEquipo(oEquipoEntityInSession);
            return oJugadorRepository.save(oJugadorEntity).getId();
        } else {
            return oJugadorRepository.save(oJugadorEntity).getId();
        }
    }

    public JugadorEntity update(JugadorEntity oJugadorEntity) {
        oJugadorEntity = oJugadorRepository.findById(oJugadorEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Jugador not found"));
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            if (oJugadorEntity.getEquipo().getId().equals(oEquipoEntityInSession.getId())) {
                return oJugadorRepository.save(oJugadorEntity);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oJugadorRepository.save(oJugadorEntity);
        }
    }

    public Long delete(Long id) {
        JugadorEntity oJugadorEntity = oJugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador not found"));
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            if (oJugadorEntity.getEquipo().getId().equals(oEquipoEntityInSession.getId())) {
                oJugadorRepository.deleteById(id);
                return id;
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            oJugadorRepository.deleteById(id);
            return id;
        }
    }

    public Long populate(Integer amount) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            for (int i = 0; i < amount; i++) {
                String nombre = DataGenerationHelper.getRadomPlayerName();
                String apellido = DataGenerationHelper.getRadomPlayerSurname();
                Date fechaNacimiento = DataGenerationHelper.getRandomYear();
                String posicion = DataGenerationHelper.getRadomPlayerPosition();
                String nacionalidad = DataGenerationHelper.getRadomCountry();
                EquipoEntity equipo = oEquipoService.getOneRandom();

                oJugadorRepository
                        .save(new JugadorEntity(nombre, apellido, fechaNacimiento, posicion, nacionalidad, equipo));
            }
            return oEquipoRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public JugadorEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oJugadorRepository.count()), 1);
        return oJugadorRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            oJugadorRepository.deleteAll();
            oJugadorRepository.resetAutoIncrement();
            oJugadorRepository.flush();
            return oJugadorRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

}