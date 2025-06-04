package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    // CREATE: Agregar una nueva sede
  public SedeEntity crearSede(SedeEntity sedeEntity) throws IllegalOperationException {
    Optional<SedeEntity> existingSede = sedeRepository.findByNombre(sedeEntity.getNombre());
    if (existingSede.isPresent()) {
        throw new IllegalOperationException("Ya existe una sede con el nombre especificado.");
    }
    return sedeRepository.save(sedeEntity);
}


    // READ: Ver información de las sedes
    @Transactional
    public List<SedeEntity> verSedes() {
        return sedeRepository.findAll();
    }

    public Optional<SedeEntity> verSede(Long id) {
        return sedeRepository.findById(id);
    }
    

    public List<String> verSedesInformacion() {
        return sedeRepository.findAll().stream()
                .map(sede -> String.format("Sede: %s\nDirección: %s\nTeléfono: %s\nHorario de atención: %s",
                        sede.getNombre(), sede.getDireccion(), sede.getTelefono(), sede.getHorarioAtencion()))
                .collect(Collectors.toList());
    }

    public List<String> verSedesHorario() {
        return sedeRepository.findAll().stream()
                .map(sede -> String.format("Sede: %s\nHorario de atención: %s", sede.getNombre(), sede.getHorarioAtencion()))
                .collect(Collectors.toList());
    }

    
   // public String verSedeTestDrive(VehiculoEntity vehiculo) {
    
   //     return "Método necesita revisión para ajustarse a las entidades relacionadas.";
   // }

    // UPDATE: Actualizar una sede existente
    public SedeEntity actualizarSede(Long sedeId, SedeEntity sedeDetails) {
        SedeEntity sede = sedeRepository.findById(sedeId).orElseThrow(() -> new IllegalStateException("Sede no encontrada con id " + sedeId));
        sede.setNombre(sedeDetails.getNombre());
        sede.setDireccion(sedeDetails.getDireccion());
        sede.setTelefono(sedeDetails.getTelefono());
        sede.setHorarioAtencion(sedeDetails.getHorarioAtencion());
        return sedeRepository.save(sede);
    }

    // DELETE: Eliminar una sede
    public void eliminarSede(Long sedeId) {
        SedeEntity sede = sedeRepository.findById(sedeId).orElseThrow(() -> new IllegalStateException("Sede no encontrada con id " + sedeId));
        sedeRepository.delete(sede);
    }
}
