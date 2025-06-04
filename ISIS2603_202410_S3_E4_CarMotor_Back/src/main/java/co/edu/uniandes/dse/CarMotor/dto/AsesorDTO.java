package co.edu.uniandes.dse.CarMotor.dto;

import lombok.Data;

@Data
public class AsesorDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String fotografia;
    private SedeDTO sede;
    
    
}
