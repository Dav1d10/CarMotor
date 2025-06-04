package co.edu.uniandes.dse.CarMotor.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa la página principal AutoInnov
 */

@Data
@Entity

public class AutoInnovEntity extends BaseEntity {

    @PodamExclude
    @OneToMany(mappedBy = "autoInnovEntity")
        private List<EntidadBancariaEntity> entidadesBancarias = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "autoInnovEntity")
        private List<SedeEntity> sedes = new ArrayList<>();

}