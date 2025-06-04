import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EntidadBancariaListComponent } from './entidad-bancaria-list/entidad-bancaria-list.component';
import { EntidadBancariaDetailComponent } from './entidad-bancaria-detail/entidad-bancaria-detail.component';

const routes: Routes = [
    {
     path: 'list',
     component: EntidadBancariaListComponent
   },
   {
     path: ':id',
     component: EntidadBancariaDetailComponent
   },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
   })
   export class EntidadBancariaRoutingModule { }