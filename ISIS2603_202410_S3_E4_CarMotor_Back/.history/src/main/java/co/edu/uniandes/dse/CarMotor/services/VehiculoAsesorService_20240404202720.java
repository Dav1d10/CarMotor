package co.edu.uniandes.dse.CarMotor.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
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
    public VehiculoEntity asociarAutoInovConService(Long VehiculoId, Long AsesorId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociar un Vehiculo con su Asesor = {0}", VehiculoId);
        VehiculoEntity Vehiculo = VehiculoRepository.findById(VehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("AutoInnov con el ID " + VehiculoId + " no encontrado."));

        AsesorEntity Asesor = AsesorRepository.findById(AsesorId)
                .orElseThrow(() -> new EntityNotFoundException("EntidadBancaria con el ID " + AsesorId + " no encontrada."));

        Vehiculo.setAsesor(Asesor);
        return VehiculoRepository.save(Vehiculo);
    }

   @Transactional
    public AsesorEntity conseguirAutoInovConService(Long VehiculoId) throws EntityNotFoundException {
        log.info("Inicia proceso de asociar un Vehiculo con su Asesor = {0}", VehiculoId);
        VehiculoEntity Vehiculo = VehiculoRepository.findById(VehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("AutoInnov con el ID " + VehiculoId + " no encontrado."));
        AsesorEntity asesor = Vehiculo.getAsesor();
        return asesor;
    }
}
