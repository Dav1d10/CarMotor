package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.HorarioTestDriveRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class TestDriveSedeService {

    private final HorarioTestDriveRepository horarioTestDriveRepository;
    private final SedeRepository sedeRepository;

    @Autowired
    public TestDriveSedeService(HorarioTestDriveRepository horarioTestDriveRepository, SedeRepository sedeRepository) {
        this.horarioTestDriveRepository = horarioTestDriveRepository;
        this.sedeRepository = sedeRepository;
    }

    
    @Transactional 
    public HorarioTestDriveEntity asociarHorarioConSede(Long horarioId, Long sedeId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociar un test drive con su sede = {0}", sedeId);
        HorarioTestDriveEntity horario = horarioTestDriveRepository.findById(horarioId)
                .orElseThrow(() -> new EntityNotFoundException("Horario con el ID " + horarioId + " no encontrado."));

        SedeEntity sede = sedeRepository.findById(sedeId)
                .orElseThrow(() -> new EntityNotFoundException("Sede con el ID " + sedeId + " no encontrada."));

        horario.setSede(sede);
        return horarioTestDriveRepository.save(horario);
    }

   @Transactional
    public List<HorarioTestDriveEntity> obtenerHorariosPorSede(Long sedeId) throws EntityNotFoundException, IllegalOperationException {
        SedeEntity sedeEntity = sedeRepository.findById(sedeId)
                .orElseThrow(() -> new EntityNotFoundException("Sede con el ID " + sedeId + " no encontrado."));

        List<HorarioTestDriveEntity> horariosPorSede = horarioTestDriveRepository.findBySede(sedeEntity);

        if (horariosPorSede.isEmpty()) {
            throw new IllegalOperationException("No se encontraron horarios para la sede con ID " + sedeId);
        }

    return horariosPorSede;
    }
}