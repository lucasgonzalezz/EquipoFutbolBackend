package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {

    Optional<EquipoEntity> findByUsername(String username);

    Optional<EquipoEntity> findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT e.*,count(j.id) FROM equipo e, jugador j WHERE e.id = j.id_equipo GROUP BY e.id ORDER BY COUNT(e.id) desc", nativeQuery = true)
    Page<EquipoEntity> findEquiposByJugadoresNumberDescFilter(Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE equipo AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}