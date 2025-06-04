package co.edu.uniandes.dse.CarMotor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.ErrorMessage;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.ImagenRepository;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class VehiculoImagenService {

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private VehiculoRepository vehiculoRepository;

    /**
	 * Agregar una imagen al vehiculo
	 *
	 * @param imagenId      El id imagen a guardar
	 * @param vehiculoId El id del vehiculo en ek cual se va a guardar la imagen.
	 * @return La imagen creada.
	 * @throws EntityNotFoundException 
	 */
	
	@Transactional
	public ImagenEntity addImagen(Long imagenId, Long vehiculoId) throws EntityNotFoundException {
		log.info("Inicia proceso de agregarle una imagen al vehiculo con id = {0}", vehiculoId);
		
		Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
		if(imagenEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.IMAGEN_NOT_FOUND);
		
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if(vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
		
		imagenEntity.get().setVehiculo(vehiculoEntity.get());
		log.info("Termina proceso de agregarle un imagen al vehiculo con id = {0}", vehiculoId);
		return imagenEntity.get();
	}

	/**
	 * Retorna todas las imagenes asociadas a un vehiculo
	 *
	 * @param vehiculoId El ID del vehiculo buscado
	 * @return La lista de imagenes del vehiculo
	 * @throws EntityNotFoundException si el vehiculo no existe
	 */
	@Transactional
	public List<ImagenEntity> getImagenes(Long vehiculoId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar las imagenes asociados al vehiculo con id = {0}", vehiculoId);
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if(vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
		
		return vehiculoEntity.get().getImagenes();
	}

	/**
	 * Retorna una imagen asociada a un vehiculo
	 *
	 * @param vehiculoId El id del vehiculo a buscar.
	 * @param imamgenId      El id de la imagen a buscar
	 * @return La imagen encontrada dentro del vehiculo.
	 * @throws EntityNotFoundException Si la imagen no se encuentra en el vehiculo
	 * @throws IllegalOperationException Si la imagen no está asociada a el vehiculo
	 */
	@Transactional
	public ImagenEntity getImagen(Long vehiculoId, Long imagenId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de consultar la imagen con id = {0} del vehiculo con id = " + vehiculoId, imagenId);
		
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if(vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
		
		Optional<ImagenEntity> imagenEntity = imagenRepository.findById(imagenId);
		if(imagenEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.IMAGEN_NOT_FOUND);
				
		log.info("Termina proceso de consultar la imagen con id = {0} del vehiculo con id = " + vehiculoId, imagenId);
		
		if(!vehiculoEntity.get().getImagenes().contains(imagenEntity.get()))
			throw new IllegalOperationException("The image is not associated to the vehicle");
		
		return imagenEntity.get();
	}

	/**
	 * Remplazar imagenes de un vehiculo
	 *
	 * @param imagenes        Lista de imagenes que serán los del vehiculo.
	 * @param vehiculoId El id del vehiculo que se quiere actualizar.
	 * @return La lista de imagenes actualizada.
	 * @throws EntityNotFoundException Si el vehiculo o un imagen de la lista no se encuentran
	 */
	@Transactional
	public List<ImagenEntity> replaceImagenes(Long vehiculoId, List<ImagenEntity> imagenes) throws EntityNotFoundException {
		log.info("Inicia proceso de actualizar el vehiculo con id = {0}", vehiculoId);
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepository.findById(vehiculoId);
		if(vehiculoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VEHICULO_NOT_FOUND);
		
		for(ImagenEntity imagen : imagenes) {
			Optional<ImagenEntity> b = imagenRepository.findById(imagen.getId());
			if(b.isEmpty())
				throw new EntityNotFoundException(ErrorMessage.IMAGEN_NOT_FOUND);
			
			b.get().setVehiculo(vehiculoEntity.get());
		}		
		return imagenes;
	}
}
