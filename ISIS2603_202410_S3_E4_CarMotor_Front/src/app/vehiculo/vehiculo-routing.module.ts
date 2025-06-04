import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VehiculoListComponent } from './vehiculo-list/vehiculo-list.component';
import { VehiculoDetailComponent } from './vehiculo-detail/vehiculo-detail.component';


const routes: Routes = [
    {
     path: 'list',
     component: VehiculoListComponent
   },
   {
     path: ':id',
     component: VehiculoDetailComponent
   },
];


@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class VehiculoRoutingModule { }