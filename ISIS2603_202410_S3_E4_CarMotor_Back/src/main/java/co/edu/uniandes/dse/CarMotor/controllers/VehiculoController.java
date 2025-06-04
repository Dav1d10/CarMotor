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

import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTO;
import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTODetail;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.VehiculoService;

@RestController
@RequestMapping("/vehiculos")

public class VehiculoController {
    @Autowired
        private VehiculoService vehiculoService;
    
    @Autowired
        private ModelMapper modelMapper;

@GetMapping
@ResponseStatus(code = HttpStatus.OK)
public List<VehiculoDTODetail> findAll() {
        List<VehiculoEntity> vehiculo = vehiculoService.verVehiculos();
        return modelMapper.map(vehiculo, new TypeToken<List<VehiculoDTODetail>>() {
        }.getType());
    }


@GetMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.OK)
public VehiculoDTODetail findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        VehiculoEntity vehiculoEntity = vehiculoService.getVehiculo(id);
        return modelMapper.map(vehiculoEntity, VehiculoDTODetail.class);
    }

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public VehiculoDTO create(@RequestBody VehiculoDTO vehiculoDTO) throws IllegalOperationException, EntityNotFoundException {
        VehiculoEntity vehiculoEntity = vehiculoService.createVehiculo(modelMapper.map(vehiculoDTO, VehiculoEntity.class));
        return modelMapper.map(vehiculoEntity, VehiculoDTO.class);
    }

@PutMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.OK)
public VehiculoDTO update(@PathVariable("id") Long id, @RequestBody VehiculoDTO vehiculoDTO)
                        throws EntityNotFoundException, IllegalOperationException {
        VehiculoEntity vehiculoEntity = vehiculoService.updateVehiculo(id, modelMapper.map(vehiculoDTO, VehiculoEntity.class));
        return modelMapper.map(vehiculoEntity, VehiculoDTO.class);
    }
@DeleteMapping(value = "/{id}")
@ResponseStatus(code = HttpStatus.NO_CONTENT)
public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        vehiculoService.deleteVehiculo(id);
}

    
}
