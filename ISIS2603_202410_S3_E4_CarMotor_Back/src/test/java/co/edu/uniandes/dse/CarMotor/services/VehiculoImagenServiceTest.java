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
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.VehiculoImagenService;
import co.edu.uniandes.dse.CarMotor.services.ImagenService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({ VehiculoService.class, VehiculoImagenService.class })
class VehiculoImagenServiceTest {

    @Autowired
	private VehiculoImagenService vehiculoImagenService;

	@Autowired
	private TestEntityManager entityManager;

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
			ImagenEntity imagen = factory.manufacturePojo(ImagenEntity.class);
			entityManager.persist(imagen);
			imagenesList.add(imagen);
		}

		for (int i = 0; i < 3; i++) {
			VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);
			entityManager.persist(entity);
			vehiculosList.add(entity);
			if (i == 0) {
				imagenesList.get(i).setVehiculo(entity);
				entity.getImagenes().add(imagenesList.get(i));
			}
		}
	}

	/**
	 * Prueba para asociar una Imagen existente a un Vehiculo.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testAddImagen() throws EntityNotFoundException {
		VehiculoEntity entity = vehiculosList.get(0);
		ImagenEntity imagenEntity = imagenesList.get(1);
		ImagenEntity response = vehiculoImagenService.addImagen(imagenEntity.getId(), entity.getId());

		assertNotNull(response);
		assertEquals(imagenEntity.getId(), response.getId());
	}
	
	/**
	 * Prueba para asociar una imagen que no existe a un Vehiculo.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testAddInvalidImagen() {
		assertThrows(EntityNotFoundException.class, ()->{
			VehiculoEntity entity = vehiculosList.get(0);
			vehiculoImagenService.addImagen(0L, entity.getId());
		});
	}
	
	/**
	 * Prueba para asociar una Imagen a una Vehiculo que no existe.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	void testAddImagenInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class, ()->{
			ImagenEntity imagenEntity = imagenesList.get(1);
			vehiculoImagenService.addImagen(imagenEntity.getId(), 0L);
		});
	}

	/**
	 * Prueba para obtener una colección de instancias de Imagenes asociadas a una
	 * instancia Vehiculo.
	 * 
	 * @throws EntityNotFoundException
	 */

	@Test
	void testGetImagenes() throws EntityNotFoundException {
		List<ImagenEntity> list = vehiculoImagenService.getImagenes(vehiculosList.get(0).getId());
		assertEquals(1, list.size());
	}
	
	/**
	 * Prueba para obtener una colección de instancias de Imagenes asociadas a una
	 * instancia Vehiculo que no existe.
	 * 
	 * @throws EntityNotFoundException
	 */

	@Test
	void testGetImagenesInvalidVehiculo() {
		assertThrows(EntityNotFoundException.class,()->{
			vehiculoImagenService.getImagenes(0L);
		});
	}

	/**
	 * Prueba para obtener una instancia de Imagen asociada a una instancia Vehiculo.
	 * 
	 * @throws IllegalOperationException
	 * @throws EntityNotFoundException
	 *
	 * @throws co.edu.uniandes.csw.CarMotor.exceptions.BusinessLogicException
	 */
	@Test
	void testGetImagen() throws EntityNotFoundException, IllegalOperationException {
		VehiculoEntity entity = vehiculosList.get(0);
		ImagenEntity imagenEntity = imagenesList.get(0);
		ImagenEntity response = vehiculoImagenService.getImagen(entity.getId(), imagenEntity.getId());

		assertEquals(imagenEntity.getId(), response.getId());
		assertEquals(imagenEntity.getURL(), response.getURL());
        assertEquals(imagenEntity.getTipoImagen(), response.getTipoImagen());
	}
	
	/**
	 * Prueba para obtener una instancia de Imagen asociada a una instancia Vehiculo que no existe.
	 * 
	 * @throws EntityNotFoundException
	 *
	 */
	@Test
	void testGetImagenInvalidVehiculo()  {
		assertThrows(EntityNotFoundException.class, ()->{
			ImagenEntity imagenEntity = imagenesList.get(0);
			vehiculoImagenService.getImagen(0L, imagenEntity.getId());
		});
	}
	
	/**
	 * Prueba para obtener una instancia de Imagen que no existe asociada a una instancia Vehiculo.
	 * 
	 * @throws EntityNotFoundException
	 * 
	 */
	@Test
	void testGetInvalidImagen()  {
		assertThrows(EntityNotFoundException.class, ()->{
			VehiculoEntity entity = vehiculosList.get(0);
			vehiculoImagenService.getImagen(entity.getId(), 0L);
		});
	}

	/**
	 * Prueba para obtener una instancia de Imagenes asociada a una instancia Vehiculo
	 * que no le pertenece.
	 *
	 * @throws co.edu.uniandes.csw.CarMotor.exceptions.BusinessLogicException
	 */
	@Test
	public void getImagenNoAsociadoTest() {
		assertThrows(IllegalOperationException.class, () -> {
			VehiculoEntity entity = vehiculosList.get(0);
			ImagenEntity imagenEntity = imagenesList.get(1);
			vehiculoImagenService.getImagen(entity.getId(), imagenEntity.getId());
		});
	}

	/**
	 * Prueba para remplazar las instancias de Imagenes asociadas a una instancia de
	 * Vehiculo.
	 */
	@Test
	void testReplaceImagenes() throws EntityNotFoundException {
		VehiculoEntity entity = vehiculosList.get(0);
		List<ImagenEntity> list = imagenesList.subList(1, 3);
		vehiculoImagenService.replaceImagenes(entity.getId(), list);

		for (ImagenEntity imagen : list) {
			ImagenEntity b = entityManager.find(ImagenEntity.class, imagen.getId());
			assertTrue(b.getVehiculo().equals(entity));
		}
	}
	
	/**
	 * Prueba para remplazar las instancias de Imagenes que no existen asociadas a una instancia de
	 * Vehiculo.
	 */
	@Test
	void testReplaceInvalidImagenes() {
		assertThrows(EntityNotFoundException.class, ()->{
			VehiculoEntity entity = vehiculosList.get(0);
			
			List<ImagenEntity> imagenes = new ArrayList<>();
			ImagenEntity newImagen = factory.manufacturePojo(ImagenEntity.class);
			newImagen.setId(0L);
			imagenes.add(newImagen);
			
			vehiculoImagenService.replaceImagenes(entity.getId(), imagenes);
		});
	}
	
	/**
	 * Prueba para remplazar las instancias de Imagenes asociadas a una instancia de
	 * Vehiculo que no existe.
	 */
	@Test
	void testReplaceImagenesInvalidVehiculo() throws EntityNotFoundException {
		assertThrows(EntityNotFoundException.class, ()->{
			List<ImagenEntity> list = imagenesList.subList(1, 3);
			vehiculoImagenService.replaceImagenes(0L, list);
		});
	}
    
}
