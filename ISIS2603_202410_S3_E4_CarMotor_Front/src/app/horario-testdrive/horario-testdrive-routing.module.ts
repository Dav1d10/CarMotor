import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HorarioTestDriveListComponent } from './horario-testdrive-list/horario-testdrive-list.component';
import { HorarioTestDriveDetailComponent } from './horario-testdrive-detail/horario-testdrive-detail.component';

const routes: Routes = [
  { path: 'list', component: HorarioTestDriveListComponent },
  { path: ':id', component: HorarioTestDriveDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HorarioTestDriveRoutingModule { }
