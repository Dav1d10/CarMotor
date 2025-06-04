package co.edu.uniandes.dse.CarMotor.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class HorarioTestDriveDTO {

    private Long id;
    private String fecha;
    private Map<String, Boolean> disponible;
    private SedeDTO sede;

}
