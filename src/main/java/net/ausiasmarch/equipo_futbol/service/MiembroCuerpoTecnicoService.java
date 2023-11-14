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

    @Autowired
    SessionService oSessionService;

    public MiembroCuerpoTecnicoEntity get(Long id) {
        return oMiembroCuerpoTecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro de Cuerpo Tecnico not found"));
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
        oSessionService.onlyAdminsOrUsers();
        if (oSessionService.isUser()) {
            oMiembroCuerpoTecnicoEntity.setEquipo(oSessionService.getSessionUser());
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity).getId();
        } else {
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity).getId();
        }
    }

    public MiembroCuerpoTecnicoEntity update(MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntityToSet) {
        MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntityFromDatabase = this
                .get(oMiembroCuerpoTecnicoEntityToSet.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oMiembroCuerpoTecnicoEntityFromDatabase.getEquipo().getId());
        if (oSessionService.isUser()) {
            if (oMiembroCuerpoTecnicoEntityToSet.getEquipo().getId().equals(oSessionService.getSessionUser().getId())) {
                return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntityToSet);
            } else {
                throw new ResourceNotFoundException("Unauthorized");
            }
        } else {
            return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntityToSet);
        }
    }

    public Long delete(Long id) {
        MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oMiembroCuerpoTecnicoEntityFromDatabase.getEquipo().getId());
        oMiembroCuerpoTecnicoRepository.deleteById(id);
        return id;
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            String nombre = DataGenerationHelper.getRadomCuerpoTecnicoName();
            String apellido = DataGenerationHelper.getRadomRadomCuerpoTecnicoSurname();
            LocalDate fechaNacimiento = DataGenerationHelper.getRandomYear();
            String nacionalidad = DataGenerationHelper.getRadomCountry();
            String titulo = DataGenerationHelper.getRadomRadomCuerpoTecnicoTitle();
            EquipoEntity equipo = oEquipoService.getOneRandom();
            oMiembroCuerpoTecnicoRepository.save(
                    new MiembroCuerpoTecnicoEntity(nombre, apellido, fechaNacimiento, nacionalidad, titulo, equipo));
        }
        return oEquipoRepository.count();
    }

    public MiembroCuerpoTecnicoEntity getOneRandom() {
        oSessionService.onlyAdmins();
        Pageable oPageable = PageRequest.of((int) (Math.random() * oMiembroCuerpoTecnicoRepository.count()), 1);
        return oMiembroCuerpoTecnicoRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oMiembroCuerpoTecnicoRepository.deleteAll();
        oMiembroCuerpoTecnicoRepository.resetAutoIncrement();
        oMiembroCuerpoTecnicoRepository.flush();
        return oMiembroCuerpoTecnicoRepository.count();
    }

}