// asesor.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AsesorListComponent } from './asesor-list/asesor-list.component';
import { AsesorDetailComponent } from './asesor-detail/asesor-detail.component';
import { AsesorRoutingModule } from './asesor-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AsesorListComponent,
    AsesorDetailComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    AsesorRoutingModule
  ]
})
export class AsesorModule { }
