import { Role } from './../model/role.model';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from "../model/user.model";
import { TokenStorageService } from '../auth/token-storage.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  board: string;
  errorMessage: string;
  users: User[];

  constructor(private router: Router,private userService: UserService,private token: TokenStorageService) { }

  ngOnInit() {
    this.userService.getClientssByTeam().subscribe(
        data => {
          this.users= data.result;
        },
        error => {
          this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
        }
      )
    }
    addClientUser(): void {
      this.router.navigate(['adminclientignup']);
    };

    editUser(user: User): void {
      window.localStorage.removeItem("editUserId");
      window.localStorage.setItem("editUserId", user.id.toString());
      this.router.navigate(['edit-client']);
    };

    deleteClient(user: User): void {
      this.userService.deleteClient(user.id)
        .subscribe( data => {
          this.users = this.users.filter(u => u !== user);
          alert("User Deleted Successfully!!")
          this.router.navigate(['client-list']);
        })
    };
    
    approveUser(user: User): void {
     if(confirm("Are you Sure the Client Paid the minimum Fees?")){
      this.userService.approveClient(user)
      .subscribe( data => {
        if(data.status === 200) {
          window.location.reload();
        }else {
          alert(data.message);
        }
      })
     }
 
  };
  
}
