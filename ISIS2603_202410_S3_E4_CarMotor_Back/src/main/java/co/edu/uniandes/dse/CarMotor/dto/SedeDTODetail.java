package co.edu.uniandes.dse.CarMotor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SedeDTODetail extends SedeDTO {

    private List<HorarioTestDriveDTO> horarioTestDriveList;
    private List<VehiculoDTO> vehiculos;
    private List<AsesorDTO> asesores;

}
