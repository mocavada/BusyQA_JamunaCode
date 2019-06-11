import { AuthGuardService } from './busyqacrm/services/auth-guard.service';
import { UsersListComponent } from './busyqacrm/_admin/users-list/users-list.component';
import { StudentsListComponent } from './busyqacrm/_audit/students-list/students-list.component';
import { LeadsListComponent } from './busyqacrm/_sales/leads-list/leads-list.component';
import { AddUsersComponent } from './busyqacrm/_admin/add-users/add-users.component';
import { BusyQaCrmComponent } from './busyqacrm/busyqacrm.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { RegisterComponent } from './busyqacrm/auth/register/register.component';
import { LoginComponent } from './busyqacrm/auth/login/login.component';
import { HomeComponent } from './home/home.component';

import { ResetPasswordComponent } from './busyqacrm/auth/reset-password/reset-password.component';


export const router: Routes = [
    { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'auth', children: [
      { path: 'signup', component: RegisterComponent },
      { path: 'login', component: LoginComponent },
      { path: 'resetPassword', component: ResetPasswordComponent, canActivate: [AuthGuardService] }
    ]},

    { path: 'dashboard', children: [
      { path: '', component: BusyQaCrmComponent, canActivate: [AuthGuardService] },
      { path: 'leads', component: LeadsListComponent, canActivate: [AuthGuardService] },
      { path: 'students', component: StudentsListComponent, canActivate: [AuthGuardService] },
      { path: 'users', component: UsersListComponent, canActivate: [AuthGuardService] },

    ]}

  ];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);



