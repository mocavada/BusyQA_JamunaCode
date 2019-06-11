/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { BusyQaCrmComponent } from './busyqacrm.component';

describe('BusyQaCrmComponent', () => {
  let component: BusyQaCrmComponent;
  let fixture: ComponentFixture<BusyQaCrmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusyQaCrmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusyQaCrmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
