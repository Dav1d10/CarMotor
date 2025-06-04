import { Component, OnInit } from '@angular/core';
import { HorarioTestDriveService } from '../horario-testdrive.service';
import { HorarioTestDriveDetail } from '../horario-testdrive-detail.model';

@Component({
  selector: 'app-horario-testdrive-list',
  templateUrl: './horario-testdrive-list.component.html',
  styleUrls: ['./horario-testdrive-list.component.css']
})
export class HorarioTestDriveListComponent implements OnInit {

  horarios: HorarioTestDriveDetail[] = [];

  constructor(private horarioService: HorarioTestDriveService) { }

  ngOnInit(): void {
    this.horarioService.getHorariosTestDrive().subscribe(horarios => this.horarios = horarios);
  }
}
