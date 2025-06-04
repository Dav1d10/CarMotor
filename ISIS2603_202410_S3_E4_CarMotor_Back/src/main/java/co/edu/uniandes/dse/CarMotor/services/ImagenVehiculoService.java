package co.edu.uniandes.dse.CarMotor.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.ErrorMessage;
import co.edu.uniandes.dse.CarMotor.repositories.ImagenRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImagenVehiculoService {

    @Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	/**
	 * Remplazar el vehiculo de una imagen.
	 *
	 * @param imagenId      id de la imagen que se quiere actualizar.
	 * @param vehiculoId El id del vehiculo que se será de la imagen.
	 * @return la nuevo imagen.
	 */

	@Transactional
	public ImagenEntity replaceVehiculo(Long imagenId, Long vehiculoId) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar imagen con id = {0}", imagenId);
		Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
		if (imagenEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.IMAGEN_NOT_FOUND);

		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if (vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);

		imagenEntity.get().setVehiculo(vehiculoEntity.get());
		log.info("Termina proceso de actualizar imagen con id = {0}", imagenId);

		return imagenEntity.get();
	}

	/**
	 * Borrar una imagen de un vehiculo. Este metodo se utiliza para borrar la
	 * relacion de una imagen.
	 *
	 * @param imagenesId La imagen que se desea borrar del vehiculo.
	 */
	@Transactional
	public void removeVehiculo(Long imagenId) throws EntityNotFoundException {
		log.info("Inicia proceso de borrar el vehiculo de la imagen con id = {0}", imagenId);
		Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
		if (imagenEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.IMAGEN_NOT_FOUND);

		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository
				.findById(imagenEntity.get().getVehiculo().getId());
		vehiculoEntity.ifPresent(vehiculo -> vehiculo.getImagenes().remove(imagenEntity.get()));

		imagenEntity.get().setVehiculo(null);
		log.info("Termina proceso de borrar el vehiculo de la imagen con id = {0}", imagenId);
	}
    
}
