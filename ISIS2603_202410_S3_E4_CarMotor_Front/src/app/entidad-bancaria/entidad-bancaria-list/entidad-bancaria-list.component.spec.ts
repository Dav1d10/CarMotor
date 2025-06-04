import {async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntidadBancariaListComponent } from './entidad-bancaria-list.component';
import { DebugElement } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { EntidadBancariaService } from '../entidad-bancaria.service';
import { EntidadBancariaDetail } from '../entidad-bancaria-detail';
import { faker } from '@faker-js/faker';
import { By } from '@angular/platform-browser';


describe('EntidadBancariaListComponent', () => {
  let component: EntidadBancariaListComponent;
  let fixture: ComponentFixture<EntidadBancariaListComponent>;
  let debug: DebugElement;

  beforeEach(async (() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [EntidadBancariaListComponent],
      providers: [EntidadBancariaService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntidadBancariaListComponent);
    component = fixture.componentInstance;
    
    let testEntidades: Array<EntidadBancariaDetail> = [];
    for(let i = 0; i < 25; i++) {
      testEntidades[i] = new EntidadBancariaDetail(
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.number.int(),
        
      );
    }
    component.entidadesBancarias = testEntidades;
    fixture.detectChanges();
    debug = fixture.debugElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
 
  it('should have 25 <div.col.mb-2> elements', () => {
    expect(debug.queryAll(By.css('div.col.mb-2'))).toHaveSize(25)
  });
 
  it('should have 25 <card.p-2> elements', () => {
    expect(debug.queryAll(By.css('div.card.p-2'))).toHaveSize(25)
  });
  



});
