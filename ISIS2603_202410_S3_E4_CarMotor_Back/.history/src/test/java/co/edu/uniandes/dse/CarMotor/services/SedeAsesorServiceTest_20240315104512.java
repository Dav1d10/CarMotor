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
        entityManager.getEntityManager().createQuery("delete from AsesorEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
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
    void testGetAsesoresBySede() throws EntityNotFoundException {
        List<AsesorEntity> asesorEntities = sedeAsesorService.getAsesoresBySede(sede.getAsesores());

        assertEquals(asesorList.size(), asesorEntities.size());

        for (int i = 0; i < asesorList.size(); i++) {
            assertTrue(asesorEntities.contains(asesorList.get(i)));
        }
    }

    @Test
    void testRemoveAsesorFromSede() throws EntityNotFoundException {
        AsesorEntity asesorToRemove = asesorList.get(0);
        sedeAsesorService.removeAsesorFromSede(sede.getId(), asesorToRemove.getId());

        SedeEntity sedeEntity = entityManager.find(SedeEntity.class, sede.getId());
        assertFalse(sedeEntity.getAsesores().contains(asesorToRemove));
    }

}
