package co.edu.uniandes.dse.CarMotor.services;

import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.ImagenRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Slf4j
@Service

public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Transactional
    public ImagenEntity createImagen(ImagenEntity imagenEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de imagen");

        if (imagenEntity.getVehiculo() == null)
        throw new IllegalOperationException("El vehículo no está especificado");

        Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(imagenEntity.getVehiculo().getId());

        if (vehiculoEntity.isEmpty())
                throw new IllegalOperationException("El vehículo con el ID especificado no existe");

        if (!validateURL(imagenEntity.getURL()))
                throw new IllegalOperationException("URL is not valid");

        if (!imagenRepository.findByURL(imagenEntity.getURL()).isEmpty())
                throw new IllegalOperationException("URL already exists");

        imagenEntity.setVehiculo(vehiculoEntity.get());
        log.info("Termina proceso de creación de la imagen");
        return imagenRepository.save(imagenEntity);
    }

    @Transactional
    public List<ImagenEntity> getImagenes() {
        log.info("Inicia proceso de consultar todas las imágenes");
        return imagenRepository.findAll();
    }

    @Transactional
    public ImagenEntity getImagen(Long imagenId) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar imagen con id = {0}", imagenId);
        Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
        if (imagenEntity.isEmpty()) 
            throw new EntityNotFoundException("Imagen no encontrada");
        log.info("Termina proceso de consultar la imagen con id = {0}", imagenId);
        return imagenEntity.get();
    }


    @Transactional
    public ImagenEntity updateImagen(Long imagenId, ImagenEntity imagen) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de actualizar la imagen con id = {0}", imagenId);
        Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
        if (imagenEntity.isEmpty())
                throw new EntityNotFoundException("La imagen con el id " + imagenId + " no existe");

        if (!validateURL(imagen.getURL()))
            throw new IllegalOperationException("URL is not valid");

        imagen.setId(imagenId);
        log.info("Termina proceso de actualizar la imagen con id = {0}", imagenId);
        return imagenRepository.save(imagen);
}

    @Transactional
    public void deleteImagen(Long imagenId) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de borrar la imagen con id = {0}", imagenId);
        Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
        if (imagenEntity.isEmpty()) {
            throw new EntityNotFoundException("La imagen con el id " + imagenId + " no existe");
        }
        imagenRepository.deleteById(imagenId);
        log.info("Termina proceso de borrar la imagen con id = {0}", imagenId);
    }


    private boolean validateURL(String url) {
        return !(url == null || url.isEmpty());
    }

}
