package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.CarMotor.entities.EntidadBancariaEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(EntidadBancariaService.class)
public class EntidadBancariaServiceTest {

    @Autowired
	EntidadBancariaService entidadBancariaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<EntidadBancariaEntity> entidadBancariaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
            clearData();
            insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
            entityManager.getEntityManager().createQuery("delete from EntidadBancariaEntity");
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                EntidadBancariaEntity entidadBancariaEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                entityManager.persist(entidadBancariaEntity);
                entidadBancariaList.add(entidadBancariaEntity);

    }
    }
        
    @Test
    void testCreateEntidadBancaria() throws EntityNotFoundException, IllegalOperationException {
        EntidadBancariaEntity newEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
        newEntity.setIdEntidadBancaria("1");
        EntidadBancariaEntity result = entidadBancariaService.createEntidadBancaria(newEntity);
        assertNotNull(result);
        EntidadBancariaEntity entity = entityManager.find(EntidadBancariaEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getAsesorFinanciero(), entity.getAsesorFinanciero());
        assertEquals(newEntity.getTelefonoContacto(), entity.getTelefonoContacto());
        assertEquals(newEntity.getPoliza(), entity.getPoliza());
        assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test
    void testCreateEntidadBancariaWithNoValidId() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity newEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    newEntity.setIdEntidadBancaria("");
                    entidadBancariaService.createEntidadBancaria(newEntity);
            });
    }    

    @Test
    void testCreateEntidadBancariaWithNoValidId2() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity newEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    newEntity.setIdEntidadBancaria(null);
                    entidadBancariaService.createEntidadBancaria(newEntity);
            });
    }    

    @Test
    void testCreateEntidadBancariaWithStoredId() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity newEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    newEntity.setIdEntidadBancaria(entidadBancariaList.get(0).getIdEntidadBancaria());
                    entidadBancariaService.createEntidadBancaria(newEntity);
            });
    }    

    @Test
    void testGetEntidadesBancarias() {
            List<EntidadBancariaEntity> list = entidadBancariaService.getEntidadesBancarias();
            assertEquals(entidadBancariaList.size(), list.size());
            for (EntidadBancariaEntity entity : list) {
                    boolean found = false;
                    for (EntidadBancariaEntity storedEntity : entidadBancariaList) {
                            if (entity.getId().equals(storedEntity.getId())) {
                                    found = true;
                            }
                    }
                    assertTrue(found);
            }
    }    

    @Test
    void testGetEntidadBancaria() throws EntityNotFoundException {
            EntidadBancariaEntity entity = entidadBancariaList.get(0);
            EntidadBancariaEntity resultEntity = entidadBancariaService.getEntidadBancaria(entity.getId());
            assertNotNull(resultEntity);
            assertEquals(entity.getId(), resultEntity.getId());
            assertEquals(entity.getAsesorFinanciero(), resultEntity.getAsesorFinanciero());
            assertEquals(entity.getTelefonoContacto(), resultEntity.getTelefonoContacto());
            assertEquals(entity.getPoliza(), resultEntity.getPoliza());
            assertEquals(entity.getNombre(), resultEntity.getNombre());
    }    

    @Test
    void testGetInvalidEntidadBancaria() {
            assertThrows(EntityNotFoundException.class,()->{
                    entidadBancariaService.getEntidadBancaria(0L);
            });
    }    

    @Test
    void testUpdateEntidadBancaria() throws EntityNotFoundException, IllegalOperationException {
            EntidadBancariaEntity entity = entidadBancariaList.get(0);
            EntidadBancariaEntity pojoEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
            pojoEntity.setId(entity.getId());
            entidadBancariaService.updateEntidadBancaria(entity.getId(), pojoEntity);
    
            EntidadBancariaEntity resp = entityManager.find(EntidadBancariaEntity.class, entity.getId());
            assertEquals(pojoEntity.getId(), resp.getId());
            assertEquals(pojoEntity.getAsesorFinanciero(), resp.getAsesorFinanciero());
            assertEquals(pojoEntity.getTelefonoContacto(), resp.getTelefonoContacto());
            assertEquals(pojoEntity.getPoliza(), resp.getPoliza());
            assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }    

    @Test
    void testUpdateEntidadBancariaInvalid() {
            assertThrows(EntityNotFoundException.class, () -> {
                    EntidadBancariaEntity pojoEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    pojoEntity.setId(0L);
                    entidadBancariaService.updateEntidadBancaria(0L, pojoEntity);
            });
    }   
    
    @Test
    void testUpdateEntidadBancariaWithNoValidId() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity entity = entidadBancariaList.get(0);
                    EntidadBancariaEntity pojoEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    pojoEntity.setIdEntidadBancaria("");
                    pojoEntity.setId(entity.getId());
                    entidadBancariaService.updateEntidadBancaria(entity.getId(), pojoEntity);
            });
    }    

    @Test
    void testUpdateEntidadBancariaWithNoValidId2() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity entity = entidadBancariaList.get(0);
                    EntidadBancariaEntity pojoEntity = factory.manufacturePojo(EntidadBancariaEntity.class);
                    pojoEntity.setIdEntidadBancaria(null);
                    pojoEntity.setId(entity.getId());
                    entidadBancariaService.updateEntidadBancaria(entity.getId(), pojoEntity);
            });
    }    

    @Test
    void testDeleteEntidadBancaria() throws EntityNotFoundException, IllegalOperationException {
            EntidadBancariaEntity entity = entidadBancariaList.get(1);
            entidadBancariaService.deleteEntidadBancaria(entity.getId());
            EntidadBancariaEntity deleted = entityManager.find(EntidadBancariaEntity.class, entity.getId());
            assertNull(deleted);
    }    

    @Test
    void testDeleteInvalidEntidadBancaria() {
            assertThrows(EntityNotFoundException.class, ()->{
                    entidadBancariaService.deleteEntidadBancaria(0L);
            });
    }    

    @Test
    void testDeleteEntidadBancariaWithId() {
            assertThrows(IllegalOperationException.class, () -> {
                    EntidadBancariaEntity entity = entidadBancariaList.get(0);
                    entidadBancariaService.testDeleteEntidadBancariaWithId(entity.getId());
            });
    }    

}