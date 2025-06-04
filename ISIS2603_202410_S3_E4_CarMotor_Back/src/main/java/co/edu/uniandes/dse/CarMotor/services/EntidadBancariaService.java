package co.edu.uniandes.dse.CarMotor.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.CarMotor.entities.EntidadBancariaEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.EntidadBancariaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntidadBancariaService {

	@Autowired
	EntidadBancariaRepository EntidadBancariaRepository;

	@Transactional
	public EntidadBancariaEntity createEntidadBancaria(EntidadBancariaEntity entidadBancaria) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de entidadBancaria");
                
        if (entidadBancaria.getTelefonoContacto() == null)
                throw new IllegalOperationException("Telefono is not valid");

		if (entidadBancaria.getAsesorFinanciero() == null)
                throw new IllegalOperationException("Asesor is not valid");

		if (entidadBancaria.getPoliza() == null)
                throw new IllegalOperationException("Poliza is not valid");

		if (entidadBancaria.getNombre() == null)
                throw new IllegalOperationException("Nombre is not valid");

        if (entidadBancaria.getIdEntidadBancaria() == null)
                throw new IllegalOperationException("ID is not valid");

		if (entidadBancaria.getIdEntidadBancaria() == (""))
                throw new IllegalOperationException("ID is not valid");		

		if (!EntidadBancariaRepository.findByIdEntidadBancaria(entidadBancaria.getIdEntidadBancaria()).isEmpty()) {
            throw new IllegalOperationException("El ID ya existe");
        }		
		
        entidadBancaria.setId(entidadBancaria.getId());
        log.info("Termina proceso de creación la entidad bancaria");
        return EntidadBancariaRepository.save(entidadBancaria);
	}

	@Transactional
	public List<EntidadBancariaEntity> getEntidadesBancarias() {
        log.info("Inicia proceso de consultar todos las entidades bancarias");
        return EntidadBancariaRepository.findAll();
	}

	@Transactional
	public EntidadBancariaEntity getEntidadBancaria(Long idEntidadBancaria) throws EntityNotFoundException {
        log.info("Inicia proceso de consultar la entidad bancaria con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> entidadBancariaEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (entidadBancariaEntity.isEmpty())
                throw new EntityNotFoundException("a");
        log.info("Termina proceso de consultar la entidad bancaria con id = {0}", idEntidadBancaria);
        return entidadBancariaEntity.get();
	}

	@Transactional
	public EntidadBancariaEntity updateEntidadBancaria(Long idEntidadBancaria, EntidadBancariaEntity entidadBancaria)
                        throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de actualizar la entidad bancaria con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> bookEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (bookEntity.isEmpty())
                throw new EntityNotFoundException("error");

        if (entidadBancaria.getId().equals(""))
                throw new IllegalOperationException("is not valid");	

        if (entidadBancaria.getId() == null)
                throw new IllegalOperationException("is not valid");

        if (entidadBancaria.getIdEntidadBancaria() == null)
                throw new IllegalOperationException("is not valid");

        if (entidadBancaria.getIdEntidadBancaria() == (""))
                throw new IllegalOperationException("is not valid");

        entidadBancaria.setId(idEntidadBancaria);
        log.info("Termina proceso de actualizar la entidad bancaria con id = {0}", idEntidadBancaria);
        return EntidadBancariaRepository.save(entidadBancaria);
	}

	@Transactional
	public void deleteEntidadBancaria(Long idEntidadBancaria) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de borrar la entidad bancaria con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> entidadBancariaEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (entidadBancariaEntity.isEmpty())
                throw new EntityNotFoundException("error");		

        EntidadBancariaRepository.deleteById(idEntidadBancaria);
        log.info("Termina proceso de borrar la entidad bancaria con id = {0}", idEntidadBancaria);
	}

	@Transactional
	public void testDeleteEntidadBancariaWithId(Long idEntidadBancaria) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de borrar la entidad bancaria con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> entidadBancariaEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (entidadBancariaEntity.isEmpty())
                throw new EntityNotFoundException("error");

        Long id = entidadBancariaEntity.get().getId();

        if (id.equals(idEntidadBancaria))
                throw new IllegalOperationException("Unable to delete book because it has associated authors");				

        EntidadBancariaRepository.deleteById(idEntidadBancaria);
        log.info("Termina proceso de borrar la entidad bancaria con id = {0}", idEntidadBancaria);
	}

	/*@Transactional
	public static JFrame logo()throws FileNotFoundException, IOException{

		log.info("Cargando logo");
		JFrame logo = new JFrame();
        BufferedImage myPicture = ImageIO.read(new FileInputStream(""));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        logo.add(picLabel);
        return logo;
        
    }

	@Transactional
	public static List<String> verEBConvenio(EntidadBancariaEntity entidadBancaria) {
		
		List<String> convenios = new ArrayList<String>();
		convenios.add("Bancolombia");
		convenios.add("Paypal");
		log.info("los convenios son: ");
		for(int i = 0; i < convenios.size(); i++){
			log.info(convenios.get(i));
		}
		return convenios;
		
	}
	
	@Transactional
	  public static List<String> verDetalleEB(EntidadBancariaEntity entidadBancaria) {
		
		List<String> detalles = new ArrayList<String>();
		detalles.add(entidadBancaria.getTelefonoContacto());
		detalles.add(entidadBancaria.getAsesorFinanciero());
		detalles.add(entidadBancaria.getPoliza());
		detalles.add(entidadBancaria.getNombre());
		log.info("los detalles son: ");
		for(int i = 0; i < detalles.size(); i++){
			log.info(detalles.get(i));
		}
		return detalles;
		
	}

	@Transactional
	public static void ordenarPoliza(EntidadBancariaEntity entidadBancaria, String nuevaPoliza) {
		
		log.info("La nueva poliza va a ser " + nuevaPoliza);
		entidadBancaria.setPoliza(nuevaPoliza);

	}

	@Transactional
	public EntidadBancariaEntity ordenarPoliza(Integer idEntidadBancaria, EntidadBancariaEntity entidadBancaria) throws EntityNotFoundException{
        log.info("Inicia proceso de consultar el libro con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> EntidadBancariaEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (EntidadBancariaEntity.isEmpty())
                throw new EntityNotFoundException("EntidadBancariaError");
        log.info("Termina proceso de consultar el libro con id = {0}", idEntidadBancaria);
        return EntidadBancariaEntity.get();
	}	

	@Transactional
	public EntidadBancariaEntity ordenarPoliza(Long idEntidadBancaria, EntidadBancariaEntity entidadBancaria, String nuevaPoliza)
                        throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de actualizar el libro con id = {0}", idEntidadBancaria);
        Optional<EntidadBancariaEntity> EntidadBancariaEntity = EntidadBancariaRepository.findById(idEntidadBancaria);
        if (EntidadBancariaEntity.isEmpty())
                throw new EntityNotFoundException("a");

        if (entidadBancaria.getId().equals(null))
                throw new IllegalOperationException("ID is not valid");

        entidadBancaria.setPoliza(nuevaPoliza);
        log.info("Termina proceso de actualizar el libro con id = {0}", idEntidadBancaria);
        return EntidadBancariaRepository.save(entidadBancaria);
	}
	*/
}