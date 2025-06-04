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

import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(ImagenService.class)
class ImagenServiceTest {

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ImagenEntity> imagenList = new ArrayList<>();
    private List<VehiculoEntity> vehiculoList = new ArrayList<>();

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
            entityManager.getEntityManager().createQuery("delete from ImagenEntity");
            entityManager.getEntityManager().createQuery("delete from VehiculoEntity");
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
            for (int i = 0; i < 2; i++) {
                    VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
                    entityManager.persist(vehiculoEntity);
                    vehiculoList.add(vehiculoEntity);
            }

            for (int i = 0; i < 2; i++) {
                    ImagenEntity imagenEntity = factory.manufacturePojo(ImagenEntity.class);
                    imagenEntity.setVehiculo(vehiculoList.get(0));
                    entityManager.persist(imagenEntity);
                    imagenList.add(imagenEntity);
            }

    }

    @Test
    void testCreateImagen() throws EntityNotFoundException, IllegalOperationException {
            ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
            newEntity.setVehiculo(vehiculoList.get(0));
            ImagenEntity result = imagenService.createImagen(newEntity);
            assertNotNull(result);
            ImagenEntity entity = entityManager.find(ImagenEntity.class, result.getId());
            assertEquals(newEntity.getId(), entity.getId());
            assertEquals(newEntity.getURL(), entity.getURL());
            assertEquals(newEntity.getTipoImagen(), entity.getTipoImagen());
    }

    @Test
    void testCreateImagenWithNoValidURL() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
                    newEntity.setVehiculo(vehiculoList.get(0));
                    newEntity.setURL("");
                    imagenService.createImagen(newEntity);
            });
    }

    @Test
    void testCreateImagenWithStoredURL() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
                    newEntity.setVehiculo(vehiculoList.get(0));
                    newEntity.setURL(imagenList.get(0).getURL());
                    imagenService.createImagen(newEntity);
            });
    }

    @Test
    void testCreateImagenWithInvalidVehiculo() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
                    newEntity.setURL("www.abc.com");
                    VehiculoEntity vehiculoEntity = new VehiculoEntity();
                    vehiculoEntity.setId(0L);
                    newEntity.setVehiculo(vehiculoEntity);
                    imagenService.createImagen(newEntity);
            });
    }

    @Test
    void testCreateImagenWithNullVehiculo() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
                    newEntity.setURL("www.abc.com");
                    newEntity.setVehiculo(null);
                    imagenService.createImagen(newEntity);
            });
    }

    @Test
    void testGetImagenes() {
            List<ImagenEntity> list = imagenService.getImagenes();
            assertEquals(imagenList.size(), list.size());
            for (ImagenEntity entity : list) {
                    boolean found = false;
                    for (ImagenEntity storedEntity : imagenList) {
                            if (entity.getId().equals(storedEntity.getId())) {
                                    found = true;
                            }
                    }
                    assertTrue(found);
            }
    }

    @Test
    void testGetImagen() throws EntityNotFoundException {
            ImagenEntity entity = imagenList.get(0);
            ImagenEntity resultEntity = imagenService.getImagen(entity.getId());
            assertNotNull(resultEntity);
            assertEquals(entity.getURL(), resultEntity.getURL());
            assertEquals(entity.getTipoImagen(), resultEntity.getTipoImagen());
            ;
    }

    @Test
    void testGetInvalidImagen() {
            assertThrows(EntityNotFoundException.class,()->{
                    imagenService.getImagen(0L);
            });
    }

    @Test
    void testUpdateImagen() throws EntityNotFoundException, IllegalOperationException {
            ImagenEntity entity = imagenList.get(0);
            ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);
            pojoEntity.setId(entity.getId());
            imagenService.updateImagen(entity.getId(), pojoEntity);

            ImagenEntity resp = entityManager.find(ImagenEntity.class, entity.getId());
            assertEquals(pojoEntity.getURL(), resp.getURL());
            assertEquals(pojoEntity.getTipoImagen(), resp.getTipoImagen());
    }

    @Test
    void testUpdateImagenInvalid() {
            assertThrows(EntityNotFoundException.class, () -> {
                    ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);
                    pojoEntity.setId(0L);
                    imagenService.updateImagen(0L, pojoEntity);
            });
    }

    @Test
    void testUpdateImagenWithNoValidURL() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity entity = imagenList.get(0);
                    ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);
                    pojoEntity.setURL("");
                    pojoEntity.setId(entity.getId());
                    imagenService.updateImagen(entity.getId(), pojoEntity);
            });
    }

    @Test
    void testUpdateImagenWithNoValidIURL() {
            assertThrows(IllegalOperationException.class, () -> {
                    ImagenEntity entity = imagenList.get(0);
                    ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);
                    pojoEntity.setURL(null);
                    pojoEntity.setId(entity.getId());
                    imagenService.updateImagen(entity.getId(), pojoEntity);
            });
    }

    @Test
    void testDeleteImagen() throws EntityNotFoundException, IllegalOperationException {
            ImagenEntity entity = imagenList.get(1);
            imagenService.deleteImagen(entity.getId());
            ImagenEntity deleted = entityManager.find(ImagenEntity.class, entity.getId());
            assertNull(deleted);
    }

    @Test
    void testDeleteInvalidImagen() {
            assertThrows(EntityNotFoundException.class, ()->{
                    imagenService.deleteImagen(0L);
            });
    }

}
