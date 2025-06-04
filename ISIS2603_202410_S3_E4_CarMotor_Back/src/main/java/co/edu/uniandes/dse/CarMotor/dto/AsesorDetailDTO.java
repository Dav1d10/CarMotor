package co.edu.uniandes.dse.CarMotor.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AsesorDetailDTO extends AsesorDTO {
    private List<VehiculoDTO> vehiculos = new ArrayList<>();
    
}
