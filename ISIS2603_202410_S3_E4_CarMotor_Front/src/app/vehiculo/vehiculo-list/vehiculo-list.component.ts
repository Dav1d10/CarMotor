import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../vehiculo';
import { VehiculoService } from '../vehiculo.service';
import { VehiculoDetail } from '../vehiculo-detail';

@Component({
 selector: 'app-vehiculo-list',
 templateUrl: './vehiculo-list.component.html',
 styleUrls: ['./vehiculo-list.component.css']
})
export class VehiculoListComponent implements OnInit {


 selected: Boolean = false;
 selectedVehiculo!: VehiculoDetail;
 vehiculos: Array<VehiculoDetail> = [];
 constructor(private vehiculoService: VehiculoService) { }


 getVehiculos(): void {
  this.vehiculoService.getVehiculos().subscribe(
    (vehiculos) => {
      console.log(vehiculos); // Imprime los vehículos para ver su contenido, incluyendo las imágenes
      this.vehiculos = vehiculos;
    },
    (error) => {
      console.error('Error al cargar los vehículos:', error);
    }
  );
}


 onSelected(vehiculo: VehiculoDetail): void {
   this.selected = true;
   this.selectedVehiculo = vehiculo;
 }


 ngOnInit() {
   this.getVehiculos();
 }


}
