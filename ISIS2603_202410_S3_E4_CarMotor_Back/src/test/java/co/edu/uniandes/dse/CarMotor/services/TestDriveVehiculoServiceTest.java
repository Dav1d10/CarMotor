package co.edu.uniandes.dse.CarMotor.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({ TestDriveVehiculoService.class, HorarioTestDriveService.class, VehiculoService.class })
class TestDriveVehiculoServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestDriveVehiculoService testDriveVehiculoService;

    @Autowired
    private HorarioTestDriveService horarioTestDriveService;

    

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HorarioTestDriveEntity> horarioList = new ArrayList<>();
    private List<VehiculoEntity> vehiculoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from HorarioTestDriveEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from VehiculoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
            entityManager.persist(horarioEntity);
            horarioList.add(horarioEntity);
        }
        for (int i = 0; i < 3; i++) {
            VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
            entityManager.persist(vehiculoEntity);
            vehiculoList.add(vehiculoEntity);
    
            if (i < horarioList.size()) {
                horarioList.get(i).getVehiculosDisponibles().add(vehiculoEntity);
            }
        }
    }

    @Test
    void testAsociarVehiculoAHorarioTestDrive() throws EntityNotFoundException {
    
        VehiculoEntity vehiculoEntity = factory.manufacturePojo(VehiculoEntity.class);
        vehiculoEntity = entityManager.persistAndFlush(vehiculoEntity);
        HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
        horarioEntity = entityManager.persistAndFlush(horarioEntity);

        
        assertNotNull(vehiculoEntity.getId());
        assertNotNull(horarioEntity.getId());
        try {
        testDriveVehiculoService.asociarVehiculoConHorario(vehiculoEntity.getId(), horarioEntity.getId());
        horarioEntity = horarioTestDriveService.getHorarioTestDriveById(horarioEntity.getId());

        assertNotNull(horarioEntity.getVehiculosDisponibles());
        assertFalse(horarioEntity.getVehiculosDisponibles().isEmpty());
    } catch (EntityNotFoundException e) {
        fail("Unexpected EntityNotFoundException occurred: " + e.getMessage());
    }
}
    

    @Test
    void testAsociarVehiculoAHorarioTestDriveNoExistente() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveVehiculoService.asociarVehiculoConHorario(vehiculoList.get(0).getId(), 0L);
        });
    }

    @Test
    void testAsociarVehiculoNoExistenteAHorarioTestDrive() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveVehiculoService.asociarVehiculoConHorario(0L, horarioList.get(0).getId());
        });
    }
    
    @Test
    void testObtenerVehiculosPorHorario() throws EntityNotFoundException {

        HorarioTestDriveEntity horarioEntity = factory.manufacturePojo(HorarioTestDriveEntity.class);
        horarioEntity = entityManager.persistAndFlush(horarioEntity);
        
            List<VehiculoEntity> vehiculosPorHorario = testDriveVehiculoService.obtenerVehiculosPorHorario(horarioEntity.getId());

            assertNotNull(vehiculosPorHorario);
            assertFalse(vehiculosPorHorario.isEmpty());
        
    }



    @Test
    void testObtenerVehiculosPorHorarioNoExistente() {
        assertThrows(EntityNotFoundException.class, () -> {
            testDriveVehiculoService.obtenerVehiculosPorHorario(0L);
        });
    }

    
}