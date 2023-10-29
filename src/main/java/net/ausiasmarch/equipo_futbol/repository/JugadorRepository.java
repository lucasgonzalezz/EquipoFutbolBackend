package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.equipo_futbol.entity.JugadorEntity;

public interface JugadorRepository extends JpaRepository<JugadorEntity, Long>{
    
}
