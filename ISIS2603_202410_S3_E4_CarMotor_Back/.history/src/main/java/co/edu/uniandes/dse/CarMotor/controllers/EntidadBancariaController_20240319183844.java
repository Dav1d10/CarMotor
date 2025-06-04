package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.EntidadBancariaDTO;
import co.edu.uniandes.dse.CarMotor.dto.EntidadBancariaDetailDTO;
import co.edu.uniandes.dse.CarMotor.entities.EntidadBancariaEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.EntidadBancariaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/EntidadesBancarias")
public class EntidadBancariaController {

    @Autowired
    private EntidadBancariaService entidadBancariaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<EntidadBancariaDetailDTO> findAll() {
        
        List<EntidadBancariaEntity> entidadesBancarias = entidadBancariaService.getEntidadesBancarias();
        return modelMapper.map(entidadesBancarias, new TypeToken<List<EntidadBancariaDTO>>(){}.getType());

    }
    
    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EntidadBancariaDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {

        EntidadBancariaEntity entidadBancariaEntity = entidadBancariaService.getEntidadBancaria(id);
        return modelMapper.map(entidadBancariaEntity, EntidadBancariaDetailDTO.class);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntidadBancariaDTO create(@RequestBody EntidadBancariaDTO entidadBancariaDTO) throws IllegalOperationException, EntityNotFoundException {

        EntidadBancariaEntity entidadBancariaEntity = entidadBancariaService.createEntidadBancaria(modelMapper.map(entidadBancariaDTO, EntidadBancariaEntity.class));
        return modelMapper.map(entidadBancariaEntity, EntidadBancariaDTO.class);

    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EntidadBancariaDTO update(@PathVariable("id") Long id, @RequestBody EntidadBancariaDTO entidadBancariaDTO) throws EntityNotFoundException, IllegalOperationException {

        EntidadBancariaEntity entidadBancariaEntity = entidadBancariaService.updateEntidadBancaria(id, modelMapper.map(entidadBancariaDTO, EntidadBancariaEntity.class));

    }

}
