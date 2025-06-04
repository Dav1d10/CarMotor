package co.edu.uniandes.dse.CarMotor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import java.util.List;

@Repository
public interface HorarioTestDriveRepository extends JpaRepository<HorarioTestDriveEntity, Long> {
    
    List<HorarioTestDriveEntity> findBySede(SedeEntity sede);
}
