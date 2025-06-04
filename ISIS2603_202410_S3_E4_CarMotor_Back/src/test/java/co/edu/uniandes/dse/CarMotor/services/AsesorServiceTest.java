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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.AutoInnovEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(AsesorService.class)

class AsesorServiceTest {
    
    @Autowired
    private AsesorService asesorService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<AsesorEntity> asesorList = new ArrayList<>();

    private SedeEntity sedeEntity;

	private AutoInnovEntity autoInnovEntity;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from AsesorEntity");
        entityManager.getEntityManager().createQuery("delete from SedeEntity");
		entityManager.getEntityManager().createQuery("delete from VehiculoEntity");
	}


    private void insertData() {

		autoInnovEntity = factory.manufacturePojo(AutoInnovEntity.class);
		entityManager.persist(autoInnovEntity);
		
		sedeEntity = factory.manufacturePojo(SedeEntity.class);
		sedeEntity.setAutoInnovEntity(autoInnovEntity);
		entityManager.persist(sedeEntity);

		for (int i = 0; i < 3; i++) {
			AsesorEntity asesorEntity = factory.manufacturePojo(AsesorEntity.class);
			asesorEntity.setSede(sedeEntity);
			entityManager.persist(asesorEntity);
			asesorList.add(asesorEntity);
		}

		VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
		entityManager.persist(vehiculoEntity);
		
		AsesorEntity asesor = asesorList.get(0);
		vehiculoEntity.setAsesor(asesor);
		asesor.getVehiculosAsignados().add(vehiculoEntity);

	}


    @Test
    void testCreateAsesor() throws IllegalOperationException {
        AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
		newEntity.setSede(sedeEntity);
		newEntity.setNombre("David Caro");
		AsesorEntity result = asesorService.createAsesor(newEntity);
		assertNotNull(result);
		AsesorEntity entity = entityManager.find(AsesorEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getCorreo(), entity.getCorreo());
        assertEquals(newEntity.getTelefono(), entity.getTelefono());
		assertEquals(newEntity.getFotografia(), entity.getFotografia());

    }


    @Test
    void testCreateAsesorWithNoValidName() {
        assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
			newEntity.setSede(sedeEntity);
			newEntity.setNombre("");
			asesorService.createAsesor(newEntity);
		});
    }


    @Test
    void testCreateAsesorWithNoValidName2() {
        assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
			newEntity.setSede(sedeEntity);
			newEntity.setNombre(null);
			asesorService.createAsesor(newEntity);
		});
    }


    @Test
	void testCreateAsesorWithStoredName() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
			newEntity.setSede(sedeEntity);
			newEntity.setNombre(asesorList.get(0).getNombre());
			asesorService.createAsesor(newEntity);
		});
	}


    @Test
	void testCreateAsesorWithInvalidSede() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
			newEntity.setNombre("David Caro");
			SedeEntity sedeEntity = new SedeEntity();
			sedeEntity.setId(0L);
			newEntity.setSede(sedeEntity);
			asesorService.createAsesor(newEntity);
		});
	}


    @Test
	void testCreateAsesorWithNullSede() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity newEntity = factory.manufacturePojo(AsesorEntity.class);
			newEntity.setNombre("David Caro");
			newEntity.setSede(null);
			asesorService.createAsesor(newEntity);
		});
	}


    @Test
	void testGetAsesores() {
		List<AsesorEntity> list = asesorService.getAsesores();
		assertEquals(asesorList.size(), list.size());
		for (AsesorEntity entity : list) {
			boolean found = false;
			for (AsesorEntity storedEntity : asesorList) {
				if (entity.getId().equals(storedEntity.getId())) {
					found = true;
				}
			}
			assertTrue(found);
		}
	}


    @Test
	void testGetAsesor() throws EntityNotFoundException {
		AsesorEntity entity = asesorList.get(0);
		AsesorEntity resultEntity = asesorService.getAsesor(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getNombre(), resultEntity.getNombre());
        assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        assertEquals(entity.getTelefono(), resultEntity.getTelefono());
		assertEquals(entity.getFotografia(), resultEntity.getFotografia());
	}


    @Test
	void testGetInvalidAsesor() {
		assertThrows(EntityNotFoundException.class,()->{
			asesorService.getAsesor(0L);
		});
	}


    @Test
	void testUpdateAsesor() throws EntityNotFoundException, IllegalOperationException {
		AsesorEntity entity = asesorList.get(0);
		AsesorEntity pojoEntity = factory.manufacturePojo(AsesorEntity.class);
		pojoEntity.setId(entity.getId());
		asesorService.updateAsesor(entity.getId(), pojoEntity);

		AsesorEntity resp = entityManager.find(AsesorEntity.class, entity.getId());
		assertEquals(pojoEntity.getId(), resp.getId());
		assertEquals(pojoEntity.getNombre(), resp.getNombre());
		assertEquals(pojoEntity.getCorreo(), resp.getCorreo());
		assertEquals(pojoEntity.getTelefono(), resp.getTelefono());
		assertEquals(pojoEntity.getFotografia(), resp.getFotografia());
	}


    @Test
	void testUpdateAsesorInvalid() {
		assertThrows(EntityNotFoundException.class, () -> {
			AsesorEntity pojoEntity = factory.manufacturePojo(AsesorEntity.class);
			pojoEntity.setId(0L);
			asesorService.updateAsesor(0L, pojoEntity);
		});
	}


    @Test
	void testUpdateAsesorWithNoValidName() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity entity = asesorList.get(0);
			AsesorEntity pojoEntity = factory.manufacturePojo(AsesorEntity.class);
			pojoEntity.setNombre("");
			pojoEntity.setId(entity.getId());
			asesorService.updateAsesor(entity.getId(), pojoEntity);
		});
	}


    @Test
	void testUpdateAsesorWithNoValidName2() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity entity = asesorList.get(0);
			AsesorEntity pojoEntity = factory.manufacturePojo(AsesorEntity.class);
			pojoEntity.setNombre(null);
			pojoEntity.setId(entity.getId());
			asesorService.updateAsesor(entity.getId(), pojoEntity);
		});
	}


    @Test
	void testDeleteAsesor() throws EntityNotFoundException, IllegalOperationException {
		AsesorEntity entity = asesorList.get(1);
		asesorService.deleteAsesor(entity.getId());
		AsesorEntity deleted = entityManager.find(AsesorEntity.class, entity.getId());
		assertNull(deleted);
	}


    @Test
	void testDeleteInvalidAsesor() {
		assertThrows(EntityNotFoundException.class, ()->{
			asesorService.deleteAsesor(0L);
		});
	}


    @Test
	void testDeleteAsesorWithVehiculo() {
		assertThrows(IllegalOperationException.class, () -> {
			AsesorEntity entity = asesorList.get(0);
			asesorService.deleteAsesor(entity.getId());
		});
	}






    
}
