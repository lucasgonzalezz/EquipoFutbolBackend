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
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;
import net.ausiasmarch.equipo_futbol.helper.DataGenerationHelper;

@Service
public class EquipoService {

    @Autowired
    EquipoRepository oEquipoRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public EquipoEntity get(Long id) {
        return oEquipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
    }

    public Page<EquipoEntity> getPage(Pageable oPageable) {
        return oEquipoRepository.findAll(oPageable);
    }

    public Long create(EquipoEntity oEquipoEntity) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            oEquipoEntity.setId(null);
            oEquipoEntity.setPassword("e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e");
            return oEquipoRepository.save(oEquipoEntity).getId();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public EquipoEntity update(EquipoEntity oEquipoEntity) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            oEquipoEntity.setId(null);
            oEquipoEntity.setPassword("e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e");
            return oEquipoRepository.save(oEquipoEntity);
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public Long delete(Long id) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntityInSession.getRole())) {
            oEquipoRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    public EquipoEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oEquipoRepository.count()), 1);
        return oEquipoRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntity = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntity.getRole())) {
            for (int i = 0; i < amount; i++) {
                String nombre = DataGenerationHelper.getRadomTeamName();
                String ciudad = DataGenerationHelper.getRadomCity(); // Reemplaza getRadomSurname() con getRadomCity()
                Date añoFundacion = DataGenerationHelper.getRandomYear(); // Genera una fecha aleatoria como año de fundación
                String estadio = DataGenerationHelper.getRandomStadium(); // Genera un nombre aleatorio para el estadio
                String liga = DataGenerationHelper.getRandomLeague(); // Genera un nombre aleatorio para la liga
                String username = DataGenerationHelper.doNormalizeString(
                        nombre.substring(0, 3) + ciudad.substring(0, 3) + i);
                String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";
                Boolean role = true;
                oEquipoRepository
                        .save(new EquipoEntity(nombre, ciudad, añoFundacion, estadio, liga, username, password, role));
            }
            return oEquipoRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

    @Transactional
    public Long empty() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntity = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        if (Boolean.FALSE.equals(oEquipoEntity.getRole())) {
            oEquipoRepository.deleteAll();
            oEquipoRepository.resetAutoIncrement();
            EquipoEntity equipo1 = new EquipoEntity("Real Madrid", "Madrid", new Date(), "Estadio Santiago Bernabéu",
                    "La Liga", "realmadrid",
                    "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true);
            oEquipoRepository.save(equipo1);

            EquipoEntity equipo2 = new EquipoEntity("FC Barcelona", "Barcelona", new Date(), "Camp Nou", "La Liga",
                    "fcbarcelona",
                    "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true);
            oEquipoRepository.save(equipo2);
            return oEquipoRepository.count();
        } else {
            throw new ResourceNotFoundException("Unauthorized");
        }
    }

}