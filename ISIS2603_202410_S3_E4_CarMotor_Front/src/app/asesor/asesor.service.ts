// asesor.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Asesor } from './asesor';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AsesorService {

  private baseUrl: string = environment.baseUrl + 'asesores';

  constructor(private http: HttpClient) { }

  getAsesores(): Observable<Asesor[]> {
    return this.http.get<Asesor[]>(this.baseUrl);
  }

  getAsesor(id: number): Observable<Asesor> {
    return this.http.get<Asesor>(`${this.baseUrl}/${id}`);
  }
}
