import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HorarioTestDriveDetailComponent } from './horario-testdrive-detail.component';

describe('HorarioTestdriveDetailComponent', () => {
  let component: HorarioTestDriveDetailComponent;
  let fixture: ComponentFixture<HorarioTestDriveDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HorarioTestDriveDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HorarioTestDriveDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
