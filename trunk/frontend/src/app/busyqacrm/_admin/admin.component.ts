import { UserService } from './../services/user.service';
import { UserRequest } from './../model/user-request';
import { UserResponse } from './../model/user-response';
import { User } from '../model/user';
import { Role } from '../model/role';

import { Component, OnInit } from '@angular/core';


import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['.././busyqacrm.component.css']
})
export class AdminComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: User[];


  constructor(private router: Router,
              private userService: UserService,
              private token: TokenStorageService) {}

    ngOnInit() {
      this.userService.getUsersByTeam().subscribe(
          data => {
            this.users = data.result;
            console.log(this.users);
          },
          error => {
            this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
          }
        );
      }

      editUser(user: User): void {
        window.localStorage.removeItem('editUserId');
        window.localStorage.setItem('editUserId', user.id.toString());
        this.router.navigate(['edit-user']);
      }
      addUser(): void {
        this.router.navigate(['adminsignup']);
      }

      deleteUser(user: User): void {
        this.userService.deleteUser(user.id)
          .subscribe( data => {
            this.users = this.users.filter(u => u !== user);
            alert('User Deleted Successfully!!');
            this.router.navigate(['home']);
            this.router.navigate(['admin']);
            this.router.navigate(['admin']);
          });
      }
    }
