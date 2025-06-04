import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorarioTestDriveListComponent } from './horario-testdrive-list.component';

describe('HorarioTestdriveListComponent', () => {
  let component: HorarioTestDriveListComponent;
  let fixture: ComponentFixture<HorarioTestDriveListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HorarioTestDriveListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HorarioTestDriveListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
