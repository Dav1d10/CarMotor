package co.edu.uniandes.dse.CarMotor.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntidadBancariaDetailDTO extends EntidadBancariaDTO {
    private List<SedeDTO> sedes;

}