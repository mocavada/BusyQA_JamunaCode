import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { PmComponent } from './pm/pm.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditUserComponent } from './edit-user/edit-user.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { AdminregisterComponentComponent } from './adminregister/adminregister-component.component';
import { ClientListComponent } from './client-list/client-list.component';
import { AdminclientignupComponent } from './adminclientignup/adminclientignup.component';
import { ClientpanelComponent } from './clientpanel/clientpanel.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { UnregisteredClientComponent } from './unregistered-client/unregistered-client.component';
import { RegisteredClientComponent } from './registered-client/registered-client.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    EditUserComponent,
    PmComponent,
    AdminregisterComponentComponent,
    ClientListComponent,
    AdminclientignupComponent,
    ClientpanelComponent,
    EditClientComponent,
    UnregisteredClientComponent,
    RegisteredClientComponent  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot(),
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
