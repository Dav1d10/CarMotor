package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.uniandes.dse.CarMotor.dto.HorarioTestDriveDTO;
import co.edu.uniandes.dse.CarMotor.dto.HorarioTestDriveDetailDTO;
import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.CarMotor.services.HorarioTestDriveService;

@RestController
@RequestMapping("/horariosTestDrive")
public class HorarioTestDriveController {

    @Autowired
    private HorarioTestDriveService horarioTestDriveService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<HorarioTestDriveDetailDTO> findAll() {
        List<HorarioTestDriveEntity> horarios = horarioTestDriveService.getAllHorariosTestDrive();
        return modelMapper.map(horarios, new TypeToken<List<HorarioTestDriveDetailDTO>>(){}.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public HorarioTestDriveDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        HorarioTestDriveEntity horarioEntity = horarioTestDriveService.getHorarioTestDriveById(id);
        return modelMapper.map(horarioEntity, HorarioTestDriveDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public HorarioTestDriveDTO create(@RequestBody HorarioTestDriveDTO horarioTestDriveDTO) throws EntityNotFoundException, IllegalOperationException {
        HorarioTestDriveEntity horarioEntity = horarioTestDriveService.createHorarioTestDrive(modelMapper.map(horarioTestDriveDTO, HorarioTestDriveEntity.class));
        return modelMapper.map(horarioEntity, HorarioTestDriveDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public HorarioTestDriveDTO update(@PathVariable("id") Long id, @RequestBody HorarioTestDriveDTO horarioTestDriveDTO) throws EntityNotFoundException, IllegalOperationException {
        HorarioTestDriveEntity horarioEntity = horarioTestDriveService.updateHorarioTestDrive(id, modelMapper.map(horarioTestDriveDTO, HorarioTestDriveEntity.class));
        return modelMapper.map(horarioEntity, HorarioTestDriveDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalOperationException {
        horarioTestDriveService.deleteHorarioTestDrive(id);
    }
}
