
import { Asesor } from "../asesor/asesor";
import { Vehiculo } from "../vehiculo/vehiculo";
import { Sede } from "./sede";


export class SedeDetail extends Sede {
    vehiculos: Array<Vehiculo> = [];
    asesores: Array<Asesor> = [];
    
    
    constructor(
        id: number,
        nombre: string,
        direccion: string,
        telefono: string,
        horarioAtencion: string,
        imagen: string,
        vehiculos: Array<Vehiculo>,
        asesores: Array<Asesor>
    ) {
        super(id, nombre, direccion, telefono, horarioAtencion, imagen);
        this.vehiculos = vehiculos;
        this.asesores = asesores;
    }
}
