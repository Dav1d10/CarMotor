import { EntidadBancaria } from "./entidad-bancaria";


export class EntidadBancariaDetail extends EntidadBancaria {
    constructor(
        idEntidadBancaria: string,
        telefonoContacto: string,
        asesorFinanciero: string,
        poliza: string,
        nombre: string,
        autoInnovEntityId?: number,
    ) {
        super(idEntidadBancaria, telefonoContacto, asesorFinanciero, poliza, nombre, autoInnovEntityId);
    }
}
