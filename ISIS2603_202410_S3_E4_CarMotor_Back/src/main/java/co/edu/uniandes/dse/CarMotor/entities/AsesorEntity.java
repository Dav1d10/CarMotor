package co.edu.uniandes.dse.CarMotor.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class AsesorEntity extends BaseEntity {
    
    private String nombre;
    private String telefono;
    private String correo;
    private String fotografia;


    @PodamExclude
    @ManyToOne
    private SedeEntity sede;


    @PodamExclude
    @OneToMany(mappedBy = "asesor") 
    private List<VehiculoEntity> vehiculosAsignados = new ArrayList<>();
}
