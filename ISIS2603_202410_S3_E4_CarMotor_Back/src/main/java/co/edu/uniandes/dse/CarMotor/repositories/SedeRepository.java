package co.edu.uniandes.dse.CarMotor.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;

@Repository
public interface SedeRepository extends JpaRepository<SedeEntity, Long>{
    Optional<SedeEntity> findByNombre(String nombre);
}
