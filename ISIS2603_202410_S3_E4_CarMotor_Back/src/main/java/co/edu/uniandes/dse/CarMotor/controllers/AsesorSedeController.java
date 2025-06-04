package co.edu.uniandes.dse.CarMotor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.AsesorDetailDTO;
import co.edu.uniandes.dse.CarMotor.dto.SedeDTO;
import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.AsesorSedeService;

@RestController
@RequestMapping("/asesores")
public class AsesorSedeController {
    
    @Autowired
    private AsesorSedeService asesorSedeService;

    @Autowired
    private ModelMapper modelMapper;


    @PutMapping(value = "/{asesorId}/sede")
	@ResponseStatus(code = HttpStatus.OK)
	public AsesorDetailDTO replaceSede(@PathVariable("asesorId") Long asesorId, @RequestBody SedeDTO sedeDTO)
			throws EntityNotFoundException {
		AsesorEntity asesorEntity = asesorSedeService.replaceSede(asesorId, sedeDTO.getId());
		return modelMapper.map(asesorEntity, AsesorDetailDTO.class);
	}
    
}
