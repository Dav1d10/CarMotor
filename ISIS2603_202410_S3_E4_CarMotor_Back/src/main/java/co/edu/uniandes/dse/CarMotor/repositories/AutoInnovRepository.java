package co.edu.uniandes.dse.CarMotor.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.CarMotor.entities.AutoInnovEntity;


@Repository
public interface AutoInnovRepository extends JpaRepository<AutoInnovEntity, Long> {
}