package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.repositories.HorarioTestDriveRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TestDriveVehiculoService {

    private final HorarioTestDriveRepository horarioTestDriveRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public TestDriveVehiculoService(HorarioTestDriveRepository horarioTestDriveRepository, VehiculoRepository vehiculoRepository) {
        this.horarioTestDriveRepository = horarioTestDriveRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    //revisar en master con vehiculo bien
    @Transactional
    public void asociarVehiculoConHorario(Long vehiculoId, Long horarioId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociar un test drive con un horario = {0}", horarioId);
        VehiculoEntity vehiculoEntity = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo con el ID " + vehiculoId + " no encontrado."));

        HorarioTestDriveEntity horarioEntity = horarioTestDriveRepository.findById(horarioId)
                .orElseThrow(() -> new EntityNotFoundException("HorarioTestDrive con el ID " + horarioId + " no encontrado."));

        List<VehiculoEntity> vehiculosDisponibles = horarioEntity.getVehiculosDisponibles();
        if (vehiculosDisponibles == null) {
            vehiculosDisponibles = new ArrayList<>();
        }
        vehiculosDisponibles.add(vehiculoEntity);
        horarioEntity.setVehiculosDisponibles(vehiculosDisponibles);

        horarioTestDriveRepository.save(horarioEntity);
    }
  
    @Transactional
    public List<VehiculoEntity> obtenerVehiculosPorHorario(Long horarioId) throws EntityNotFoundException {
        HorarioTestDriveEntity horarioEntity = horarioTestDriveRepository.findById(horarioId)
                .orElseThrow(() -> new EntityNotFoundException("HorarioTestDrive con el ID " + horarioId + " no encontrado."));

        return horarioEntity.getVehiculosDisponibles();
    }

}