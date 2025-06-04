package co.edu.uniandes.dse.CarMotor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDTO {

    private Long id;
    private String marca;
    private String serie;
    private int ultimoPlaca;
    private String modelo;
    private String tipo;
    private int capacidad;
    private int precio;
    private String fotografia;
    private SedeDTO sede;
    private AsesorDTO asesor;
    
}
//B