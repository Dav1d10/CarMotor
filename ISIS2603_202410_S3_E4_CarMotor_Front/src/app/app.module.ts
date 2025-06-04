import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SedeModule } from './sede/sede.module';
import { HttpClientModule } from '@angular/common/http';
import { SedeRoutingModule } from './sede/sede-routing.module';
import { AsesorModule } from './asesor/asesor.module';
import { AsesorRoutingModule } from './asesor/asesor-routing.module';
import { EntidadBancariaService } from './entidad-bancaria/entidad-bancaria.service';
import { EntidadBancariaDetailComponent } from './entidad-bancaria/entidad-bancaria-detail/entidad-bancaria-detail.component';
import { EntidadBancariaListComponent } from './entidad-bancaria/entidad-bancaria-list/entidad-bancaria-list.component';
import { HorarioTestdriveModule } from './horario-testdrive/horario-testdrive.module';
import { HorarioTestDriveRoutingModule } from './horario-testdrive/horario-testdrive-routing.module';

import { RouterModule } from '@angular/router';
import { HorarioTestDriveService } from './horario-testdrive/horario-testdrive.service';
import { EntidadBancariaModule } from './entidad-bancaria/entidad-bancaria.module';
import { FormsModule } from '@angular/forms';

import { VehiculoModule } from './vehiculo/vehiculo.module';
import { VehiculoRoutingModule } from './vehiculo/vehiculo-routing.module';


@NgModule({
  declarations: [
    AppComponent
    
      ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SedeModule,
    HttpClientModule,
    SedeRoutingModule,
    RouterModule,
    EntidadBancariaModule,
    AsesorModule,
    AsesorRoutingModule,
    VehiculoModule,
    VehiculoRoutingModule,
    HorarioTestdriveModule,
    HorarioTestDriveRoutingModule
  ],
  providers: [ HorarioTestDriveService,
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
