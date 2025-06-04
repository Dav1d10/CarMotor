import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsesorListComponent } from './asesor-list.component';

describe('AsesorListComponent', () => {
  let component: AsesorListComponent;
  let fixture: ComponentFixture<AsesorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AsesorListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AsesorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
