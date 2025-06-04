import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntidadBancariaDetailComponent } from './entidad-bancaria-detail.component';

describe('EntidadBancariaDetailComponent', () => {
  let component: EntidadBancariaDetailComponent;
  let fixture: ComponentFixture<EntidadBancariaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EntidadBancariaDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EntidadBancariaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
