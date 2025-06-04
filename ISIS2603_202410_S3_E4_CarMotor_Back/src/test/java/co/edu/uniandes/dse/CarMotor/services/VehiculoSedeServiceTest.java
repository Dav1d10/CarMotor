package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.VehiculoSedeService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(VehiculoSedeService.class)
public class VehiculoSedeServiceTest {

    @Autowired
    private VehiculoSedeService vehiculoSedeService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private SedeEntity sede;
    private List<VehiculoEntity> vehiculoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from VehiculoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from SedeEntity").executeUpdate();
    }

    private void insertData() {
        sede = factory.manufacturePojo(SedeEntity.class);
        entityManager.persist(sede);

        for (int i = 0; i < 3; i++) {
            VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
            vehiculo.setSede(sede);
            entityManager.persist(vehiculo);
            vehiculoList.add(vehiculo);
        }
    }

    @Test
    void testAsignarVehiculoASede() throws EntityNotFoundException {
        VehiculoEntity newVehiculo = factory.manufacturePojo(VehiculoEntity.class);
        entityManager.persist(newVehiculo);

        VehiculoEntity result = vehiculoSedeService.asignarVehiculoASede(newVehiculo.getId(), sede.getId());
        assertNotNull(result);
        assertEquals(newVehiculo.getId(), result.getId());
        assertEquals(sede.getId(), result.getSede().getId());
    }

    @Test
    void testObtenerVehiculosPorSede() throws EntityNotFoundException {
        List<VehiculoEntity> vehiculosDeLaSede = vehiculoSedeService.obtenerVehiculosPorSede(sede.getId());
        assertNotNull(vehiculosDeLaSede);
        assertFalse(vehiculosDeLaSede.isEmpty());
        assertEquals(5, vehiculosDeLaSede.size());
    }

    @Test
    void testRemoverVehiculoDeSede() throws EntityNotFoundException {
        VehiculoEntity vehiculoParaRemover = vehiculoList.get(0);
        vehiculoSedeService.removerVehiculoDeSede(vehiculoParaRemover.getId(), sede.getId());

        VehiculoEntity result = entityManager.find(VehiculoEntity.class, vehiculoParaRemover.getId());
        assertNull(result.getSede());
    }
}
