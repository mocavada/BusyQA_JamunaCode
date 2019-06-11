import { UnregisteredClientComponent } from './unregistered-client/unregistered-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { ClientpanelComponent } from './clientpanel/clientpanel.component';
import { AdminclientignupComponent } from './adminclientignup/adminclientignup.component';
import { ClientListComponent } from './client-list/client-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { PmComponent } from './pm/pm.component';
import { AdminComponent } from './admin/admin.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import {AdminregisterComponentComponent} from "./adminregister/adminregister-component.component";
import { RegisteredClientComponent } from './registered-client/registered-client.component';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'user',
        component: UserComponent
    },
    {
        path: 'pm',
        component: PmComponent
    },
    {
        path: 'admin',
        component: AdminComponent
    },
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'edit-user',
        component: EditUserComponent
    },
    {
        path: 'client-list',
        component: ClientListComponent
    },
    {
        path: 'unregistered-client/:id',
        component: UnregisteredClientComponent
    },
    {
        path: 'registered-client/:id',
        component: RegisteredClientComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
    path: 'adminsignup',
    component: AdminregisterComponentComponent
    },
    {
    path: 'clientpanel',
    component: ClientpanelComponent
    },
    {
    path: 'edit-client',
    component: EditClientComponent
    },
    {
    path: 'adminclientignup',
    component: AdminclientignupComponent
    },
 
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
