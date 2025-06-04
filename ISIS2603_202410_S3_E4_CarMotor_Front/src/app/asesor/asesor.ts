import { Sede } from "../sede/sede";

export class Asesor {
  id: number;
  nombre: string;
  fotografia: string;
  telefono: string;
  correo: string;
  sede: Sede;

  constructor(
    id: number,
    nombre: string,
    fotografia: string,
    telefono: string,
    correo: string,
    sede: Sede
  ) {
    this.id = id;
    this.nombre = nombre;
    this.fotografia = fotografia;
    this.telefono = telefono;
    this.correo = correo;
    this.sede = sede;
  }
}
