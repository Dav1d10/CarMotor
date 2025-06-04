package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.AsesorDTO;
import co.edu.uniandes.dse.CarMotor.dto.AsesorDetailDTO;
import co.edu.uniandes.dse.CarMotor.dto.EntidadBancariaDTO;
import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTO;
import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTODetail;
import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.repositories.VehiculoRepository;
import co.edu.uniandes.dse.CarMotor.services.VehiculoAsesorService;
import co.edu.uniandes.dse.CarMotor.services.VehiculoService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/VehiculosAsesores")
public class VehiculoAsesor {
    
    @Autowired
    private VehiculoAsesorService vehiculoAsesorService;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/asesores/{asesorId}/vehiculos/{vehiculoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public AsesorDetailDTO addAsesor(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("asesorId") Long asesorId)
			throws EntityNotFoundException {
		AsesorEntity asesorEntity = vehiculoAsesorService.addAsesor(asesorId, vehiculoId);
		return modelMapper.map(asesorEntity, AsesorDetailDTO.class);
	}

    @GetMapping(value = "/asesores/{vehiculoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public AsesorDetailDTO getAsesor(@PathVariable("vehiculoId") Long vehiculoId)
			throws EntityNotFoundException, IllegalOperationException {
		AsesorEntity asesorEntity = vehiculoAsesorService.getAsesor(vehiculoId);
		return modelMapper.map(asesorEntity, AsesorDetailDTO.class);
	}

    @GetMapping(value = "/vehiculo/{vehiculoId}/asesores")
	@ResponseStatus(code = HttpStatus.OK)
	public List<AsesorEntity> getAsesores(@PathVariable("vehiculoId") Long vehiculoId) throws EntityNotFoundException {
		List<AsesorEntity> asesorEntity = vehiculoAsesorService.getAsesores(vehiculoRepository.findAll());
		return modelMapper.map(asesorEntity, new TypeToken<List<VehiculoDTODetail>>() {
		}.getType());
	}

    @DeleteMapping(value = "/{asesorId}/asesores/{vehiculoId}/vehiculos")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeAsesor(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("asesorId") Long asesorId)
			throws EntityNotFoundException {
		vehiculoAsesorService.removeAsesor(asesorId, vehiculoId);
	}



}
