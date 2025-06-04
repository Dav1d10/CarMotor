import { Asesor } from "../asesor/asesor";
import { HorarioTestDrive } from "../horario-testdrive/horario-testdrive";
import { Sede } from "../sede/sede";
import { Imagen } from "./imagen";
import { Vehiculo } from "./vehiculo";

export class VehiculoDetail extends Vehiculo {
    imagenes: Array<Imagen> = [];
  
    constructor(
        id: number,
        marca: string,
        serie: string,
        ultimoPlaca: number,
        modelo: string,
        tipo: string,
        capacidad: number,
        precio: number,
        fotografia: string,
        asesor: Asesor,
        sede: Sede,
        horarioTestDrive: HorarioTestDrive,
        imagenes: Array<Imagen>
        ) {
        super(id, marca, serie, ultimoPlaca, modelo, tipo, capacidad, precio, fotografia, asesor, sede, horarioTestDrive);
        this.imagenes = imagenes;
      }
  }
  