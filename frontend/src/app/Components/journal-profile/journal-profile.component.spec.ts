import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalProfileComponent } from './journal-profile.component';

describe('JournalProfileComponent', () => {
  let component: JournalProfileComponent;
  let fixture: ComponentFixture<JournalProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JournalProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JournalProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
