package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.repositories.SedeRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoSedeService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Transactional
    public VehiculoEntity asignarVehiculoASede(Long vehiculoId, Long sedeId) throws EntityNotFoundException {
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);

        if (!vehiculoEntity.isPresent())
            throw new EntityNotFoundException("El vehículo no fue encontrado");

        if (!sedeEntity.isPresent())
            throw new EntityNotFoundException("La sede no fue encontrada");

        vehiculoEntity.get().setSede(sedeEntity.get());
        return vehiculoRepository.save(vehiculoEntity.get());
    }

    @Transactional
    public List<VehiculoEntity> obtenerVehiculosPorSede(Long sedeId) throws EntityNotFoundException {
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);
        if (!sedeEntity.isPresent())
            throw new EntityNotFoundException("La sede no fue encontrada");

        return sedeEntity.get().getVehiculosDisponibles();
    }

    @Transactional
    public void removerVehiculoDeSede(Long vehiculoId, Long sedeId) throws EntityNotFoundException {
        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
        Optional<SedeEntity> sedeEntity = sedeRepository.findById(sedeId);

        if (!vehiculoEntity.isPresent() || !sedeEntity.isPresent())
            throw new EntityNotFoundException("El vehículo o la sede no fueron encontrados");

        if (vehiculoEntity.get().getSede().equals(sedeEntity.get())) {
            vehiculoEntity.get().setSede(null);
            vehiculoRepository.save(vehiculoEntity.get());
        } else {
            throw new EntityNotFoundException("El vehículo no está asociado a la sede indicada");
        }
    }
}
