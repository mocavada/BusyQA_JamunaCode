import { UserService } from '../services/user.service';
import { Component, OnInit } from '@angular/core';

import { UserResponse } from '../model/user-response';
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['.././busyqacrm.component.css']
})

export class SalesComponent implements OnInit {
  errorMessage: string;
  user: UserResponse;
  username: string;


  constructor(private token: TokenStorageService, private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    // this.username = this.token.getUsername();

    // this.userService.getUserInfo(this.username).subscribe(
    //   data => {
    //     this.user = data.result;
    //     console.log(this.user);
    //   },
    //   error => {
    //     this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
    //   }
    // );
  }
}
