import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EntidadBancariaService } from '../entidad-bancaria.service';
import { EntidadBancariaDetail } from '../entidad-bancaria-detail';

@Component({
  selector: 'app-entidad-bancaria-detail',
  templateUrl: './entidad-bancaria-detail.component.html',
  styleUrls: ['./entidad-bancaria-detail.component.css']
})
export class EntidadBancariaDetailComponent implements OnInit {

  @Input() entidadBancariaDetail!: EntidadBancariaDetail;

  constructor( ) { }

  ngOnInit() {
  }
}
