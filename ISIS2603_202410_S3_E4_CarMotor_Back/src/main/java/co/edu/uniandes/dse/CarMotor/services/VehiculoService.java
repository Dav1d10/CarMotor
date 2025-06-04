package co.edu.uniandes.dse.CarMotor.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.ErrorMessage;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import co.edu.uniandes.dse.CarMotor.repositories.AsesorRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    
    @Autowired
    private AsesorRepository asesorRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Transactional
    public VehiculoEntity createVehiculo(VehiculoEntity vehiculoEntity) throws IllegalOperationException {
        log.info("Inicia proceso de creación de Vehiculo");
        if (vehiculoEntity.getAsesor()== null) {
            throw new IllegalOperationException("Asesor no es válida");
        }
        if (vehiculoEntity.getSede() == null) {
            throw new IllegalOperationException("Sede no es válida");
        }

        Optional<SedeEntity> sedeEntity = sedeRepository.findById(vehiculoEntity.getSede().getId());
        if (sedeEntity.isEmpty()) {
            throw new IllegalOperationException("Sede no es válida");
        }
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(vehiculoEntity.getAsesor().getId());
        if (asesorEntity.isEmpty()) {
            throw new IllegalOperationException("Asesor no es válida");
        }
        
        VehiculoEntity nuevoVehiculo = vehiculoRepository.save(vehiculoEntity);
        log.info("Termina proceso de creación de un vehiculo");
        return nuevoVehiculo;
    } 

      

    @Transactional(readOnly = true)
    public VehiculoEntity getVehiculo(Long vehiculoId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar vehiculo por id {}", vehiculoId);
        VehiculoEntity vehiculoEntity = vehiculoRepository.findById(vehiculoId)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró el vehiculo con id " + vehiculoId));
        log.info("Termina proceso de consultar vehiculo por id {}",vehiculoId);
        return vehiculoEntity;
    }

    @Transactional(readOnly = true)
    public List<VehiculoEntity> verVehiculos() {
        log.info("Inicia proceso de consultar todos los vehiculos");
        return vehiculoRepository.findAll();
    }

    @Transactional
    public VehiculoEntity updateVehiculo(Long vehiculoId, VehiculoEntity vehiculo) throws EntityNotFoundException {
        log.info("Inicia proceso de actualizar el vehiculo con id {}", vehiculoId);
        if (!vehiculoRepository.existsById(vehiculoId)) {
            throw new EntityNotFoundException("No se encontró el vehiculo con id " + vehiculoId);
        }

        vehiculo.setId(vehiculoId);
        log.info("Termina proceso de actualizar el vehiculo con id {}", vehiculoId);
        return vehiculoRepository.save(vehiculo);
    }

    @Transactional
    public void deleteVehiculo(Long vehiculoId) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia el proceso de borrar el vehiculo con id {}", vehiculoId);
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if (vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);

		List<ImagenEntity> imagenes = vehiculoEntity.get().getImagenes();

        if (!imagenes.isEmpty()) {
            throw new IllegalOperationException("Unable to delete vehicle because it has associated images");
        }
        vehiculoRepository.deleteById(vehiculoId);
        log.info("Termina el proceso de borrar el vehiculo con id ={0}",vehiculoId);   }
}