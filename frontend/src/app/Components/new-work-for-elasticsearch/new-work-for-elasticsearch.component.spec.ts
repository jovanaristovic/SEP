import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewWorkForElasticsearchComponent } from './new-work-for-elasticsearch.component';

describe('NewWorkForElasticsearchComponent', () => {
  let component: NewWorkForElasticsearchComponent;
  let fixture: ComponentFixture<NewWorkForElasticsearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewWorkForElasticsearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewWorkForElasticsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
