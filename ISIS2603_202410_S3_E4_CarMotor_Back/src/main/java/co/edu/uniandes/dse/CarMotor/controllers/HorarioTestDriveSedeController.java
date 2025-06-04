package co.edu.uniandes.dse.CarMotor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.uniandes.dse.CarMotor.dto.HorarioTestDriveDetailDTO;
import co.edu.uniandes.dse.CarMotor.entities.HorarioTestDriveEntity;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.TestDriveSedeService;

@RestController
@RequestMapping("/horariosTestDrive")
public class HorarioTestDriveSedeController {

    @Autowired
    private TestDriveSedeService testDriveSedeService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping(value = "/{horarioId}/sede/{sedeId}")
    @ResponseStatus(code = HttpStatus.OK)
    public HorarioTestDriveDetailDTO asociarHorarioConSede(@PathVariable("horarioId") Long horarioId, @PathVariable("sedeId") Long sedeId) throws EntityNotFoundException {
        HorarioTestDriveEntity horario = testDriveSedeService.asociarHorarioConSede(horarioId, sedeId);
        return modelMapper.map(horario, HorarioTestDriveDetailDTO.class);
    }
}


