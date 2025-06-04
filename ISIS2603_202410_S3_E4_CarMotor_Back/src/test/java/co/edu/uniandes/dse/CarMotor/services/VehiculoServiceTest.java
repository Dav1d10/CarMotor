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
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({VehiculoService.class, AsesorService.class})
class VehiculoServiceTest {
    
    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<VehiculoEntity> vehiculoList = new ArrayList<>();
    private SedeEntity sedeEntity;
    private AsesorEntity asesorEntity;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from VehiculoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from AsesorEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
    }

    private void insertData() {
        sedeEntity = factory.manufacturePojo(SedeEntity.class);
        entityManager.persist(sedeEntity);

        asesorEntity = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(asesorEntity);

        for (int i = 0; i < 3; i++) {
            VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
            vehiculoEntity.setSede(sedeEntity);
            vehiculoEntity.setAsesor(asesorEntity);
            entityManager.persist(vehiculoEntity);
            vehiculoList.add(vehiculoEntity);
        }
    }

    @Test
    void testCreateVehiculo() throws IllegalOperationException {
        VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
        newEntity.setSede(sedeEntity);
        newEntity.setAsesor(asesorEntity);

        VehiculoEntity result = vehiculoService.createVehiculo(newEntity);
        assertNotNull(result);

        VehiculoEntity entity = entityManager.find(VehiculoEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getMarca(), entity.getMarca());
        assertEquals(newEntity.getSerie(), entity.getSerie());
        assertEquals(newEntity.getUltimoPlaca(), entity.getUltimoPlaca());
        assertEquals(newEntity.getModelo(), entity.getModelo());
        assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        assertEquals(newEntity.getPrecio(), entity.getPrecio());
    }

    @Test
    void testCreateVehiculoWithInvalidSede() {
        assertThrows(IllegalOperationException.class, () -> {
            VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
            SedeEntity invalidSede = new SedeEntity();
            invalidSede.setId(0L);
            newEntity.setSede(invalidSede);
            newEntity.setAsesor(asesorEntity);
            vehiculoService.createVehiculo(newEntity);
        });
    }

    @Test
    void testCreateVehiculoWithNullSede() {
        assertThrows(IllegalOperationException.class, () -> {
            VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
            newEntity.setSede(null);
            newEntity.setAsesor(asesorEntity);
            vehiculoService.createVehiculo(newEntity);
        });
    }

    @Test
    void testCreateVehiculoWithInvalidAsesor() {
        assertThrows(IllegalOperationException.class, () -> {
            VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
            AsesorEntity invalidAsesor = new AsesorEntity();
            invalidAsesor.setId(0L);
            newEntity.setAsesor(invalidAsesor);
            newEntity.setSede(sedeEntity);
            vehiculoService.createVehiculo(newEntity);
        });
    }


    @Test
	void testCreateVehiculoithNullAsesor() {
		assertThrows(IllegalOperationException.class, () -> {
			VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
            newEntity.setAsesor(null);
			newEntity.setSede(sedeEntity);
			vehiculoService.createVehiculo(newEntity);
		});
	}


    @Test
	void testGetVehiculos() {
		List<VehiculoEntity> list = vehiculoService.verVehiculos();
		assertEquals(vehiculoList.size(), list.size());
		for (VehiculoEntity entity : list) {
			boolean found = false;
			for (VehiculoEntity storedEntity : vehiculoList) {
				if (entity.getId().equals(storedEntity.getId())) {
					found = true;
				}
			}
			assertTrue(found);
		}
	}


    @Test
	void testGetVehiculo() throws EntityNotFoundException {
		VehiculoEntity entity = vehiculoList.get(0);
		VehiculoEntity resultEntity = vehiculoService.getVehiculo(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getMarca(), resultEntity.getMarca());
        assertEquals(entity.getSerie(), resultEntity.getSerie());
        assertEquals(entity.getUltimoPlaca(), resultEntity.getUltimoPlaca());
        assertEquals(entity.getModelo(), resultEntity.getModelo());
        assertEquals(entity.getCapacidad(), resultEntity.getCapacidad());
        assertEquals(entity.getPrecio(), resultEntity.getPrecio());
	}


    @Test
	void testGetInvalidVehiculo() {
		assertThrows(javax.persistence.EntityNotFoundException.class,()->{
			vehiculoService.getVehiculo(0L);
		});
	}


    @Test
	void testUpdateVehiculo() throws EntityNotFoundException, IllegalOperationException {
		VehiculoEntity entity = vehiculoList.get(0);
		VehiculoEntity pojoEntity = factory.manufacturePojo(VehiculoEntity.class);
		pojoEntity.setId(entity.getId());
		vehiculoService.updateVehiculo(entity.getId(), pojoEntity);

		VehiculoEntity resp = entityManager.find(VehiculoEntity.class, entity.getId());
		assertEquals(pojoEntity.getId(), resp.getId());
		assertEquals(pojoEntity.getMarca(), resp.getMarca());
        assertEquals(pojoEntity.getSerie(), resp.getSerie());
        assertEquals(pojoEntity.getUltimoPlaca(), resp.getUltimoPlaca());
        assertEquals(pojoEntity.getModelo(), resp.getModelo());
        assertEquals(pojoEntity.getCapacidad(), resp.getCapacidad());
        assertEquals(pojoEntity.getPrecio(), resp.getPrecio());
	}


    @Test
	void testUpdateVehiculoInvalid() {
		assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
			VehiculoEntity pojoEntity = factory.manufacturePojo(VehiculoEntity.class);
			pojoEntity.setId(0L);
			vehiculoService.updateVehiculo(0L, pojoEntity);
		});
	}


    @Test
	void testDeleteVehiculo() throws EntityNotFoundException, IllegalOperationException {
		VehiculoEntity entity = vehiculoList.get(1);
		vehiculoService.deleteVehiculo(entity.getId());
		VehiculoEntity deleted = entityManager.find(VehiculoEntity.class, entity.getId());
		assertNull(deleted);
	}


    @Test
	void testDeleteInvalidVehiculo() {
		assertThrows(javax.persistence.EntityNotFoundException.class, ()->{
			vehiculoService.deleteVehiculo(0L);
		});
	}







    
}
