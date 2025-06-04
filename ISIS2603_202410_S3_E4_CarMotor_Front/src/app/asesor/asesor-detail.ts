import { Sede } from "../sede/sede";
import { Vehiculo } from "../vehiculo/vehiculo";
import { Asesor } from "./asesor";

export class AsesorDetail extends Asesor {

    vehiculos: Array<Vehiculo> = [];
    

    constructor(
        id: number,
        nombre: string,
        fotografia: string,
        telefono: string,
        correo: string,
        sede: Sede,
        vehiculos: Array<Vehiculo>,
    ) {
        super(id, nombre, fotografia, telefono, correo, sede);
        this.vehiculos = vehiculos;
    }
}