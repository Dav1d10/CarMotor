package co.edu.uniandes.dse.CarMotor.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EntidadBancariaEntity extends BaseEntity {

    private String idEntidadBancaria;
   // @Setter
    //private static JFrame logo;
    private String telefonoContacto;    
    private String asesorFinanciero; 
    @Setter
    private String poliza;
    private String nombre;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST) // Agrega esta línea
    private AutoInnovEntity autoInnovEntity; // Este campo representa la referencia de vuelta a AutoInnovEntity

}
