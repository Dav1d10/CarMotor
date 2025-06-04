import { Component, Input, OnInit } from '@angular/core';
import { Vehiculo } from '../vehiculo';
import { VehiculoDetail } from '../vehiculo-detail';


@Component({
  selector: 'app-vehiculo-detail',
  templateUrl: './vehiculo-detail.component.html',
  styleUrls: ['./vehiculo-detail.component.css']
})
export class VehiculoDetailComponent implements OnInit {

  @Input() vehiculoDetail!: VehiculoDetail;

  constructor() { }

  ngOnInit() {
  }

}
