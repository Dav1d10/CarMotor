export class Sede {
    id: number;
    nombre: string;
    direccion: string;
    telefono: string;
    horarioAtencion: string;
    imagen: string;

    constructor(
        id: number,
        nombre: string,
        direccion: string,
        telefono: string,
        horarioAtencion: string,
        imagen: string
    ) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono
        this.horarioAtencion = horarioAtencion;
        this.imagen = imagen;
    }
}
