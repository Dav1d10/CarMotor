import { Component, Input, OnInit } from '@angular/core';
import { Sede } from '../sede';
import { SedeDetail } from '../sede-detail';


@Component({
  selector: 'app-sede-detail',
  templateUrl: './sede-detail.component.html',
  styleUrls: ['./sede-detail.component.css']
})
export class SedeDetailComponent implements OnInit {

  @Input() sedeDetail!: SedeDetail;

  constructor() { }

  ngOnInit() {
  }

}
