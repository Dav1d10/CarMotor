package co.edu.uniandes.dse.CarMotor.controllers;

import org.hibernate.mapping.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new String();
    }
    

}
