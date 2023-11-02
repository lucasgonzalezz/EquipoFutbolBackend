package net.ausiasmarch.equipo_futbol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    EquipoRepository oEquipoRepository;

    public EquipoEntity get(Long id) {
        return oEquipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Long create(EquipoEntity oEquipoEntity) {
        oEquipoEntity.setId(null);
        oEquipoEntity.setPassword("e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e");
        return oEquipoRepository.save(oEquipoEntity).getId();
    }

    public EquipoEntity update(EquipoEntity oEquipoEntity) {
        return oEquipoRepository.save(oEquipoEntity);
    }

}
