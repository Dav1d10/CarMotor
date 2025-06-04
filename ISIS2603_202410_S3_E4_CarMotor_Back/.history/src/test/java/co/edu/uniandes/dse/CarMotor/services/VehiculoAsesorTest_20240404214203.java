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
            entity.getAsesor();
            vehiculoList.add(entity);
            asesor.getVehiculosAsignados().add(entity);
        }
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
        assertEquals(asesorEntity.getTipo(), lastAsesor.getTipo());
        assertEquals(asesorEntity.getCapacidad(), lastAsesor.getCapacidad());
        assertEquals(asesorEntity.getPrecio(), lastAsesor.getPrecio());
    }

    @Test
    void testAddInvalidVehiculo() {
        assertThrows(EntityNotFoundException.class, () -> {
            AsesorEntity newAsesor = factory.manufacturePojo(AsesorEntity.class);
            newAsesor.setSede(sede);
            entityManager.persist(newAsesor);
            asesorVehiculoService.addVehiculo(newAsesor.getId(), 0L);
        });
    }


    @Test
    void testAddVehiculoInvalidAsesor() throws EntityNotFoundException, IllegalOperationException {
        assertThrows(EntityNotFoundException.class, () -> {
            VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(vehiculo);
            asesorVehiculoService.addVehiculo(0L, vehiculo.getId());
        });
    }


    @Test
    void testGetVehiculos() throws EntityNotFoundException {
        List<VehiculoEntity> vehiculoEntities = asesorVehiculoService.getVehiculos(asesor.getId());
        assertEquals(vehiculoList.size(), vehiculoEntities.size());
        for (int i = 0; i < vehiculoList.size(); i++) {
            assertTrue(vehiculoEntities.contains(vehiculoList.get(0)));
        }
    }


    @Test
	void testGetVehiculosInvalidAsesor() {
		assertThrows(EntityNotFoundException.class, () -> {
			asesorVehiculoService.getVehiculos(0L);
		});
	}


    @Test
	void testGetVehiculo() throws EntityNotFoundException, IllegalOperationException {
		VehiculoEntity vehiculoEntity = vehiculoList.get(0);
        VehiculoEntity vehiculo = asesorVehiculoService.getVehiculo(asesor.getId(), vehiculoEntity.getId());
        assertNotNull(vehiculo);
        assertEquals(vehiculoEntity.getId(), vehiculo.getId());
        assertEquals(vehiculoEntity.getMarca(), vehiculo.getMarca());
        assertEquals(vehiculoEntity.getSerie(), vehiculo.getSerie());
        assertEquals(vehiculoEntity.getUltimoPlaca(), vehiculo.getUltimoPlaca());
        assertEquals(vehiculoEntity.getModelo(), vehiculo.getModelo());
        assertEquals(vehiculoEntity.getTipo(), vehiculo.getTipo());
        assertEquals(vehiculoEntity.getCapacidad(), vehiculo.getCapacidad());
        assertEquals(vehiculoEntity.getPrecio(), vehiculo.getPrecio());
	}


    @Test
	void testGetInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class, ()->{
            asesorVehiculoService.getVehiculo(asesor.getId(), 0L);
        });
	}


    @Test
	void testGetVehiculoInvalidAsesor() {
		assertThrows(EntityNotFoundException.class,()->{
            VehiculoEntity vehiculoEntity = vehiculoList.get(0);
			asesorVehiculoService.getVehiculo(0L, vehiculoEntity.getId());
		});
	}


    @Test
	void testGetNotAssociatedVehiculo() {
		assertThrows(IllegalOperationException.class, ()->{
            AsesorEntity newAsesor = factory.manufacturePojo(AsesorEntity.class);
            newAsesor.setSede(sede);
            entityManager.persist(newAsesor);
            VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(vehiculo);
            asesorVehiculoService.getVehiculo(newAsesor.getId(), vehiculo.getId());
        });
	}


    @Test
	void testReplaceVehiculos() throws EntityNotFoundException {
		List<VehiculoEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(entity);
            asesor.getVehiculosAsignados().add(entity);
            nuevaLista.add(entity);
        }
        asesorVehiculoService.replaceVehiculos(asesor.getId(), nuevaLista);
        List<VehiculoEntity> vehiculoEntities = asesorVehiculoService.getVehiculos(asesor.getId());
        for (VehiculoEntity aNuevaLista : nuevaLista) {
            assertTrue(vehiculoEntities.contains(aNuevaLista));
        }
	}


    @Test
	void testReplaceVehiculos2() throws EntityNotFoundException {
		List<VehiculoEntity> nuevaLista = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
			entityManager.persist(entity);
			nuevaLista.add(entity);
		}
		asesorVehiculoService.replaceVehiculos(asesor.getId(), nuevaLista);
		
		List<VehiculoEntity> vehiculoEntities = asesorVehiculoService.getVehiculos(asesor.getId());
		for (VehiculoEntity aNuevaLista : nuevaLista) {
			assertTrue(vehiculoEntities.contains(aNuevaLista));
		}
	}


    @Test
	void testReplaceVehiculosInvalidAsesor() {
		assertThrows(EntityNotFoundException.class, ()->{
			List<VehiculoEntity> nuevaLista = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
				entity.getAsesor();		
				entityManager.persist(entity);
				nuevaLista.add(entity);
			}
			asesorVehiculoService.replaceVehiculos(0L, nuevaLista);
		});
	}


    @Test
    void testReplaceInvalidVehiculos() {
        assertThrows(EntityNotFoundException.class, ()->{
			List<VehiculoEntity> nuevaLista = new ArrayList<>();
			VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
			entity.setId(0L);
			nuevaLista.add(entity);
			asesorVehiculoService.replaceVehiculos(asesor.getId(), nuevaLista);
		});
    }


    @Test
    void testReplaceVehiculosInvalidVehiculo() {
        assertThrows(EntityNotFoundException.class, ()->{
			List<VehiculoEntity> nuevaLista = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
				entity.getAsesor(); // posible error		
				entityManager.persist(entity);
				nuevaLista.add(entity);
			}
			asesorVehiculoService.replaceVehiculos(0L, nuevaLista);
		});
    }


    @Test
    void testRemoveVehiculo() throws EntityNotFoundException {
        for (VehiculoEntity vehiculo : vehiculoList) {
			asesorVehiculoService.removeVehiculo(asesor.getId(), vehiculo.getId());
		}
		assertTrue(asesorVehiculoService.getVehiculos(asesor.getId()).isEmpty());
    }


    @Test
    void testRemoveInvalidVehiculo() {
        assertThrows(EntityNotFoundException.class, ()->{
			asesorVehiculoService.removeVehiculo(asesor.getId(), 0L);
		});
    }


    @Test
    void testRemoveVehiculoInvalidAsesor() {
        assertThrows(EntityNotFoundException.class, ()->{
			asesorVehiculoService.removeVehiculo(0L, vehiculoList.get(0).getId());
		});
	}
    
}
