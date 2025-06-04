import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../schedule/schedule.service';
import { Schedule } from '../../schedule/schedule.model';

@Component({
  selector: 'app-ScheduleList',
  templateUrl: './ScheduleList.component.html',
  styleUrls: ['./ScheduleList.component.css']
})
export class ScheduleListComponent implements OnInit {
  schedules: Schedule[]=[];

  constructor(private scheduleService: ScheduleService) { }

  ngOnInit(): void {
    this.loadSchedules(1); // Ejemplo con ID de sede '1'
  }

  loadSchedules(officeId: number): void {
    this.scheduleService.getAvailableSchedules(officeId).subscribe(
      data => {
        this.schedules = data;
      },
      error => {
        console.error('Error loading schedules:', error);
      }
    );
  }
}