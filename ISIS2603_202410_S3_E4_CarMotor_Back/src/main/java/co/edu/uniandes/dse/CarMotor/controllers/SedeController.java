package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.uniandes.dse.CarMotor.dto.SedeDTO;
import co.edu.uniandes.dse.CarMotor.dto.SedeDTODetail;
import co.edu.uniandes.dse.CarMotor.entities.SedeEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.SedeService;

@RestController
@RequestMapping("/sedes")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<SedeDTODetail> findAll() {
        List<SedeEntity> sedes = sedeService.verSedes();
        return modelMapper.map(sedes, new TypeToken<List<SedeDTODetail>>() {}.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public SedeDTODetail findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        Optional<SedeEntity> sedeEntity = sedeService.verSede(id);
        return modelMapper.map(sedeEntity, SedeDTODetail.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SedeDTO create(@RequestBody SedeDTO sedeDTO) throws IllegalOperationException, EntityNotFoundException {
        SedeEntity sedeEntity = sedeService.crearSede(modelMapper.map(sedeDTO, SedeEntity.class));
        return modelMapper.map(sedeEntity, SedeDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public SedeDTO update(@PathVariable("id") Long id, @RequestBody SedeDTO sedeDTO) throws EntityNotFoundException, IllegalOperationException {
        SedeEntity sedeEntity = sedeService.actualizarSede(id, modelMapper.map(sedeDTO, SedeEntity.class));
        return modelMapper.map(sedeEntity, SedeDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        sedeService.eliminarSede(id);
    }

}
