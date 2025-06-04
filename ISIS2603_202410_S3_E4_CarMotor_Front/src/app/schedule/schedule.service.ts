import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Schedule } from '../schedule/schedule.model';
import { environment } from '../../environments/environment.development'; 


@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private apiUrl: string = environment.baseUrl + 'horariosTestDrive';

  constructor(private http: HttpClient) { }

  getAvailableSchedules(officeId: number): Observable<Schedule[]> {
    return this.http.get<Schedule[]>(this.apiUrl);
  }
}

