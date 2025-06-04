package co.edu.uniandes.dse.CarMotor.services;

import java.util.List;
import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.AsesorRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsesorService {

    @Autowired
    AsesorRepository asesorRepository;

    @Autowired
    SedeRepository sedeRepository;


    @Transactional
    public AsesorEntity createAsesor(AsesorEntity asesorEntity) throws IllegalOperationException {
        log.info("Inicia proceso de creación de un asesor");
        if (asesorEntity.getNombre() == null) {
            throw new IllegalOperationException("Nombre del asesor no válido");
        }
        if (asesorEntity.getSede() == null) {
            throw new IllegalOperationException("Sede no es válida");
        }
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(asesorEntity.getSede().getId());
        if (sedeEntity.isEmpty()) {
            throw new IllegalOperationException("Sede no es válida");
        }
        if (!validarNombre(asesorEntity.getNombre())) {
            throw new IllegalOperationException("Nombre no válido");
        }
        if (!asesorRepository.findByNombre(asesorEntity.getNombre()).isEmpty()) {
            throw new IllegalOperationException("El nombre ya existe");
        }
        AsesorEntity nuevoAsesor = asesorRepository.save(asesorEntity);
        log.info("Termina proceso de creación de un asesor");
        return nuevoAsesor;
    }

    @Transactional
    public List<AsesorEntity> getAsesores() {
        log.info("Inicia proceso de consultar todos los asesores");
        return asesorRepository.findAll();
    }

    @Transactional
    public AsesorEntity getAsesor(Long asesorId) throws EntityNotFoundException  {
        log.info("Inicia proceso de consultar asesor por id", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException ("No se encontró el asesor");
        }
        log.info("Termina proceso de consultar asesor por id", asesorId);
        return asesorEntity.get();
    }

    @Transactional
    public AsesorEntity updateAsesor(Long asesorId, AsesorEntity asesor) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de actualizar el asesor con id", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el asesor");
        }
        if (!validarNombre(asesor.getNombre())) {
            throw new IllegalOperationException("Nombre inválido");
        }
        asesor.setId(asesorId);
        log.info("Termina proceso de actualizar el asesor con id", asesorId);
        return asesorRepository.save(asesor);
    }

    @Transactional
    public void deleteAsesor(Long asesorId) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia el proceso de borrar el asesor por su id", asesorId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);
        if (asesorEntity.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el asesor");
        }
        List<VehiculoEntity> vehiculos = asesorEntity.get().getVehiculosAsignados();
        if (!vehiculos.isEmpty()) {
            throw new IllegalOperationException("No se puede borrar asesor porque tiene vehiculos asignados");
        }
        asesorRepository.deleteById(asesorId);
        log.info("Termina el proceso de borrar el asesor por id", asesorId);
    }


    private boolean validarNombre(String nombre) {
        return !(nombre == null || nombre.isEmpty());
    }


    

    
}
