package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.SedeAsesorService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(SedeAsesorService.class)
public class SedeAsesorServiceTest {

    @Autowired
    private SedeAsesorService sedeAsesorService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private SedeEntity sede = new SedeEntity();
    private List<AsesorEntity> asesorList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

   private void clearData() {
    // Eliminar relaciones y entidades de forma explícita para evitar problemas de integridad referencial.
    // Empieza con las entidades que no tienen dependencias o que son el "lado débil" de una relación.
    
    // Eliminar vehículos asignados a asesores, si es necesario
    List<VehiculoEntity> vehiculos = entityManager.getEntityManager().createQuery("select v from VehiculoEntity v", VehiculoEntity.class).getResultList();
    for (VehiculoEntity vehiculo : vehiculos) {
        entityManager.getEntityManager().remove(vehiculo);
    }
    
    // Eliminar asesores (asegurándose de que no queden referencias pendientes en vehículos)
    List<AsesorEntity> asesores = entityManager.getEntityManager().createQuery("select a from AsesorEntity a", AsesorEntity.class).getResultList();
    for (AsesorEntity asesor : asesores) {
        entityManager.getEntityManager().remove(asesor);
    }

    // Eliminar sedes (después de eliminar los asesores para mantener la integridad referencial)
    List<SedeEntity> sedes = entityManager.getEntityManager().createQuery("select s from SedeEntity s", SedeEntity.class).getResultList();
    for (SedeEntity sede : sedes) {
        entityManager.getEntityManager().remove(sede);
    }

    // Si hay otras entidades relacionadas que también necesiten ser limpiadas, sigue el mismo patrón aquí.
    
    entityManager.getEntityManager().flush(); // Asegurarse de que todos los cambios se aplican.
}


    private void insertData() {
        sede = factory.manufacturePojo(SedeEntity.class);
        entityManager.persist(sede);

        for (int i = 0; i < 3; i++) {
            AsesorEntity entity = factory.manufacturePojo(AsesorEntity.class);
            entity.setSede(sede);
            entityManager.persist(entity);
            asesorList.add(entity);
        }
    }


    // @Test
    // void testGetAsesoresBySede() throws EntityNotFoundException {
    //     List<AsesorEntity> asesorEntities = sedeAsesorService.getAsesoresBySede(sede.getId());

    //     assertEquals(asesorList.size(), asesorEntities.size());

    //     for (int i = 0; i < asesorList.size(); i++) {
    //         assertTrue(asesorEntities.contains(asesorList.get(i)));
    //     }
    // }


    @Test
    void testAddAsesorToSede() throws EntityNotFoundException {
        AsesorEntity newAsesor = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(newAsesor);

        AsesorEntity asesorEntity = sedeAsesorService.addAsesorToSede(sede.getId(), newAsesor.getId());
        assertNotNull(asesorEntity);

        assertEquals(asesorEntity.getId(), newAsesor.getId());
        assertEquals(asesorEntity.getNombre(), newAsesor.getNombre());
    }

    @Test
    void testRemoveAsesorFromSede() throws EntityNotFoundException {
        AsesorEntity asesorToRemove = asesorList.get(0);
        sedeAsesorService.removeAsesorFromSede(sede.getId(), asesorToRemove.getId());

        SedeEntity sedeEntity = entityManager.find(SedeEntity.class, sede.getId());
        assertFalse(sedeEntity.getAsesores().contains(asesorToRemove));
    }

}
