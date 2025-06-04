import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsesorDetailComponent } from './asesor-detail.component';

describe('AsesorDetailComponent', () => {
  let component: AsesorDetailComponent;
  let fixture: ComponentFixture<AsesorDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AsesorDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AsesorDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
