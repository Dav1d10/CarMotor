import { TestBed, async, inject } from '@angular/core/testing';
import { EntidadBancariaService } from './entidad-bancaria.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('Service: entidad-bancaria', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EntidadBancariaService]
    });
  });

  it('should ...', inject([EntidadBancariaService], (service: EntidadBancariaService) => {
    expect(service).toBeTruthy();
  }));
});
