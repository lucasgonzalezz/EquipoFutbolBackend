package net.ausiasmarch.equipo_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;

@Service
public class EquipoService {

    @Autowired
    EquipoRepository oEquipoRepository;

    public EquipoEntity get(Long id) {
        return oEquipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
    }

    public Long create(EquipoEntity oEquipoEntity) {
        oEquipoEntity.setId(null);
        return oEquipoRepository.save(oEquipoEntity).getId();
    }

    public EquipoEntity update(EquipoEntity oEquipoEntity) {
        return oEquipoRepository.save(oEquipoEntity);
    }

    public Long delete(Long id) {
        oEquipoRepository.deleteById(id);
        return id;
    }

    public Page<EquipoEntity> getPage(Pageable oPageable) {
        return oEquipoRepository.findAll(oPageable);
    }

    public EquipoEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oEquipoRepository.count()), 1);
        return oEquipoRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            oEquipoRepository.save(new EquipoEntity("nombre" + i, "ciudad" + i, new Date(), "estadio" + i, "liga"));
        }
        return oEquipoRepository.count();
    }

}
