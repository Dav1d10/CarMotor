package co.edu.uniandes.dse.CarMotor.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HorarioTestDriveDetailDTO extends HorarioTestDriveDTO {
    
    private List<VehiculoDTO> vehiculos = new ArrayList<>();
    
}
