import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HorarioTestDriveDetail } from '../horario-testdrive/horario-testdrive-detail.model';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class HorarioTestDriveService {

  private apiUrl: string = environment.baseUrl + 'horariosTestDrive';

  constructor(private http: HttpClient) { }

  getHorariosTestDrive(): Observable<HorarioTestDriveDetail[]> {
    return this.http.get<HorarioTestDriveDetail[]>(this.apiUrl);
  }

  getHorarioTestDrive(id: string): Observable<HorarioTestDriveDetail> {
    return this.http.get<HorarioTestDriveDetail>(`${this.apiUrl}/${id}`);
  }
}
