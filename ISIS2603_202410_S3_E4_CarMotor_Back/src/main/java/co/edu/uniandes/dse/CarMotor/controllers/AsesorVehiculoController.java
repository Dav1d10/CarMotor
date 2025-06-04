package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTODetail;
import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTO;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.AsesorVehiculoService;

@RestController
@RequestMapping("/asesores")
public class AsesorVehiculoController {

    @Autowired
    private AsesorVehiculoService asesorVehiculoService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(value = "/{asesorId}/vehiculos/{vehiculoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public VehiculoDTODetail addVehiculo(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("asesorId") Long asesorId)
			throws EntityNotFoundException {
		VehiculoEntity vehiculoEntity = asesorVehiculoService.addVehiculo(asesorId, vehiculoId);
		return modelMapper.map(vehiculoEntity, VehiculoDTODetail.class);
	}

    @GetMapping(value = "/{asesorId}/vehiculos/{vehiculoId}")
	@ResponseStatus(code = HttpStatus.OK)
	public VehiculoDTODetail getVehiculo(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("asesorId") Long asesorId)
			throws EntityNotFoundException, IllegalOperationException {
		VehiculoEntity vehiculoEntity = asesorVehiculoService.getVehiculo(asesorId, vehiculoId);
		return modelMapper.map(vehiculoEntity, VehiculoDTODetail.class);
	}

    @PutMapping(value = "/{asesorId}/vehiculos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VehiculoDTODetail> addVehiculos(@PathVariable("asesorId") Long asesorId, @RequestBody List<VehiculoDTO> vehiculos)
			throws EntityNotFoundException {
		List<VehiculoEntity> entities = modelMapper.map(vehiculos, new TypeToken<List<VehiculoEntity>>() {
		}.getType());
		List<VehiculoEntity> vehiculosList = asesorVehiculoService.replaceVehiculos(asesorId, entities);
		return modelMapper.map(vehiculosList, new TypeToken<List<VehiculoDTODetail>>() {
		}.getType());
	}

    @GetMapping(value = "/{asesorId}/vehiculos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VehiculoDTODetail> getVehiculos(@PathVariable("asesorId") Long asesorId) throws EntityNotFoundException {
		List<VehiculoEntity> vehiculoEntity = asesorVehiculoService.getVehiculos(asesorId);
		return modelMapper.map(vehiculoEntity, new TypeToken<List<VehiculoDTODetail>>() {
		}.getType());
	}

    @DeleteMapping(value = "/{asesorId}/vehiculos/{vehiculoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeVehiculo(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("asesorId") Long asesorId)
			throws EntityNotFoundException {
		asesorVehiculoService.removeVehiculo(asesorId, vehiculoId);
	}



    
    
}
