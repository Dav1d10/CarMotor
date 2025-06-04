package co.edu.uniandes.dse.CarMotor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.ErrorMessage;
import co.edu.uniandes.dse.CarMotor.repositories.AsesorRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AsesorSedeService {

    @Autowired
    private AsesorRepository asesorRepository;

    @Autowired
    private SedeRepository sedeRepository;


    @Transactional
    public AsesorEntity replaceSede(Long asesorId, Long sedeId) throws EntityNotFoundException {
        log.info("Inicia proceso de actualizar asesor con id = {0}", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);
        if (sedeEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.SEDE_NOT_FOUND);
        }
        asesorEntity.get().setSede(sedeEntity.get());
        log.info("Termina proceso de actualizar asesor con id = {0}", asesorId);
        return asesorEntity.get();
    }


    @Transactional
    public void removeSede(Long asesorId) throws EntityNotFoundException {
        log.info("Inicia proceso de borrar la sede del asesor con id = {0}", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(asesorEntity.get().getSede().getId());
        sedeEntity.ifPresent(sede -> sede.getAsesores().remove(asesorEntity.get()));
        asesorEntity.get().setSede(null);
        log.info("Termina proceso de borrar la sede del asesor con id = {0}", asesorId);
    }
    
}
