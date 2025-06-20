package co.edu.uniandes.dse.CarMotor.services;

import java.util.List;
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
public class AsesorVehiculoService {

    @Autowired
    private AsesorRepository asesorRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    
    @Transactional
    public VehiculoEntity addVehiculo(Long asesorId, Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia el proceso de asociarle un vehiculo al asesor con id = {0}", asesorId);
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        asesorEntity.get().getVehiculosAsignados().add(vehiculoEntity.get());
        log.info("Termina el proceso de asociarle un vehiculo al asesor con id = {0}", asesorId);
        return vehiculoEntity.get();
    }


    @Transactional
    public List<VehiculoEntity> getVehiculos(Long asesorId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar todos los vehiculos del asesor con id = {0}", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        log.info("Finaliza el proceso de consultar todos los vehiculos del asesor con id = {0}", asesorId);
        return asesorEntity.get().getVehiculosAsignados();
    }


    @Transactional
    public VehiculoEntity getVehiculo(Long asesorId, Long vehiculoId) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de consultar un vehiculo del asesor con id = {0}", asesorId);
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (vehiculoEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
        }
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
        log.info("Termina proceso de consultar un vehiculo del asesor con id = {0}", asesorId);
        if (!asesorEntity.get().getVehiculosAsignados().contains(vehiculoEntity.get())) {
            throw new IllegalOperationException("El vehiculo no está asociado al asesor");
        }
        return vehiculoEntity.get();
    }


    @Transactional
    public List<VehiculoEntity> replaceVehiculos(Long asesorId, List<VehiculoEntity> list) throws EntityNotFoundException {
        log.info("Inicia proceso de reemplazar los vehiculos del asesor con id = {0}", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.ASESOR_NOT_FOUND);
        }
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
