import { Component, OnInit } from '@angular/core';
import { Sede } from '../sede';
import { SedeService } from '../sede.service';
import { SedeDetail } from '../sede-detail';


@Component({
  selector: 'app-sede-list',
  templateUrl: './sede-list.component.html',
  styleUrls: ['./sede-list.component.css']
})
export class SedeListComponent implements OnInit {
  
  selected: Boolean = false;
  selectedSede!: SedeDetail;
  sedes: Array<SedeDetail> = [];
  searchTerm: string = '';
  constructor(private sedeService: SedeService) { }

  getSedes(): void {
    this.sedeService.getSedes().subscribe((sedes) => {
      this.sedes = sedes;
    });
  }

  onSelected(sede: SedeDetail): void {
    this.selected = true;
    this.selectedSede = sede;
  }

  get filteredSedes(): SedeDetail[] {
    return this.sedes.filter((sede) =>
      sede.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  ngOnInit() {
    this.getSedes();
  }

}
