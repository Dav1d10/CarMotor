import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScheduleListComponent } from './schedule/ScheduleList/ScheduleList.component';
import { SedeListComponent } from './sede/sede-list/sede-list.component';
import { SedeDetailComponent } from './sede/sede-detail/sede-detail.component';
import { AsesorListComponent } from './asesor/asesor-list/asesor-list.component';
import { EntidadBancariaDetailComponent } from './entidad-bancaria/entidad-bancaria-detail/entidad-bancaria-detail.component';
import { EntidadBancariaListComponent } from './entidad-bancaria/entidad-bancaria-list/entidad-bancaria-list.component';
import { VehiculoListComponent } from './vehiculo/vehiculo-list/vehiculo-list.component';
import { HorarioTestDriveListComponent } from './horario-testdrive/horario-testdrive-list/horario-testdrive-list.component';


const routes: Routes = [
  { path: '', component: VehiculoListComponent},
  { path: 'sedes', loadChildren: () => import('./sede/sede.module').then(m => m.SedeModule)},
  { path: 'schedules', component: ScheduleListComponent },
  { path: '', redirectTo: '/asesores/list', pathMatch: 'full' },
  { path: 'asesores', loadChildren: () => import('./asesor/asesor.module').then(m => m.AsesorModule) },
  { path: 'entidades-bancarias', loadChildren: () => import('./entidad-bancaria/entidad-bancaria.module').then(m => m.EntidadBancariaModule)},
  { path: '', component: HorarioTestDriveListComponent},
  { path: 'horario-testdrive', loadChildren: () => import('./horario-testdrive/horario-testdrive.module').then(m => m.HorarioTestdriveModule) },
  { path:'vehiculos', loadChildren: () => import('./vehiculo/vehiculo.module').then(m => m.VehiculoModule)}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
