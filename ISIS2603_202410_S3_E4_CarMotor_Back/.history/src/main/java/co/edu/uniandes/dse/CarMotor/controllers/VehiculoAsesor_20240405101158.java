package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTO;
import co.edu.uniandes.dse.CarMotor.dto.VehiculoDTODetail;
import co.edu.uniandes.dse.CarMotor.entities.VehiculoEntity;
import co.edu.uniandes.dse.CarMotor.services.VehiculoService;

@RestController
@RequestMapping("/VehiculosAsesores")
public class VehiculoAsesor {
    
    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ModelMap modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<VehiculoDTODetail> findAll() {
        
        List<VehiculoEntity> entidadesBancarias = entidadBancariaService.getEntidadesBancarias();
        return modelMapper.map(entidadesBancarias, new TypeToken<List<EntidadBancariaDTO>>(){}.getType());

    }



}
