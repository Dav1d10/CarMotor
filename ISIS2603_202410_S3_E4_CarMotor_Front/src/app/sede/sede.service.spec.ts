/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SedeService } from './sede.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('Service: Sede', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SedeService]
    });
  });

  it('should ...', inject([SedeService], (service: SedeService) => {
    expect(service).toBeTruthy();
  }));
});
