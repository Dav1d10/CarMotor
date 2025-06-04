package co.edu.uniandes.dse.CarMotor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.CarMotor.entities.EntidadBancariaEntity;

@Repository
public interface EntidadBancariaRepository extends JpaRepository<EntidadBancariaEntity, Long> {

    List<EntidadBancariaEntity> findByIdEntidadBancaria(String id);

}



