package co.edu.uniandes.dse.CarMotor.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Transactional
@Import({ TestDriveSedeService.class, HorarioTestDriveService.class, SedeService.class })
class TestDriveSedeServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestDriveSedeService testDriveSedeService;

    @Autowired
    private HorarioTestDriveService horarioTestDriveService;



    private PodamFactory factory = new PodamFactoryImpl();

    private List<HorarioTestDriveEntity> horarioList = new ArrayList<>();
    private List<SedeEntity> sedeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

   
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from HorarioTestDriveEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
    }

    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SedeEntity sedeEntity = factory.manufacturePojo(SedeEntity.class);
            entityManager.persist(sedeEntity);
            sedeList.add(sedeEntity);
    
            HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            horarioEntity.setSede(sedeEntity);
            entityManager.persist(horarioEntity);
            horarioList.add(horarioEntity);
        }
    }

    @Test
    void testAsociarHorarioTestDriveASede() throws EntityNotFoundException {
    SedeEntity sedeEntity = sedeList.get(1);
    HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
    entityManager.persist(horarioEntity); 
    testDriveSedeService.asociarHorarioConSede(horarioEntity.getId(), sedeEntity.getId());
    horarioEntity = horarioTestDriveService.getHorarioTestDriveById(horarioEntity.getId());
    assertEquals(sedeEntity, horarioEntity.getSede());
}

    @Test
    void testAsociarHorarioTestDriveASedeNoExistente() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveSedeService.asociarHorarioConSede(horarioList.get(0).getId(), 0L);
        });
    }


    @Test
    void testAsociarHorarioTestDriveNoExistenteASede() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveSedeService.asociarHorarioConSede(0L, sedeList.get(0).getId());
        });
    }
    @Test
    void testObtenerHorariosPorSede() throws EntityNotFoundException, IllegalOperationException {
   
        SedeEntity sedeEntity = factory.manufacturePojo(SedeEntity.class);
        entityManager.persist(sedeEntity);  
        HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
        horarioEntity.setSede(sedeEntity);
        entityManager.persist(horarioEntity);

        try {
            List<HorarioTestDriveEntity> horariosPorSede = testDriveSedeService.obtenerHorariosPorSede(sedeEntity.getId());
            assertNotNull(horariosPorSede);
            assertTrue(horariosPorSede.contains(horarioEntity));
        } catch (IllegalOperationException | EntityNotFoundException e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testObtenerHorariosPorSedeNoExistente() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveSedeService.obtenerHorariosPorSede(0L);
        });
    }
  
}