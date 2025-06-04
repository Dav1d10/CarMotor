package co.edu.uniandes.dse.CarMotor.dto;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoDTODetail extends VehiculoDTO {

    private List<ImagenDTO> imagen;
    private List<HorarioTestDriveDTO> horarioTestDrive;
}
