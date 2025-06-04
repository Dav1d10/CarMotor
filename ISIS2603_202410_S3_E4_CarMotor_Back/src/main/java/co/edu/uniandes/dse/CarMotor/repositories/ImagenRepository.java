package co.edu.uniandes.dse.CarMotor.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;

@Repository
public interface ImagenRepository extends JpaRepository<ImagenEntity, Long> {
    List<ImagenEntity> findByURL(String url);
}