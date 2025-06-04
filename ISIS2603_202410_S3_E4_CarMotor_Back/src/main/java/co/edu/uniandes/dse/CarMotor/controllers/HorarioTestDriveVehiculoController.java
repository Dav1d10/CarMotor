package co.edu.uniandes.dse.CarMotor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTO;
import co.edu.uniandes.dse.CarMotor.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.CarMotor.services.TestDriveVehiculoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horariosTestDrive")
public class HorarioTestDriveVehiculoController {

    @Autowired
    private TestDriveVehiculoService testDriveVehiculoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/{horarioId}/vehiculos/{vehiculoId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void asociarVehiculoConHorario(@PathVariable("vehiculoId") Long vehiculoId, @PathVariable("horarioId") Long horarioId) throws EntityNotFoundException {
        testDriveVehiculoService.asociarVehiculoConHorario(vehiculoId, horarioId);
    }

    @GetMapping(value = "/{horarioId}/vehiculos")
    public List<VehiculoDTO> obtenerVehiculosPorHorario(@PathVariable("horarioId") Long horarioId) throws EntityNotFoundException {
        List<VehiculoDTO> vehiculos = testDriveVehiculoService.obtenerVehiculosPorHorario(horarioId).stream()
                .map(vehiculo -> modelMapper.map(vehiculo, VehiculoDTO.class))
                .collect(Collectors.toList());
        return vehiculos;
    }
}