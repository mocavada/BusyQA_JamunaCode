import { HomeComponent } from './home/home.component';
import { CrmHeaderComponent } from './header/crm-header.component';
import { BusyQaCrmComponent } from './busyqacrm/busyqacrm.component';
import { FooterComponent } from './footer/footer.component';

// AUTH
import { RegisterComponent } from './busyqacrm/auth/register/register.component';
import { LoginComponent } from './busyqacrm/auth/login/login.component';
import { ResetPasswordComponent } from './busyqacrm/auth/reset-password/reset-password.component';

// ADMIN
import { AdminComponent } from './busyqacrm/_admin/admin.component';
import { UsersListComponent } from './busyqacrm/_admin/users-list/users-list.component';
import { AddUsersComponent } from './busyqacrm/_admin/add-users/add-users.component';

// AUDIT
import { AuditComponent } from './busyqacrm/_audit/audit.component';
import { StudentsListComponent } from './busyqacrm/_audit/students-list/students-list.component';

// SALES
import { SalesComponent } from './busyqacrm/_sales/sales.component';
import { LeadsListComponent } from './busyqacrm/_sales/leads-list/leads-list.component';

import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';


// DEPENDENCIES
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injectable } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpInterceptor, HttpRequest, HttpHandler, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AutoSizeInputModule } from 'ngx-autosize-input';

import { httpInterceptorProviders } from './busyqacrm/auth/auth-interceptor';
import { routes } from './app-routing.module';
import { JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';

// ANGULAR MATERIAL
import {MatButtonModule,
        MatCheckboxModule,
        MatTabsModule} from '@angular/material';
import {MatGridListModule} from '@angular/material/grid-list';

import { AppComponent } from './app.component';




@NgModule({
   declarations: [
      AppComponent,
      CrmHeaderComponent,
      BusyQaCrmComponent,
      FooterComponent,
      ResetPasswordComponent,
      LoginComponent,
      RegisterComponent,
      HomeComponent,
      AdminComponent,
      UsersListComponent,
      AddUsersComponent,
      AuditComponent,
      StudentsListComponent,
      SalesComponent,
      LeadsListComponent
   ],
   imports: [
      BrowserModule,
      BrowserAnimationsModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule,
      FontAwesomeModule,
      AutoSizeInputModule,
      routes,
      NgMultiSelectDropDownModule.forRoot(),
      MatButtonModule,
      MatCheckboxModule,
      MatTabsModule,
      MatGridListModule
   ],
   providers: [
      httpInterceptorProviders,
      { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
      JwtHelperService
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
