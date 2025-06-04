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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(SedeService.class)
public class SedeServiceTest {

    @Autowired
    private SedeService sedeService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<SedeEntity> sedeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SedeEntity sedeEntity = factory.manufacturePojo(SedeEntity.class);
            entityManager.persist(sedeEntity);
            sedeList.add(sedeEntity);
        }
    }

    @Test
    void testCrearSede() throws IllegalOperationException {
        SedeEntity newEntity = factory.manufacturePojo(SedeEntity.class);
        SedeEntity result = sedeService.crearSede(newEntity);
        assertNotNull(result);

        SedeEntity entity = entityManager.find(SedeEntity.class, result.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    void testCrearSedeConNombreExistente() {
        SedeEntity firstEntity = sedeList.get(0);
        SedeEntity newEntity = factory.manufacturePojo(SedeEntity.class);
        newEntity.setNombre(firstEntity.getNombre());

        assertThrows(IllegalOperationException.class, () -> {
            sedeService.crearSede(newEntity);
        });
    }

    /* 
    @Test
    void testObtenerSedes() {
        List<String> list = sedeService.verSedes();
        assertEquals(sedeList.size(), list.size());
    }
    */

    @Test
    void testActualizarSede() throws EntityNotFoundException, IllegalOperationException {
        SedeEntity entity = sedeList.get(0);
        SedeEntity updatedEntity = factory.manufacturePojo(SedeEntity.class);
        updatedEntity.setId(entity.getId());
        sedeService.actualizarSede(entity.getId(), updatedEntity);

        SedeEntity resp = entityManager.find(SedeEntity.class, entity.getId());
        assertEquals(updatedEntity.getNombre(), resp.getNombre());
    }

    @Test
    void testEliminarSede() throws EntityNotFoundException, IllegalOperationException {
        SedeEntity entity = sedeList.get(1);
        sedeService.eliminarSede(entity.getId());
        SedeEntity deleted = entityManager.find(SedeEntity.class, entity.getId());
        assertNull(deleted);
    }

    @Test
    void testEliminarSedeNoExistente() {
        assertThrows(java.lang.IllegalStateException.class, () -> {
            sedeService.eliminarSede(Long.MAX_VALUE);
        });
    }

    @Test
    void testVerSedesInformacion() {
        List<String> infoList = sedeService.verSedesInformacion();
        assertEquals(sedeList.size(), infoList.size());

    }

}
