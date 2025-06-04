import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiculoDetailComponent } from './vehiculo-detail/vehiculo-detail.component';
import { VehiculoListComponent } from './vehiculo-list/vehiculo-list.component';
import { RouterModule } from '@angular/router';
import { VehiculoRoutingModule } from './vehiculo-routing.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    VehiculoRoutingModule
  ],
  exports: [VehiculoListComponent],
  declarations: [VehiculoListComponent, VehiculoDetailComponent]
})
export class VehiculoModule { }
