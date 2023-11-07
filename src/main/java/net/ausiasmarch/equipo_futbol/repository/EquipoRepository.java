package net.ausiasmarch.equipo_futbol.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {

    Optional<EquipoEntity> findByUsername(String username);

    Optional<EquipoEntity> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "ALTER TABLE equipo AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}