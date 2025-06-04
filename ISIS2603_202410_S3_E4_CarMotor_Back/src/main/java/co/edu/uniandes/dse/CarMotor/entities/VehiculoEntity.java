package co.edu.uniandes.dse.CarMotor.entities;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class VehiculoEntity extends BaseEntity {
    private String marca;
    private String serie;
    private int ultimoPlaca;
    private String modelo;
    private String tipo;
    private int capacidad;
    private int precio;
    private String fotografia;

    @PodamExclude
    @ManyToOne
    private AsesorEntity asesor;

    @PodamExclude
    @ManyToOne
    private SedeEntity sede;

    @PodamExclude
    @ManyToOne
    @JoinColumn(name = "horario_test_drive_id")
    private HorarioTestDriveEntity horarioTestDrive;

    @PodamExclude
    @OneToMany(mappedBy = "vehiculo")
    private List<ImagenEntity> imagenes = new ArrayList<>();
}
