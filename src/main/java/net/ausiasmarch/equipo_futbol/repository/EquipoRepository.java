package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Long>{
    
}