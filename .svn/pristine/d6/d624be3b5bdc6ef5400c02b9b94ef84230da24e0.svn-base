import { Role } from './../model/role.model';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from "../model/user.model";
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-clientpanel',
  templateUrl: './clientpanel.component.html',
  styleUrls: ['./clientpanel.component.css']
})
export class ClientpanelComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: User[];

  constructor(private router: Router,private userService: UserService,private token: TokenStorageService) { }

  ngOnInit() {
  this.userService.getClientsByUsername().subscribe(
      data => {
        this.users= data.result;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    )
  }

}
