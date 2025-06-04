import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EntidadBancariaDetailComponent } from './entidad-bancaria-detail/entidad-bancaria-detail.component';
import { EntidadBancariaListComponent } from './entidad-bancaria-list/entidad-bancaria-list.component';
import { EntidadBancariaRoutingModule } from './entidad-bancaria-routing.module';

@NgModule({
  
  imports: [
    CommonModule,
    RouterModule,
    EntidadBancariaRoutingModule,
    FormsModule
  ],
  exports: [EntidadBancariaListComponent],
  declarations: [EntidadBancariaListComponent,EntidadBancariaDetailComponent]
})
export class EntidadBancariaModule { }
