package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.repositories.AsesorRepository;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SedeAsesorService {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private AsesorRepository asesorRepository;

    /**
     * Asocia un Asesor existente a una Sede
     *
     * @param sedeId   Identificador de la instancia de Sede
     * @param asesorId Identificador de la instancia de Asesor
     * @return Instancia de AsesorEntity que fue asociada a Sede
     */
    @Transactional
    public AsesorEntity addAsesorToSede(Long sedeId, Long asesorId) throws EntityNotFoundException {
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);

        if (!sedeEntity.isPresent())
            throw new EntityNotFoundException("Sede not found");

        if (!asesorEntity.isPresent())
            throw new EntityNotFoundException("Asesor not found");

        asesorEntity.get().setSede(sedeEntity.get());
        return asesorRepository.save(asesorEntity.get());
    }

    /**
     * Obtiene una colección de instancias de AsesorEntity asociadas a una instancia de Sede
     *
     * @param sedeId Identificador de la instancia de Sede
     * @return Colección de instancias de AsesorEntity asociadas a la instancia de Sede
     */
    @Transactional
    public List<AsesorEntity> getAsesoresBySede(Long sedeId) throws EntityNotFoundException {
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);
        if (!sedeEntity.isPresent())
            throw new EntityNotFoundException("Sede not found");

        return sedeEntity.get().getAsesores();
    }

    /**
     * Desasocia un Asesor existente de una Sede existente
     *
     * @param sedeId   Identificador de la instancia de Sede
     * @param asesorId Identificador de la instancia de Asesor
     */
    @Transactional
    public void removeAsesorFromSede(Long sedeId, Long asesorId) throws EntityNotFoundException {
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);
        Optional<AsesorEntity> asesorEntity = asesorRepository.findById(asesorId);

        if (!sedeEntity.isPresent())
            throw new EntityNotFoundException("Sede not found");

        if (!asesorEntity.isPresent() || !asesorEntity.get().getSede().equals(sedeEntity.get()))
            throw new EntityNotFoundException("Asesor not found or not associated with the given Sede");

        asesorEntity.get().setSede(null);
        asesorRepository.save(asesorEntity.get());
    }
}
