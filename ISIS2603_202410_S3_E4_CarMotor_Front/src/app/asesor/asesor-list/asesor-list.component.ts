// asesor-list.component.ts
import { Component, OnInit } from '@angular/core';
import { AsesorService } from '../asesor.service';
import { Asesor } from '../asesor';

@Component({
  selector: 'app-asesor-list',
  templateUrl: './asesor-list.component.html',
  styleUrls: ['./asesor-list.component.css']
})
export class AsesorListComponent implements OnInit {
  asesores: Asesor[] = [];

  constructor(private asesorService: AsesorService) { }

  ngOnInit(): void {
    this.asesorService.getAsesores().subscribe((asesores) => {
      this.asesores = asesores;
    },
    (error) => {
      console.error('Error loading asesores', error);
    }
    );
  }
}
