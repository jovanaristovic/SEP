import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewJournalComponent } from './new-journal.component';

describe('NewJournalComponent', () => {
  let component: NewJournalComponent;
  let fixture: ComponentFixture<NewJournalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewJournalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewJournalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
