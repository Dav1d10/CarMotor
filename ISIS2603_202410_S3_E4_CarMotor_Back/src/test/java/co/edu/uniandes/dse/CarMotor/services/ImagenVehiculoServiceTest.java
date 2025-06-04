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

import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.ImagenVehiculoService;
import co.edu.uniandes.dse.CarMotor.services.ImagenService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({ ImagenService.class, ImagenVehiculoService.class })

class ImagenVehiculoServiceTest {
    @Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ImagenVehiculoService imagenVehiculoService;

	@Autowired
	private ImagenService imagenService;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<VehiculoEntity> vehiculosList = new ArrayList<>();
	private List<ImagenEntity> imagenesList = new ArrayList<>();

	/**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ImagenEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from VehiculoEntity").executeUpdate();
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {
		for (int i = 0; i < 3; i++) {
			ImagenEntity imagenes = factory.manufacturePojo(ImagenEntity.class);
			entityManager.persist(imagenes);
			imagenesList.add(imagenes);
		}
		for (int i = 0; i < 3; i++) {
			VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
			entityManager.persist(entity);
			vehiculosList.add(entity);
			if (i == 0) {
				imagenesList.get(i).setVehiculo(entity);
			}
		}
	}

	/**
	 * Prueba para remplazar las instancias de Imagenes asociadas a una instancia de
	 * Vehiculo.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testReplaceVehiculo() throws EntityNotFoundException {
		ImagenEntity entity = imagenesList.get(0);
		imagenVehiculoService.replaceVehiculo(entity.getId(), vehiculosList.get(1).getId());
		entity = imagenService.getImagen(entity.getId());
		assertEquals(entity.getVehiculo(), vehiculosList.get(1));
	}
	
	/**
	 * Prueba para remplazar las instancias de Imagenes asociadas a una instancia de
	 * Vehiculo con una imagen que no existe
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testReplaceVehiculoInvalidImagen() {
		assertThrows(EntityNotFoundException.class, ()->{
			imagenVehiculoService.replaceVehiculo(0L, vehiculosList.get(1).getId());
		});
	}
	
	/**
	 * Prueba para remplazar las instancias de Imagenes asociadas a una instancia de
	 * Vehiculo que no existe.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testReplaceInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class, ()->{
			ImagenEntity entity = imagenesList.get(0);
			imagenVehiculoService.replaceVehiculo(entity.getId(), 0L);
		});
	}

	/**
	 * Prueba para desasociar una Imagen existente de un Vehiculo existente
	 * 
	 * @throws EntityNotFoundException
	 *
	 * @throws co.edu.uniandes.csw.CarMotor.exceptions.BusinessLogicException
	 */
	@Test
	void testRemoveVehiculo() throws EntityNotFoundException {
		imagenVehiculoService.removeVehiculo(imagenesList.get(0).getId());
		ImagenEntity response = imagenService.getImagen(imagenesList.get(0).getId());
		assertNull(response.getVehiculo());
	}
	
	/**
	 * Prueba para desasociar una Imagen que no existe de un Vehiculo
	 * 
	 * @throws EntityNotFoundException
	 *
	 * @throws co.edu.uniandes.csw.CarMotor.exceptions.BusinessLogicException
	 */
	@Test
	void testRemoveVehiculoInvalidImagen() throws EntityNotFoundException {
		assertThrows(EntityNotFoundException.class, ()->{
			imagenVehiculoService.removeVehiculo(0L);
		});
	}
	

}
