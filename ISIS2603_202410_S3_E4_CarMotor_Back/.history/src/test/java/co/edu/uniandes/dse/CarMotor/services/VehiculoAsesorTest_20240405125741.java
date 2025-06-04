package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(VehiculoAsesorService.class)
public class VehiculoAsesorTest {

    @Autowired
    private VehiculoAsesorService vehiculoAsesorService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private AsesorEntity asesor = new AsesorEntity();

    private SedeEntity sede = new SedeEntity();

    private HorarioTestDriveEntity horarioTestDrive = new HorarioTestDriveEntity();

    private List<ImagenEntity> imagenesList = new ArrayList<>();

    private List<VehiculoEntity> vehiculoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from VehiculoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from AsesorEntity").executeUpdate();
    }

    private void insertData() {

        sede = factory.manufacturePojo(SedeEntity.class);
        entityManager.persist(sede);

        asesor = factory.manufacturePojo(AsesorEntity.class);
        asesor.setSede(sede);
        entityManager.persist(asesor);

        for (int i = 0; i < 3; i++) {
            VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(entity);
            entity.setAsesor(asesor);
            vehiculoList.add(entity);
        }
        asesor.setVehiculosAsignados(vehiculoList);
    }


    @Test
    void testAddAsesor() throws EntityNotFoundException, IllegalOperationException  {
        VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
        vehiculoEntity.setSede(sede);
        entityManager.persist(vehiculoEntity);
        AsesorEntity asesorEntity = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(asesorEntity);
        vehiculoAsesorService.addAsesor(asesorEntity.getId(), vehiculoEntity.getId());
        AsesorEntity lastAsesor = vehiculoAsesorService.getAsesor(vehiculoEntity.getId());
        assertEquals(asesorEntity.getId(), lastAsesor.getId());
        assertEquals(asesorEntity.getCorreo(), lastAsesor.getCorreo());
        assertEquals(asesorEntity.getFotografia(), lastAsesor.getFotografia());
        assertEquals(asesorEntity.getSede(), lastAsesor.getSede());
        assertEquals(asesorEntity.getNombre(), lastAsesor.getNombre());
        assertEquals(asesorEntity.getTelefono(), lastAsesor.getTelefono());
        assertEquals(asesorEntity.getVehiculosAsignados(), lastAsesor.getVehiculosAsignados());
    }

    @Test
    void testAddInvalidAsesor() {
        assertThrows(EntityNotFoundException.class, () -> {
            VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
            vehiculoEntity.setSede(sede);
            entityManager.persist(vehiculoEntity);
            vehiculoAsesorService.addAsesor(0L, vehiculoEntity.getId());
        });
    }


    @Test
    void testAddAsesorInvalidVehiculo() throws EntityNotFoundException, IllegalOperationException {
        assertThrows(EntityNotFoundException.class, () -> {
            AsesorEntity asesorEntity = factory.manufacturePojo(AsesorEntity.class);
            entityManager.persist(asesorEntity);
            vehiculoAsesorService.addAsesor(asesorEntity.getId(), 0L);
        });
    }


    @Test
    void testGetAsesor() throws EntityNotFoundException {
        AsesorEntity asesorEntity = vehiculoAsesorService.getAsesor(vehiculoList.get(0).getId());
        assertEquals(asesorEntity.getId(), asesor.getId());
    }


    @Test
	void testGetAsesorInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class, () -> {
			vehiculoAsesorService.getAsesor(0L);
		});
	}


    @Test
	void testGetAsesor2() throws EntityNotFoundException, IllegalOperationException {
		AsesorEntity asesorEntity = vehiculoList.get(0).getAsesor();
        AsesorEntity asesor = vehiculoAsesorService.getAsesor(vehiculoList.get(0).getId());
        assertNotNull(asesor);
        assertEquals(asesorEntity.getId(), asesor.getId());
        assertEquals(asesorEntity.getCorreo(), asesor.getCorreo());
        assertEquals(asesorEntity.getFotografia(), asesor.getFotografia());
        assertEquals(asesorEntity.getSede(), asesor.getSede());
        assertEquals(asesorEntity.getNombre(), asesor.getNombre());
        assertEquals(asesorEntity.getTelefono(), asesor.getTelefono());
        assertEquals(asesorEntity.getVehiculosAsignados(), asesor.getVehiculosAsignados());
	}

    @Test
    void testGetAsesores() throws EntityNotFoundException {
        List<VehiculoEntity> vehiculoEntities = asesorVehiculoService.getVehiculos(asesor.getId());
        assertEquals(vehiculoList.size(), vehiculoEntities.size());
        for (int i = 0; i < vehiculoList.size(); i++) {
            assertTrue(vehiculoEntities.contains(vehiculoList.get(0)));
        }
    }


    @Test
	void testGetInvalidAsesor() {
		assertThrows(EntityNotFoundException.class, ()->{
            vehiculoAsesorService.getAsesor(0L);
        });
	}


    @Test
	void testGetAsesorInvalidVehiculo2() {
		assertThrows(EntityNotFoundException.class,()->{
            AsesorEntity asesorEntity = vehiculoList.get(0).getAsesor();
			vehiculoAsesorService.getAsesor(0L);
		});
	}


    @Test
	void testGetNotAssociatedAsesor() {
		assertThrows(EntityNotFoundException.class, ()->{
            AsesorEntity asesorEntity = factory.manufacturePojo(AsesorEntity.class);
            asesorEntity.setSede(sede);
            entityManager.persist(asesorEntity);
            vehiculoAsesorService.getAsesor(asesorEntity.getId());
        });
	}


    @Test
	void testReplaceAsesor() throws EntityNotFoundException {
		AsesorEntity asesorEntity = new AsesorEntity();
        asesorEntity.setId((long) 1);
        AsesorEntity entity = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(entity);
        vehiculoAsesorService.replaceAsesor(entity.getId(), vehiculoList.get(0).getId());
        assertEquals(entity, vehiculoList.get(0).getAsesor());
	}

    @Test
	void testReplaceAsesorInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class, ()->{
        AsesorEntity asesorEntity = new AsesorEntity();
        AsesorEntity entity = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(entity);
        vehiculoList.get(0).setAsesor(entity);
        vehiculoAsesorService.replaceAsesor(asesorEntity.getId(), 0L);
		});
	}


    @Test
    void testReplaceInvalidAsesor() {
        assertThrows(EntityNotFoundException.class, ()->{
        AsesorEntity asesorEntity = new AsesorEntity();
        AsesorEntity entity = factory.manufacturePojo(AsesorEntity.class);
        entityManager.persist(entity);
        vehiculoList.get(0).setAsesor(entity);
        vehiculoAsesorService.replaceAsesor(0L, vehiculoList.get(0).getId());
		});
    }

    @Test
    void testRemoveAsesor() throws EntityNotFoundException {
        vehiculoAsesorService.removeAsesor(asesor.getId(), vehiculoList.get(0).getId());
		assertTrue(vehiculoList.get(0).getAsesor() == null);
    }


    @Test
    void testRemoveInvalidVehiculo() {
        assertThrows(EntityNotFoundException.class, ()->{
			vehiculoAsesorService.removeAsesor(asesor.getId(), 0L);
		});
    }


    @Test
    void testRemoveVehiculoInvalidAsesor() {
        assertThrows(EntityNotFoundException.class, ()->{
			vehiculoAsesorService.removeAsesor(0L, vehiculoList.get(0).getId());
		});
	}
    
}
