import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HorarioTestDriveService } from '../horario-testdrive.service';
import { HorarioTestDriveDetail } from '../horario-testdrive-detail.model';

@Component({
  selector: 'app-horario-testdrive-detail',
  templateUrl: './horario-testdrive-detail.component.html',
  styleUrls: ['./horario-testdrive-detail.component.css']
})
export class HorarioTestDriveDetailComponent implements OnInit {

  @Input() horarioDetail!: HorarioTestDriveDetail;

  constructor(
    private route: ActivatedRoute,
    private horarioService: HorarioTestDriveService
  ) { }

  ngOnInit(): void {
    if (this.horarioDetail === undefined) {
      const id = this.route.snapshot.paramMap.get('id')!;
      this.horarioService.getHorarioTestDrive(id).subscribe(horario => this.horarioDetail = horario);
    }
  }
}
