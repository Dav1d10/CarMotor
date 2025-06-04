package co.edu.uniandes.dse.CarMotor.entities;

import lombok.Data;
import java.util.Map;
import javax.persistence.*;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity

public class HorarioTestDriveEntity extends BaseEntity{

    private String fecha;

    @ElementCollection
    @CollectionTable(name = "disponibilidad_por_dia", joinColumns = @JoinColumn(name = "horario_id"))
    @MapKeyColumn(name = "dia_semana")
    @Column(name = "disponible")
    private Map<String, Boolean> disponible;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private SedeEntity sede;


    @OneToMany(mappedBy = "horarioTestDrive", cascade = CascadeType.PERSIST)
    private List<VehiculoEntity> vehiculosDisponibles;
    

   
}
