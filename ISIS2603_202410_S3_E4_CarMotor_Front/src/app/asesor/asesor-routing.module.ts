// asesor-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AsesorListComponent } from './asesor-list/asesor-list.component';
import { AsesorDetailComponent } from './asesor-detail/asesor-detail.component';

const routes: Routes = [
  { path: 'list', component: AsesorListComponent },
  { path: ':id', component: AsesorDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AsesorRoutingModule { }
