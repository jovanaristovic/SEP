import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllJournalsComponent } from './all-journals.component';

describe('AllJournalsComponent', () => {
  let component: AllJournalsComponent;
  let fixture: ComponentFixture<AllJournalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllJournalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllJournalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
