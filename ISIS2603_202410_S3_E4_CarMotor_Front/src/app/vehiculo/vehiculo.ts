import { Sede } from '../sede/sede';
import { Asesor } from '../asesor/asesor';
import { HorarioTestDrive } from '../horario-testdrive/horario-testdrive';

export class Vehiculo {
  id: number;
  marca: string;
  serie: string;
  ultimoPlaca: number;
  modelo: string;
  tipo: string;
  capacidad: number;
  precio: number;
  fotografia: string;
  asesor: Asesor;
  sede: Sede;
  horarioTestDrive: HorarioTestDrive;

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
    horarioTestDrive: HorarioTestDrive
  ) {
    this.id = id;
    this.marca = marca;
    this.serie = serie;
    this.ultimoPlaca = ultimoPlaca;
    this.modelo = modelo;
    this.tipo = tipo;
    this.capacidad = capacidad;
    this.precio = precio;
    this.fotografia = fotografia;
    this.asesor = asesor;
    this.sede = sede;
    this.horarioTestDrive = horarioTestDrive;
  }
}



