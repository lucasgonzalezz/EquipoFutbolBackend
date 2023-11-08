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
import net.ausiasmarch.equipo_futbol.helper.DataGenerationHelper;
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.entity.MiembroCuerpoTecnicoEntity;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;
import net.ausiasmarch.equipo_futbol.repository.MiembroCuerpoTecnicoRepository;

@Service
public class MiembroCuerpoTecnicoService {

    @Autowired
    MiembroCuerpoTecnicoRepository oMiembroCuerpoTecnicoRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    EquipoRepository oEquipoRepository;

    @Autowired
    EquipoService oEquipoService;

    public MiembroCuerpoTecnicoEntity get(Long id) {
        return oMiembroCuerpoTecnicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Miembro de Cuerpo Tecnico not found"));
    }

    public Page<MiembroCuerpoTecnicoEntity> getPage(Pageable oPageable, Long equipoId) {
        if (equipoId == 0) {
            return oMiembroCuerpoTecnicoRepository.findAll(oPageable);
        } else {
            return oMiembroCuerpoTecnicoRepository.findByEquipoId(equipoId, oPageable);
        }
    }

    public Long create(MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        oMiembroCuerpoTecnicoEntity.setId(null);
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            oMiembroCuerpoTecnicoEntity.setEquipo(oEquipoEntityInSession);
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity).getId();
        } else {
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity).getId();
        }
    }

    public MiembroCuerpoTecnicoEntity update(MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        oMiembroCuerpoTecnicoEntity = oMiembroCuerpoTecnicoRepository.findById(oMiembroCuerpoTecnicoEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Miembro Cuerpo Tecnico not found"));
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            if (oMiembroCuerpoTecnicoEntity.getEquipo().getId().equals(oEquipoEntityInSession.getId())) {
                return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity);
        }
    }

    public Long delete(Long id) {
        MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity = oMiembroCuerpoTecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro Cuerpo Tecnico not found"));
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.TRUE.equals(oEquipoEntityInSession.getRole())) {
            if (oMiembroCuerpoTecnicoEntity.getEquipo().getId().equals(oEquipoEntityInSession.getId())) {
                oMiembroCuerpoTecnicoRepository.deleteById(id);
                return id;
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            oMiembroCuerpoTecnicoRepository.deleteById(id);
            return id;
        }
    }

    public Long populate(Integer amount) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            for (int i = 0; i < amount; i++) {
                String nombre = DataGenerationHelper.getRadomCuerpoTecnicoName();
                String apellido = DataGenerationHelper.getRadomRadomCuerpoTecnicoSurname();
                Date fechaNacimiento = DataGenerationHelper.getRandomYear();
                String nacionalidad = DataGenerationHelper.getRadomCountry();
                String titulo = DataGenerationHelper.getRadomRadomCuerpoTecnicoTitle();
                EquipoEntity equipo = oEquipoService.getOneRandom();
            
                oMiembroCuerpoTecnicoRepository.save(new MiembroCuerpoTecnicoEntity(nombre, apellido, fechaNacimiento, nacionalidad, titulo, equipo)); 
            }
            return oEquipoRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public MiembroCuerpoTecnicoEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oMiembroCuerpoTecnicoRepository.count()), 1);
        return oMiembroCuerpoTecnicoRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            oMiembroCuerpoTecnicoRepository.deleteAll();
            oMiembroCuerpoTecnicoRepository.resetAutoIncrement();
            oMiembroCuerpoTecnicoRepository.flush();
            return oMiembroCuerpoTecnicoRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }
    
}