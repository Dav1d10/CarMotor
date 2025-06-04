import { Component, OnInit } from '@angular/core';
import { EntidadBancariaService } from '../entidad-bancaria.service';
import { EntidadBancaria } from '../entidad-bancaria';
import { EntidadBancariaDetail } from '../entidad-bancaria-detail';

@Component({
  selector: 'app-entidad-bancaria-list',
  templateUrl: './entidad-bancaria-list.component.html',
  styleUrls: ['./entidad-bancaria-list.component.css']
})
export class EntidadBancariaListComponent implements OnInit {

  select: Boolean = false;

  entidadSelect!: EntidadBancariaDetail;
  entidadesBancarias: Array<EntidadBancaria> = [];
  filteredEntidadesBancarias: Array<EntidadBancaria> = [];
  searchTerm: string = '';

  constructor(private entidadBancariaService: EntidadBancariaService) { }

  getEntidadesBancarias(): void {
    this.entidadBancariaService.getEntidadesBancarias().subscribe((entidadesBancarias) => {
      console.log('Entidades Bancarias:', entidadesBancarias);
      this.entidadesBancarias = entidadesBancarias;
      this.filteredEntidadesBancarias = entidadesBancarias;
    });
  }
  
  onSelected(entidad: EntidadBancariaDetail): void {
    this.select = true;
    this.entidadSelect = entidad;
    console.log('Entidad Seleccionada:', this.entidadSelect); 
  }

  sortByPolizaCost(): void {
    this.entidadesBancarias.sort((a, b) => {
      return parseFloat(a.poliza) - parseFloat(b.poliza);
    });
  }

  filterEntidades(): void {
    this.filteredEntidadesBancarias = this.entidadesBancarias.filter(entidad =>
      entidad.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      entidad.asesorFinanciero.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      entidad.poliza.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  ngOnInit(): void {
    this.getEntidadesBancarias();
  }
  
}
