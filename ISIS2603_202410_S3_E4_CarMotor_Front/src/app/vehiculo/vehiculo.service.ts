import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { catchError, Observable, throwError } from 'rxjs';
import { VehiculoDetail } from './vehiculo-detail';


@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  private apiUrl: string = environment.baseUrl + 'vehiculos';


constructor(private http: HttpClient) { }

getVehiculos(): Observable<VehiculoDetail[]> {
  return this.http
    .get<VehiculoDetail[]>(this.apiUrl)
    .pipe(
      catchError((err) => throwError(() => new Error('error en el servicio')))
    );
}

getVehiculo(id: string): Observable<VehiculoDetail> {
  return this.http.get<VehiculoDetail>(this.apiUrl + '/' + id);
}

}
