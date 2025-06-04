import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { EntidadBancaria } from './entidad-bancaria';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class EntidadBancariaService {

   private apiUrl: string = environment.baseUrl + 'EntidadesBancarias';

  constructor(private http: HttpClient) { }

  getEntidadesBancarias(): Observable<EntidadBancaria[]> {
    return this.http.get<EntidadBancaria[]>(this.apiUrl).pipe(
      tap(data => console.log('API Response:', data)) 
    );
  }

  getEntidadBancariaById(id: number): Observable<EntidadBancaria> {
    return this.http.get<EntidadBancaria>(`${this.apiUrl}/${id}`);
  }

  createEntidadBancaria(entidadBancaria: EntidadBancaria): Observable<EntidadBancaria> {
    return this.http.post<EntidadBancaria>(this.apiUrl, entidadBancaria);
  }

  deleteEntidadBancaria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
