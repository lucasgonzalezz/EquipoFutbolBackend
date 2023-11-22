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
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;
import net.ausiasmarch.equipo_futbol.helper.DataGenerationHelper;

@Service
public class EquipoService {

    private final String equipofutbolPASSWORD = "AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336";

    @Autowired
    EquipoRepository oEquipoRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    @Autowired
    SessionService oSessionService;

    public EquipoEntity get(Long id) {
        return oEquipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
    }

    public EquipoEntity getByUsername(String username) {
        return oEquipoRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found by username"));
    }

    public Page<EquipoEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdmins();
        return oEquipoRepository.findAll(oPageable);
    }

    public Long create(EquipoEntity oEquipoEntity) {
        oSessionService.onlyAdmins();
        oEquipoEntity.setId(null);
        oEquipoEntity.setPassword(equipofutbolPASSWORD);
        return oEquipoRepository.save(oEquipoEntity).getId();
    }

    public EquipoEntity update(EquipoEntity oEquipoEntityToSet) {
        EquipoEntity oEquipoEntityFromDatabase = this.get(oEquipoEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oEquipoEntityFromDatabase.getId());
        if (oSessionService.isUser()) {
            oEquipoEntityToSet.setRole(oEquipoEntityFromDatabase.getRole());
            oEquipoEntityToSet.setPassword(equipofutbolPASSWORD);
            return oEquipoRepository.save(oEquipoEntityToSet);
        } else {
            oEquipoEntityToSet.setPassword(equipofutbolPASSWORD);
            return oEquipoRepository.save(oEquipoEntityToSet);
        }
    }

    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        oEquipoRepository.deleteById(id);
        return id;
    }

    public EquipoEntity getOneRandom() {
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oEquipoRepository.count()), 1);
        return oEquipoRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String nombre = DataGenerationHelper.getRadomTeamName();
            String ciudad = DataGenerationHelper.getRadomCity();
            LocalDate anoFundacion = DataGenerationHelper.getRandomDate();
            String estadio = DataGenerationHelper.getRandomStadium();
            String liga = DataGenerationHelper.getRandomLeague();
            String username = DataGenerationHelper.doNormalizeString(
                    nombre.substring(0, 3) + ciudad.substring(0, 3) + i);
            oEquipoRepository
                    .save(new EquipoEntity(nombre, ciudad, anoFundacion, estadio, liga, username, equipofutbolPASSWORD,
                            true));
        }
        return oEquipoRepository.count();
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oEquipoRepository.deleteAll();
        oEquipoRepository.resetAutoIncrement();
        EquipoEntity equipo1 = new EquipoEntity(1L, "Real Madrid", "Madrid", LocalDate.of(1902, 03, 06),
                "Estadio Santiago Bernabéu",
                "La Liga", "realmadrid",
                equipofutbolPASSWORD, false);
        oEquipoRepository.save(equipo1);

        EquipoEntity equipo2 = new EquipoEntity(2L, "FC Barcelona", "Barcelona", LocalDate.of(1899, 10, 22), "Camp Nou",
                "La Liga",
                "fcbarcelona",
                equipofutbolPASSWORD, true);
        oEquipoRepository.save(equipo2);
        return oEquipoRepository.count();
    }

    public Page<EquipoEntity> getPageByJugadoresNumberDesc(Pageable oPageable) {
        return oEquipoRepository.findEquiposByJugadoresNumberDescFilter(oPageable);
    }

}