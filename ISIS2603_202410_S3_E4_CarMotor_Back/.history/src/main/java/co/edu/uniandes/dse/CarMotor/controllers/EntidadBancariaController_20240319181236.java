package co.edu.uniandes.dse.CarMotor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.services.EntidadBancariaService;

@RestController
@RequestMapping("/EntidadesBancarias")
public class EntidadBancariaController {

    @Autowired
    private EntidadBancariaService entidadBancariaService;

    @Autowired
    private ModelMapper modelMapper;
    
}
