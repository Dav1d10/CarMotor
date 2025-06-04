package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.repositories.HorarioTestDriveRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

import javax.transaction.Transactional;

@Slf4j
@Service
public class HorarioTestDriveService {

    private final HorarioTestDriveRepository horarioTestDriveRepository;
    private final VehiculoRepository vehiculoRepository;
    private final SedeRepository sedeRepository;

    @Autowired
    public HorarioTestDriveService(HorarioTestDriveRepository horarioTestDriveRepository, VehiculoRepository vehiculoRepository,SedeRepository sedeRepository) {
        this.horarioTestDriveRepository = horarioTestDriveRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.sedeRepository = sedeRepository;
        
    }


    @Transactional
    public HorarioTestDriveEntity createHorarioTestDrive(HorarioTestDriveEntity horarioTestDriveEntity) throws IllegalOperationException, EntityNotFoundException {
        log.info("Creando un posible horario para un test drive.");
        if (horarioTestDriveEntity.getFecha() == null) {
            throw new IllegalOperationException("Fecha invalida");
        }

        if (horarioTestDriveEntity.getSede() == null){ 
            throw new IllegalOperationException("Entidad de sede nula");
        }

        if (horarioTestDriveEntity.getVehiculosDisponibles() == null) {
            throw new IllegalOperationException("Lista de vehiculos nula");
        }

        Optional<SedeEntity> sedeEntity = sedeRepository.findById(horarioTestDriveEntity.getSede().getId());

        if (sedeEntity.isEmpty()) {
            throw new IllegalOperationException("Sede no es válida");
        }

        try {
            return horarioTestDriveRepository.save(horarioTestDriveEntity);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creando HorarioTestDriveEntity: " + e.getMessage());
            throw new IllegalOperationException("Error creando la entidad HorarioTestDrive. Datos nulos o invalidos.");
        }
    }

    @Transactional
    public List<HorarioTestDriveEntity> getAllHorariosTestDrive() {
        return horarioTestDriveRepository.findAll();
    }
    @Transactional
    public HorarioTestDriveEntity getHorarioTestDriveById(Long id) throws EntityNotFoundException {
        return horarioTestDriveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horario con el id " + id + " no fue encontrado."));
    }
    @Transactional
    public HorarioTestDriveEntity updateHorarioTestDrive(Long id, HorarioTestDriveEntity updatedEntity) throws EntityNotFoundException, IllegalOperationException {
        if (!horarioTestDriveRepository.existsById(id)) {
            throw new EntityNotFoundException("Horario con el id " + id + " no fue encontrado.");
        }    
    
        if (updatedEntity.getFecha() == null) {
            throw new IllegalOperationException("Fecha inválida");
        }
    
        updatedEntity.setId(id);
        return horarioTestDriveRepository.save(updatedEntity);
    }
    @Transactional
    public void deleteHorarioTestDrive(Long id) throws EntityNotFoundException, IllegalOperationException {
    try {
        horarioTestDriveRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new EntityNotFoundException("Horario con el " + id + " no fue encontrado.");}
    }   

    
    
}

