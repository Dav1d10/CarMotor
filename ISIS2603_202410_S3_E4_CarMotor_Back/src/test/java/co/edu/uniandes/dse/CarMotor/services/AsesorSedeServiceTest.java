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

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({AsesorService.class, AsesorSedeService.class})

class AsesorSedeServiceTest {

    @Autowired
    private AsesorSedeService asesorSedeService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AsesorService asesorService;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<SedeEntity> sedesList = new ArrayList<>();

    private List<AsesorEntity> asesoresList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }


    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from AsesorEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
    }


    private void insertData() {
        for (int i = 0; i < 3; i++) {
			AsesorEntity asesores = factory.manufacturePojo(AsesorEntity.class);
			entityManager.persist(asesores);
			asesoresList.add(asesores);
		}
		for (int i = 0; i < 3; i++) {
			SedeEntity entity = factory.manufacturePojo(SedeEntity.class);
			entityManager.persist(entity);
			sedesList.add(entity);
			if (i == 0) {
				asesoresList.get(i).setSede(entity);
			}
		}
    }


    @Test
    void testReplaceSede() throws EntityNotFoundException {
        AsesorEntity entity = asesoresList.get(0);
		asesorSedeService.replaceSede(entity.getId(), sedesList.get(1).getId());
		entity = asesorService.getAsesor(entity.getId());
		assertEquals(entity.getSede(), sedesList.get(1));
    }


    @Test
    void testReplaceSedeInvalidAsesor() {
        assertThrows(EntityNotFoundException.class, ()->{
			asesorSedeService.replaceSede(0L, sedesList.get(1).getId());
		});
    }


    @Test
    void testReplaceInvalidSede() {
        assertThrows(EntityNotFoundException.class, ()->{
			AsesorEntity entity = asesoresList.get(0);
			asesorSedeService.replaceSede(entity.getId(), 0L);
		});
    }


    @Test
    void testRemoveSede() throws EntityNotFoundException {
        asesorSedeService.removeSede(asesoresList.get(0).getId());
		AsesorEntity response = asesorService.getAsesor(asesoresList.get(0).getId());
		assertNull(response.getSede());
    }


    @Test
    void testRemoveSedeInvalidAsesor() throws EntityNotFoundException {
        assertThrows(EntityNotFoundException.class, ()->{
			asesorSedeService.removeSede(0L);
		});
    }

    

}
