import { AuthService } from '../../auth/auth-service';
import { SignUpInfo } from './../../auth/signup-info';

import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faTimes, faPlus } from '@fortawesome/free-solid-svg-icons';
import { without, findIndex } from 'lodash';




@Component({
  selector: 'app-add-users',
  templateUrl: './add-users.component.html',
  styleUrls: ['../.././busyqacrm.component.css']
})
export class AddUsersComponent implements OnInit {
  showForm: boolean;

  editForm: FormGroup;
  positions: FormArray;
  validMessage = '';
  signupInfo: SignUpInfo;
  private sub: Subscription;
  message: string;
  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private authService: AuthService) {
    this.showForm = true;
   }
  positionArray = ['ROLE_USER,TEAM_SALES', 'ROLE_ADMIN,TEAM_SALES',
  'ROLE_PM,TEAM_SALES', 'ROLE_USER,TEAM_ACCOUNTS',
  'ROLE_ADMIN,TEAM_ACCOUNTS', 'ROLE_PM,TEAM_ACCOUNTS',
  'ROLE_USER,TEAM_ADMIN', 'ROLE_ADMIN,TEAM_ADMIN',
  'ROLE_PM,TEAM_ADMIN', 'ROLE_USER,TEAM_UNASSIGNED'];

  ngOnInit() {
    this.updateForm();
  }

  toggleAptDisplay() {
    console.log(this.showForm);
    this.showForm = !this.showForm;
  }


  get formData() { return this.editForm.get('positions') as FormArray; }


  updateForm() {
    // const formControls = this.rolesArray.map(control => new FormControl(false));
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      positions: this.fb.array([])
    });

  }

  // createPosition(): FormGroup {
  //   return this.fb.group({
  //     name: ''
  //   });
  // }

  // addPosition(): void {
  //   this.positions = this.editForm.get('positions') as FormArray;
  //   this.positions.push(this.createPosition());
  // }

  onChange(isChecked: boolean, position: string) {
    const formArray = this.editForm.get('positions') as FormArray;

    if (isChecked) {
      formArray.push(new FormControl(position));
    } else {
      const index = formArray.controls.findIndex(x => x.value === position);
      formArray.removeAt(index);
    }
    console.log(formArray);
  }


  onAdd() {
    if (this.editForm.valid) {
      this.validMessage = 'The user information is added';
      this.authService.signUp(this.editForm.value).subscribe(
        data => {
          this.message = 'The user has been added!';
          return true;
        },
        error => console.error(error));
    } else {
      this.validMessage = 'Please make sure the inputs are valid!';
    }
    this.showForm = !this.showForm;
  }


}




