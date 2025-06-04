// asesor-detail.component.ts
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AsesorService } from '../asesor.service';
import { Asesor } from '../asesor';

@Component({
  selector: 'app-asesor-detail',
  templateUrl: './asesor-detail.component.html',
  styleUrls: ['./asesor-detail.component.css']
})
export class AsesorDetailComponent implements OnInit {
  @Input() asesor!: Asesor;
asesorDetail: any;

  constructor(
    private route: ActivatedRoute,
    private asesorService: AsesorService
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.asesorService.getAsesor(id).subscribe((asesor) => {
      this.asesor = asesor;
    });
  }
}
