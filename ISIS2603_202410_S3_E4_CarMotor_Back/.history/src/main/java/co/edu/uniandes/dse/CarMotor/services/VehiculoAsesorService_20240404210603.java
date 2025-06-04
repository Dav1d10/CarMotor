package co.edu.uniandes.dse.CarMotor.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.ErrorMessage;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.AsesorRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VehiculoAsesorService {

    @Autowired
    VehiculoRepository VehiculoRepository;

    @Autowired
    AsesorRepository AsesorRepository;
    
    @Transactional
    public AsesorEntity addAsesor(Long asesorId, Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia el proceso de asociarle un asesor al vehiculo con id = {0}", vehiculoId);
        Optional<AsesorEntity> asesorEntity = AsesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        Optional<VehiculoEntity> vehiculoEntity = VehiculoRepository.findById(vehiculoId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        vehiculoEntity.get().setAsesor(asesorEntity.get());
        log.info("Termina el proceso de asociarle un asesor al vehiculo con id = {0}", vehiculoId);
        return asesorEntity.get();
    }


    @Transactional
    public AsesorEntity getAsesor(Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar el asesor al vehiculo con id = {0}", vehiculoId);
        Optional<VehiculoEntity> vehiculoEntity = VehiculoRepository.findById(vehiculoId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        log.info("Finaliza el proceso de consultar el asesor del vehiculo con id = {0}", vehiculoId);
        return vehiculoEntity.get().getAsesor();
    }

    @Transactional
    public AsesorEntity replaceAsesor(Long asesorId, Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia proceso de reemplazar el asesor del vehiculo con id = {0}", vehiculoId);
        Optional<VehiculoEntity> vehiculoEntity = VehiculoRepository.findById(vehiculoId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        Optional<AsesorEntity> asesorEntity = AsesorRepository.findById(asesorId);
        for (VehiculoEntity vehiculo : list) {
            Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculo.getId());
            if (vehiculoEntity.isEmpty()) {
                throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
            }
            if (!asesorEntity.get().getVehiculosAsignados().contains(vehiculoEntity.get())) {
                asesorEntity.get().getVehiculosAsignados().add(vehiculoEntity.get());
            }
        }
        log.info("Termina proceso de reemplazar los vehiculos del asesor con id = {0}", asesorId);
        return getVehiculos(asesorId);
    }


    @Transactional
    public void removeVehiculo(Long asesorId, Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia proceso de borrar un vehiculo del asesor con id = {0}", asesorId);
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        asesorEntity.get().getVehiculosAsignados().remove(vehiculoEntity.get());
        log.info("Termina proceso de borrar un vehiculo del asesor con id = {0}", asesorId);
    }
}
