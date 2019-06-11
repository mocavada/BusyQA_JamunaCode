import { Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";
import { UserService } from '../services/user.service';
import { RoleAndTeam } from "./role-and-team";
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User;
  username: string;
  teams: string[];
  roles: string[];
  rolesAndTeams: RoleAndTeam[];
  editForm: FormGroup;
  private fieldArray: Array<any> = [];
  private fieldArray2: Array<any> = [];
  private newAttribute: any = {};


  selectedItems = [];
  dropdownSettings = {};
  dropdownList = [];

  //Team
  teamSelectedItems = [];
  teamDropdownSettings = {};
  teamDropdownList = [];


  constructor(private formBuilder: FormBuilder,private router: Router, private userService: UserService) { }

  ngOnInit() {
    let userId = window.localStorage.getItem("editUserId");
    if(!userId) {
      alert("Invalid action.")
      this.router.navigate(['admin']);
      return;
    }
    this.dropdownList = [
      { id: 1, name: 'ROLE_ADMIN' },
      { id: 2, name: 'ROLE_USER' },
      { id: 3, name: 'ROLE_PM' },
      { id: 4, name: 'ROLE_ADMIN' },
      { id: 5, name: 'ROLE_USER' },
      { id: 6, name: 'ROLE_PM' }
    ];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      scrollableHeight: '300px',
      scrollable: true,
      allowSearchFilter: false
    };

    this.teamDropdownList = [
      { id: 1, name: 'TEAM_SALES' },
      { id: 2, name: 'TEAM_ACCOUNTS' },
      { id: 3, name: 'TEAM_ADMIN' },
      { id: 4, name: 'TEAM_UNASSIGNED' },
    ];

    this.teamDropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      scrollableHeight: '300px',
      scrollable: true,
      allowSearchFilter: false
    };

    this.editForm = this.formBuilder.group({
      id: [''],
      password:[''],
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      roles: [],
      teams: [],
      status:[''],
      statusAsOfDay:['']
    });
    this.userService.getUserById(+userId)
      .subscribe( data => {
        this.editForm.setValue(data.result);
      });
  }

  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  onSubmit() {
    this.userService.updateUser(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(data.status === 200) {
            alert('User updated successfully.');
            this.router.navigate(['admin']);
          }else {
            alert(data.message);
          }
        },
        error => {
          alert("Couldnt update the user");
        });
  }

}
