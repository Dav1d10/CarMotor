package co.edu.uniandes.dse.CarMotor.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class SedeEntity extends BaseEntity {

    private String nombre;
    private String direccion;
    private String telefono;
    private String horarioAtencion;
    private String imagen;

    @ManyToOne(cascade = CascadeType.ALL)
    private AutoInnovEntity autoInnovEntity;

    @OneToMany(mappedBy = "sede")
    private List<VehiculoEntity> vehiculosDisponibles = new ArrayList<>();

    @OneToMany(mappedBy = "sede")
    private List<AsesorEntity> asesores = new ArrayList<>();

    @OneToMany(mappedBy = "sede")
    private List<HorarioTestDriveEntity> horariosTestDrive = new ArrayList<>();

}

