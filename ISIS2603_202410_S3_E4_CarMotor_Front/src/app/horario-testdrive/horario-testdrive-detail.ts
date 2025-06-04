import { Sede } from '../sede/sede';
import { HorarioTestDrive } from './horario-testdrive';

export class HorarioTestDriveDetail extends HorarioTestDrive {
    otherDetail: string;  

    constructor(
        id: number,
        fecha: string,
        sede: Sede,
        otherDetail: string
    ) {
        super(id, fecha, sede);
        this.otherDetail = otherDetail;
        this.sede = sede;
    }
}
