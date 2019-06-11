import { Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";
import { UserService } from '../services/user.service';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {

  user: User;
  username: string;
  teams: string;
  roles: string;
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
    this.editForm = this.formBuilder.group({
      id: [''],
      password:[''],
      name:[''],
      username:[''],
      email:[''],
      phoneNumber: [''],
      address: [''],
      country: [''],
      city: [''],
      trainingLocation: [''],
      state: [''],
      zipCode: [''],
      emergencyPhoneNumber: [''],
      aboutUs: [''],
      isRegisteredStudent:[''],
      roles: 'ROLE_CLIENT',
      teams: 'TEAM_CLIENT',
      status:[''],
      statusAsOfDay:[''],
      clientCourse: ['', Validators.required],
      clientStatus: ['', Validators.required],
      paymentPlan: ['', Validators.required],
      paymentStatus: ['', Validators.required],
      amountPaid: ['', Validators.required],
      totalAmount: ['', Validators.required],
      lastPaidDate: ['', Validators.required],
      nextPaymentDate:['', Validators.required]
    });
    this.userService.getClientById(+userId)
      .subscribe( data => {
        if(data.status === 200) {
          this.editForm.setValue(data.result);
        }else {
          alert(data.message);
          this.router.navigate(['client-list']);
        }
      });
  }

  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  onSubmit() {
    this.userService.updateClient(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(data.status === 200) {
            alert('User updated successfully.');
            this.router.navigate(['client-list']);
          }else {
            alert(data.message);
          }
        });
  }

}
