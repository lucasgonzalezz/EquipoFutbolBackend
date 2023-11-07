package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.ausiasmarch.equipo_futbol.entity.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<JugadorEntity, Long> {

    Page<JugadorEntity> findByEquipoId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE jugador AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}