package co.edu.uniandes.dse.CarMotor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;

@Repository
public interface AsesorRepository extends JpaRepository<AsesorEntity, Long> {
    List<AsesorEntity> findByNombre(String nombre);
}