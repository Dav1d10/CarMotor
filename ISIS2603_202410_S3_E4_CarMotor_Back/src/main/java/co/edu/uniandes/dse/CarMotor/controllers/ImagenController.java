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

import co.edu.uniandes.dse.CarMotor.dto.ImagenDTO;
import co.edu.uniandes.dse.CarMotor.entities.ImagenEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.ImagenService;

@RestController
@RequestMapping("/imagenes")

public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @Autowired
    private ModelMapper modelMapper;

    // FIND ALL

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ImagenDTO> findAll() {
        List<ImagenEntity> imagenes = imagenService.getImagenes();
        return modelMapper.map(imagenes, new TypeToken<List<ImagenDTO>>() {
        }.getType());
    }

    // FIND ONE

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ImagenDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        ImagenEntity imagenEntity = imagenService.getImagen(id);
        return modelMapper.map(imagenEntity, ImagenDTO.class);
    }

    // CREATE

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ImagenDTO create(@RequestBody ImagenDTO imagenDTO)
            throws IllegalOperationException, EntityNotFoundException {
        ImagenEntity imagenEntity = imagenService.createImagen(modelMapper.map(imagenDTO, ImagenEntity.class));
        return modelMapper.map(imagenEntity, ImagenDTO.class);
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ImagenDTO update(@PathVariable("id") Long id, @RequestBody ImagenDTO imagenDTO)
            throws EntityNotFoundException, IllegalOperationException {
        ImagenEntity imagenEntity = imagenService.updateImagen(id, modelMapper.map(imagenDTO, ImagenEntity.class));
        return modelMapper.map(imagenEntity, ImagenDTO.class);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        imagenService.deleteImagen(id);
    }
}
