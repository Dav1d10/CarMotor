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

import co.edu.uniandes.dse.CarMotor.dto.AsesorDTO;
import co.edu.uniandes.dse.CarMotor.dto.AsesorDetailDTO;
import co.edu.uniandes.dse.CarMotor.entities.AsesorEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.AsesorService;

@RestController
@RequestMapping("/asesores")
public class AsesorController {

    @Autowired
    private AsesorService asesorService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AsesorDetailDTO> findAll() {
        List<AsesorEntity> asesores = asesorService.getAsesores();
        return modelMapper.map(asesores, new TypeToken<List<AsesorDetailDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AsesorDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
       AsesorEntity asesorEntity = asesorService.getAsesor(id);
       return modelMapper.map(asesorEntity, AsesorDetailDTO.class); 
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AsesorDTO create(@RequestBody AsesorDTO asesorDTO) throws IllegalOperationException, EntityNotFoundException {
        AsesorEntity asesorEntity = asesorService.createAsesor(modelMapper.map(asesorDTO, AsesorEntity.class));
        return modelMapper.map(asesorEntity, AsesorDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AsesorDTO update(@PathVariable("id") Long id, @RequestBody AsesorDTO asesorDTO) throws EntityNotFoundException, IllegalOperationException {
        AsesorEntity asesorEntity = asesorService.updateAsesor(id, modelMapper.map(asesorDTO, AsesorEntity.class));
        return modelMapper.map(asesorEntity, AsesorDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        asesorService.deleteAsesor(id);
    }

    
}
