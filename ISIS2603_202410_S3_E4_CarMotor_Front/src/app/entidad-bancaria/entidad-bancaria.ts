export class EntidadBancaria {
    idEntidadBancaria: string;
    telefonoContacto: string;
    asesorFinanciero: string;
    poliza: string;
    nombre: string;
    autoInnovEntityId?: number;
  
    constructor(
      idEntidadBancaria: string,
      telefonoContacto: string,
      asesorFinanciero: string,
      poliza: string,
      nombre: string,
      autoInnovEntityId?: number
    ) {
      this.idEntidadBancaria = idEntidadBancaria;
      this.telefonoContacto = telefonoContacto;
      this.asesorFinanciero = asesorFinanciero;
      this.poliza = poliza;
      this.nombre = nombre;
      this.autoInnovEntityId = autoInnovEntityId;
    }
  }
  