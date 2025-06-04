package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(HorarioTestDriveService.class)
class HorarioTestDriveServiceTest {

    @Autowired
    private HorarioTestDriveService horarioTestDriveService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HorarioTestDriveEntity> horarioList = new ArrayList<>();
    private List<SedeEntity> sedeList = new ArrayList<>();
    private List<VehiculoEntity> vehiculoList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from HorarioTestDriveEntity");
        entityManager.getEntityManager().createQuery("delete from SedeEntity");
        entityManager.getEntityManager().createQuery("delete from VehiculoEntity");
    }
    private void insertData() {
        for (int i = 0; i < 2; i++) {
            SedeEntity sedeEntity = factory.manufacturePojo(SedeEntity.class);
            entityManager.persist(sedeEntity);
            sedeList.add(sedeEntity);
        }
    
        for (int i = 0; i < 2; i++) {
            HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            horarioEntity.setSede(sedeList.get(i)); 
            entityManager.persist(horarioEntity);
            horarioList.add(horarioEntity);
        }
    
        for (int i = 0; i < 2; i++) {
            VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(vehiculoEntity);
            vehiculoList.add(vehiculoEntity);
        }
    }

    @Test
    void testCreateHorarioTestDrive() throws EntityNotFoundException, IllegalOperationException {
        HorarioTestDriveEntity newEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
        newEntity.setSede(sedeList.get(0));
        newEntity.setVehiculosDisponibles(new ArrayList<>()); 
    
        HorarioTestDriveEntity result = horarioTestDriveService.createHorarioTestDrive(newEntity);
        assertNotNull(result);
    
        HorarioTestDriveEntity entity = entityManager.find(HorarioTestDriveEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(0, entity.getVehiculosDisponibles().size()); 
        assertEquals(newEntity.getFecha(), entity.getFecha());
        assertEquals(newEntity.getSede(), entity.getSede());
    }

    @Test
    void testCreateHorarioTestDriveWithNoValidDate() {
        assertThrows(IllegalOperationException.class, () -> {
            HorarioTestDriveEntity newEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            newEntity.setSede(sedeList.get(0));
            newEntity.setFecha(null);
            horarioTestDriveService.createHorarioTestDrive(newEntity);
        });
    }

    @Test
    void testCreateHorarioTestDriveWithInvalidSede() {
        assertThrows(IllegalOperationException.class, () -> {
            HorarioTestDriveEntity newEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            newEntity.setFecha("20/02/2024");
            SedeEntity sedeEntity = new SedeEntity();
            sedeEntity.setId(0L);
            newEntity.setSede(sedeEntity);
            horarioTestDriveService.createHorarioTestDrive(newEntity);
        });
    }

    @Test
    void testCreateHorarioTestDriveWithNullSede() {
        assertThrows(IllegalOperationException.class, () -> {
            HorarioTestDriveEntity newEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            newEntity.setFecha("20/02/2024");
            newEntity.setSede(null);
            horarioTestDriveService.createHorarioTestDrive(newEntity);
        });
    }
    @Test
    void testCreateHorarioTestDriveWithNullVehiculo() {
        assertThrows(IllegalOperationException.class, () -> {
            HorarioTestDriveEntity newEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            newEntity.setSede(sedeList.get(0));         
            newEntity.setVehiculosDisponibles(null); 
            horarioTestDriveService.createHorarioTestDrive(newEntity);
        });
    }

    
    @Test
    void testGetHorariosTestDrive() {
        for (int i = 0; i < 3; i++) {
            HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            horarioEntity.setSede(sedeList.get(0));
            entityManager.persist(horarioEntity);
            horarioList.add(horarioEntity);
        }
    
        List<HorarioTestDriveEntity> retrievedHorarios = horarioTestDriveService.getAllHorariosTestDrive();
    
        assertEquals(horarioList.size(), retrievedHorarios.size());
    
        for (HorarioTestDriveEntity persistedHorario : horarioList) {
            assertTrue(retrievedHorarios.contains(persistedHorario));
        }
    }
    


    @Test
    void testGetHorarioTestDrive() throws EntityNotFoundException {
        HorarioTestDriveEntity entity = horarioList.get(0);
        HorarioTestDriveEntity resultEntity = horarioTestDriveService.getHorarioTestDriveById(entity.getId());
        assertNotNull(resultEntity);
        assertEquals(entity.getFecha(), resultEntity.getFecha());
        assertEquals(entity.getSede(), resultEntity.getSede());
    }

    @Test
    void testGetInvalidHorarioTestDrive() {
        assertThrows(EntityNotFoundException.class, () -> {
            horarioTestDriveService.getHorarioTestDriveById(0L);
        });
    }
     
    @Test
    void testUpdateHorarioTestDrive() throws EntityNotFoundException, IllegalOperationException {
    
        HorarioTestDriveEntity entity = horarioList.get(0);

        HorarioTestDriveEntity pojoEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
        pojoEntity.setId(entity.getId());

        pojoEntity.setFecha("Nueva fecha");

        horarioTestDriveService.updateHorarioTestDrive(pojoEntity.getId(), pojoEntity);

        HorarioTestDriveEntity updatedEntity = entityManager.find(HorarioTestDriveEntity.class, entity.getId());

        assertEquals(pojoEntity.getId(), updatedEntity.getId());
        assertEquals(pojoEntity.getFecha(), updatedEntity.getFecha());
}

    @Test
    void testUpdateHorarioTestDriveInvalid() {
        assertThrows(EntityNotFoundException.class, () -> {
            HorarioTestDriveEntity pojoEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            pojoEntity.setId(0L);
            horarioTestDriveService.updateHorarioTestDrive(0L, pojoEntity);
    });
}

@Test
    void testUpdateHorarioTestDriveWithNoValidDate() {
        assertThrows(IllegalOperationException.class, () -> {
            HorarioTestDriveEntity entity = horarioList.get(0);
            HorarioTestDriveEntity pojoEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            pojoEntity.setFecha(null);
            pojoEntity.setId(entity.getId());
            horarioTestDriveService.updateHorarioTestDrive(entity.getId(), pojoEntity);
    });
    }
    
   
    @Test
    void testDeleteHorarioTestDrive() throws EntityNotFoundException, IllegalOperationException {
        HorarioTestDriveEntity entity = horarioList.get(1);
        horarioTestDriveService.deleteHorarioTestDrive(entity.getId());
        HorarioTestDriveEntity deleted = entityManager.find(HorarioTestDriveEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    void testDeleteInvalidHorarioTestDrive() {
        assertThrows(EntityNotFoundException.class, () -> {
            horarioTestDriveService.deleteHorarioTestDrive(0L);
        });
    }
    
}