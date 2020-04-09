import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubscriptionPeriodComponent } from './add-subscription-period.component';

describe('AddSubscriptionPeriodComponent', () => {
  let component: AddSubscriptionPeriodComponent;
  let fixture: ComponentFixture<AddSubscriptionPeriodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSubscriptionPeriodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSubscriptionPeriodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
