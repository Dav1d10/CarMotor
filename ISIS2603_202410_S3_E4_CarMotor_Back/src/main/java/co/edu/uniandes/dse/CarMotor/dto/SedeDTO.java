package co.edu.uniandes.dse.CarMotor.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SedeDTO {

    private Long id; 
    private String nombre;
    private String direccion;
    private String telefono;
    private String horarioAtencion;
    private String imagen;

}
