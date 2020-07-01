import { TestBed } from '@angular/core/testing';

import { WorkUddService } from './work-udd.service';

describe('WorkUddService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkUddService = TestBed.get(WorkUddService);
    expect(service).toBeTruthy();
  });
});
