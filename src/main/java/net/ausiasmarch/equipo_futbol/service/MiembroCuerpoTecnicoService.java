package net.ausiasmarch.equipo_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.equipo_futbol.entity.MiembroCuerpoTecnicoEntity;
import net.ausiasmarch.equipo_futbol.repository.MiembroCuerpoTecnicoRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;

@Service
public class MiembroCuerpoTecnicoService {

    @Autowired
    MiembroCuerpoTecnicoRepository oMiembroCuerpoTecnicoRepository;

    public MiembroCuerpoTecnicoEntity get(Long id) {
        return oMiembroCuerpoTecnicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Miembro de Cuerpo Tecnico not found"));
    }

    public Long create(MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        oMiembroCuerpoTecnicoEntity.setId(null);
        return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity).getId();
    }

    public MiembroCuerpoTecnicoEntity update(MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        return oMiembroCuerpoTecnicoRepository.save(oMiembroCuerpoTecnicoEntity);
    }

    public Long delete(Long id) {
        oMiembroCuerpoTecnicoRepository.deleteById(id);
        return id;
    }

    public Page<MiembroCuerpoTecnicoEntity> getPage(Pageable oPageable) {
        return oMiembroCuerpoTecnicoRepository.findAll(oPageable);
    }

    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            oMiembroCuerpoTecnicoRepository.save(new MiembroCuerpoTecnicoEntity("nombre" + i, "apellido" + i, new Date(), "nacionalidad" + i, "titulo"));
        }
        return oMiembroCuerpoTecnicoRepository.count();
    }
    
}
