package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.ausiasmarch.equipo_futbol.entity.MiembroCuerpoTecnicoEntity;

public interface MiembroCuerpoTecnicoRepository extends JpaRepository<MiembroCuerpoTecnicoEntity, Long> {

    Page<MiembroCuerpoTecnicoEntity> findByEquipoId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE miembro_cuerpo_tecnico AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}