import { TestBed } from '@angular/core/testing';

import { ScientificFieldService } from './scientific-field.service';

describe('ScientificFieldService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScientificFieldService = TestBed.get(ScientificFieldService);
    expect(service).toBeTruthy();
  });
});
