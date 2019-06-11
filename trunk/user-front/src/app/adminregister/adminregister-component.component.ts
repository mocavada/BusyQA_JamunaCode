import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {AdminSignUpInfo} from "../auth/adminsingup-info";

@Component({
  selector: 'app-adminregister-component',
  templateUrl: './adminregister-component.component.html',
  styleUrls: ['./adminregister-component.component.css']
})
export class AdminregisterComponentComponent implements OnInit {

  selectedItems2 = [];
  dropdownSettings2 = {};
  dropdownList2 = [];

  //Team
  teamSelectedItems2 = [];
  teamDropdownSettings2 = {};
  teamDropdownList2 = [];


  form: any = {};
  asignupInfo: AdminSignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.dropdownList2 = [
      { id: 1, name: 'ROLE_ADMIN' },
      { id: 2, name: 'ROLE_USER' },
      { id: 3, name: 'ROLE_PM' },
      { id: 4, name: 'ROLE_ADMIN' },
      { id: 5, name: 'ROLE_USER' },
      { id: 6, name: 'ROLE_PM' }
    ];
    this.dropdownSettings2 = {
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
    this.teamDropdownList2 = [
      { id: 1, name: 'TEAM_SALES' },
      { id: 2, name: 'TEAM_ACCOUNTS' },
      { id: 3, name: 'TEAM_ADMIN' },
      { id: 4, name: 'TEAM_UNASSIGNED' },
    ];

    this.teamDropdownSettings2 = {
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
  }

  onItemSelect2(item: any) {
    console.log(item);
  }
  onSelectAll2(items: any) {
    console.log(items);
  }

  onSubmit() {
    console.log(this.form);

    this.asignupInfo = new AdminSignUpInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.roles,
      this.form.teams);

    this.authService.adminsignUp(this.asignupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;

      },
      error => {
        console.log(error);
        alert("Couldnt Create New user");
        this.isSignUpFailed = true;
      }
    );


  }
}

