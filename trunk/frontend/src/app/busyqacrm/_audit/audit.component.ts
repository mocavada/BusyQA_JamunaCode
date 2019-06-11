import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

import { UserResponse } from '../model/user-response';

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['.././busyqacrm.component.css']
})
export class AuditComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: UserResponse[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    // this.userService.getPmUserByTeam().subscribe(
    //     data => {
    //       this.users = data.result;
    //       console.log(this.users);
    //     },
    //     error => {
    //       this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
    //     }
    //   );
    // }
}

}

