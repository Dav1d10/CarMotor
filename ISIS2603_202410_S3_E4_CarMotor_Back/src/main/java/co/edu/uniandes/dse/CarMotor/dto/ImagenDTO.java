package co.edu.uniandes.dse.CarMotor.dto;


import lombok.Data;

@Data
public class ImagenDTO {

    private String URL;
    public enum TipoImagen {
        EXTERIOR, INTERIOR, MOTOR
    }
    private VehiculoDTO vehiculo;
}


