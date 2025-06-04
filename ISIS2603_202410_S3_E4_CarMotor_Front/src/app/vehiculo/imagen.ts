export class Imagen {
    id: number;
    url: string;
    tipoImagen: TipoImagen;

  
    constructor(
      id: number,
      url: string,
      tipoImagen: TipoImagen
    ) {
      this.id = id;
      this.url = url
      this.tipoImagen = tipoImagen;
    }
  }

  export enum TipoImagen {
    EXTERIOR = "EXTERIOR",
    INTERIOR = "INTERIOR",
    MOTOR = "MOTOR"
}
