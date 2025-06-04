import { Sede } from "../sede/sede";

export class HorarioTestDrive {
    id: number;
    fecha: string;
    sede: Sede;
  
    constructor(id: number, fecha: string, sede: Sede) {
      this.id = id;
      this.fecha = fecha;
      this.sede = sede;
    }
  }
  