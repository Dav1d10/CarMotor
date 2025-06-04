package co.edu.uniandes.dse.CarMotor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.services.VehiculoService;

@RestController
@RequestMapping("/VehiculosAsesores")
public class VehiculoAsesor {
    
    @Autowired
    private VehiculoService vehiculoService;



}
