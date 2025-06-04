package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
import co.edu.uniandes.dse.CarMotor.services.VehiculoAsesorService;
import co.edu.uniandes.dse.CarMotor.services.VehiculoService;

@RestController
@RequestMapping("/VehiculosAsesores")
public class VehiculoAsesor {
    
    @Autowired
    private VehiculoAsesorService vehiculoAsesorService;

    @Autowired
    private ModelMap modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void findAll() {

    }



}
