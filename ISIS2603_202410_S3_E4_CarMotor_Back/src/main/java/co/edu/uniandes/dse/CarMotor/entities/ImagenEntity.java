package co.edu.uniandes.dse.CarMotor.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa la entidad Imagen
 */

 @Data
 @Entity

 public class ImagenEntity extends BaseEntity {

    private String URL;

    @Enumerated(EnumType.STRING)
    private TipoImagen tipoImagen;

    @ManyToOne
    @PodamExclude
    private VehiculoEntity vehiculo;
}

enum TipoImagen {
    EXTERIOR,
    INTERIOR,
    MOTOR
}
