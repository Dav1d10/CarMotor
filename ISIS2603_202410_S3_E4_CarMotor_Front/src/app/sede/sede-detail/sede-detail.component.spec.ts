/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SedeDetailComponent } from './sede-detail.component';
import { SedeDetail } from '../sede-detail';
import { faker } from '@faker-js/faker';
import { HttpClientModule } from '@angular/common/http';
import { Advisor } from '../../advisor/advisor.model';


describe('SedeDetailComponent', () => {
  let component: SedeDetailComponent;
  let fixture: ComponentFixture<SedeDetailComponent>;
  let debug: DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ SedeDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SedeDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    const asesores: Advisor[] = [];
    for (let i = 0; i < 3; i++) {
      const asesor = new Advisor (
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
      );
      asesores.push(asesor);
    }


    component.sedeDetail = new SedeDetail(
        faker.number.int(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.lorem.sentence(),
        faker.image.url(),
    );

    fixture.detectChanges();
    debug = fixture.debugElement;
    });
    
  });

  let debug: DebugElement;
  let component: SedeDetailComponent;


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a p.h3.p-3 element with sedeDetail.nombre', () => {
    const element: HTMLElement = debug.query(By.css('p.h3.p-3')).nativeElement;
    expect(element.textContent).toContain(component.sedeDetail.nombre);
  });

  it('should have an img element with src= sedeDetail.imagen', () => {
    expect(debug.query(By.css('img')).attributes['src']).toEqual(
      component.sedeDetail.imagen
    );
  });
 
  it('should have an img element with alt= sedeDetail.nombre', () => {
    expect(debug.query(By.css('img')).attributes['alt']).toEqual(
      component.sedeDetail.nombre
    );
  });

  it('should have one dd tag for component.sedeDetail.direccion', () => {
    const allDt : DebugElement[] = debug.queryAll(By.css('dt'));
    const node = allDt.find((value) => {
      return value.nativeElement.textContent == 'Direccion';
    });
    expect(node?.nativeElement.nextSibling.textContent).toContain(component.sedeDetail.direccion);
  });
 
  it('should have one dd tag for component.sedeDetail.telefono', () => {
    const allDt : DebugElement[] = debug.queryAll(By.css('dt'));
    const node = allDt.find((value) => {
      return value.nativeElement.textContent == 'Telefono';
    });
    expect(node?.nativeElement.nextSibling.textContent).toContain(component.sedeDetail.telefono);
  });
 
  it('should have one dd tag for component.sedeDetail.horarioAtencion', () => {
    const allDt : DebugElement[] = debug.queryAll(By.css('dt'));
    const node = allDt.find((value) => {
      return value.nativeElement.textContent == 'Horario de atencion';
    });
    expect(node?.nativeElement.nextSibling.textContent).toContain(component.sedeDetail.horarioAtencion);
  });

