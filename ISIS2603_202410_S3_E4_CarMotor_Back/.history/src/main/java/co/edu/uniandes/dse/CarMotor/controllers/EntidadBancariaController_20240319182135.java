package co.edu.uniandes.dse.CarMotor.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.CarMotor.dto.EntidadBancariaDTO;
import co.edu.uniandes.dse.CarMotor.dto.EntidadBancariaDetailDTO;
import co.edu.uniandes.dse.CarMotor.entities.EntidadBancariaEntity;
import co.edu.uniandes.dse.CarMotor.services.EntidadBancariaService;
import org.springframework.web.bind.annotation.GetMapping;
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
        return modelMapper.map(entidadesBancarias, new TypeToken<List<EntidadBancariaDTO>>)

    }
    

}
