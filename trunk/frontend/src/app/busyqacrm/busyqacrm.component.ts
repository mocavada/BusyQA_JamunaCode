import { TokenStorageService } from './auth/token-storage.service';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/finally';
import { faTimes, faPlus } from '@fortawesome/free-solid-svg-icons';
import { without, findIndex } from 'lodash';
import { trigger, state, style, transition, animate, keyframes} from '@angular/animations';

import { library } from '@fortawesome/fontawesome-svg-core';
library.add(faTimes, faPlus);



@Component({
  selector: 'app-busy-qa-crm',
  templateUrl: './busyqacrm.component.html',
  styleUrls: ['./busyqacrm.component.css']
})
export class BusyQaCrmComponent implements OnInit {
  roles: string[];
  authority: string;
  username: string;
  private urlRoot = environment.apiUrl;


  showViewUser: boolean;
  showCreateRole: boolean;

  constructor(private http: HttpClient,
              private router: Router,
              private tokenStorage: TokenStorageService) {

              }

    ngOnInit() {

      this.roleAccess();
      this.showViewUser = true;
      this.showCreateRole = false;
      console.log('url ' + this.router.url);
    }


    roleAccess() {
      if (this.tokenStorage.getToken()) {
        this.roles = this.tokenStorage.getAuthorities();

      // Find the User ROLE
        this.roles.find(role => {
          if (this.roles.includes('ROLE_ADMIN' || 'ADMIN')) {
            this.authority = 'admin';
            return false;
          } else if (this.roles.includes('ROLE_AUDIT' || 'AUDIT')) {
            this.authority = 'audit';
            return false;
          } else if (this.roles.includes('ROLE_SALES' || 'SALES')) {
            this.authority = 'sales';
            return false;
          } else {
            this.authority = 'user';
            return true;
          }
        });

    //     // Find the User USERNAME
        this.username = this.tokenStorage.getUsername();
      }
    }

}
