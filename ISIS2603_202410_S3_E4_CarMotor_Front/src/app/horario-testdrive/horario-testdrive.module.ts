import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HorarioTestDriveListComponent } from './horario-testdrive-list/horario-testdrive-list.component';
import { HorarioTestDriveDetailComponent } from './horario-testdrive-detail/horario-testdrive-detail.component';
import { HorarioTestDriveRoutingModule } from './horario-testdrive-routing.module';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    HorarioTestDriveListComponent,
    HorarioTestDriveDetailComponent
  ],
  imports: [
    CommonModule,
    HorarioTestDriveRoutingModule,
    RouterModule
  ],
  exports:[HorarioTestDriveListComponent]
})
export class HorarioTestdriveModule { }
