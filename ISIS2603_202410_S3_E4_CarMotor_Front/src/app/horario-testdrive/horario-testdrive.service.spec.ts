import { TestBed } from '@angular/core/testing';

import { HorarioTestDriveService } from './horario-testdrive.service';

describe('HorarioTestdriveService', () => {
  let service: HorarioTestDriveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HorarioTestDriveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
