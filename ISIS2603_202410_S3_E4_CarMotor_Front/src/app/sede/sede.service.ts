import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development'; 
import { Observable } from 'rxjs';
import { Sede } from './sede';
import { SedeDetail } from './sede-detail';



@Injectable({
  providedIn: 'root'
})
export class SedeService {

  private apiUrl: string = environment.baseUrl + 'sedes';

  constructor(private http: HttpClient) { }

  getSedes(): Observable<SedeDetail[]> {
    return this.http.get<SedeDetail[]>(this.apiUrl);
  }

}
