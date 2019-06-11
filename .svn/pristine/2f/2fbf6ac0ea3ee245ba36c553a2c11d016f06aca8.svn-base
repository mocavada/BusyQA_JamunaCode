import { Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";
import { UserService } from '../services/user.service';
import { BrowserModule } from '@angular/platform-browser';
import { SignUpInfo } from '../auth/signup-info';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-registered-client',
  templateUrl: './registered-client.component.html',
  styleUrls: ['./registered-client.component.css']
})
export class RegisteredClientComponent implements OnInit {
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

  form: any = {};
signupInfo: SignUpInfo;
isSignedUp = false;
isSignUpFailed = false;
errorMessage = '';


constructor(private authService: AuthService,private formBuilder: FormBuilder,private router: Router, private userService: UserService) { }

ngOnInit() {
  var str  = window.location.pathname;
  this.editForm = this.formBuilder.group({
    id: [''],
    password:['', Validators.required],
    name: [''],
    username: [''],
    email: [''],
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
    clientCourse: [''],
    clientStatus: [''],
    paymentPlan: [''],
    paymentStatus: [''],
    amountPaid: [''],
    totalAmount: [''],
    lastPaidDate: [''],
    nextPaymentDate:[''],
  });
  this.userService.getregisteredClient(str)
    .subscribe( data => {
      if(data.status === 200) {
        this.editForm.setValue(data.result);
      }else {
        alert(data.message);
        this.router.navigate(['home']);
      }
    });
}

onItemSelect(item: any) {
  console.log(item);
}
onSelectAll(items: any) {
  console.log(items);
}

countryList: Array<any> = [
  { name: 'Canada', states: ['Ontario', 'Quebec', 'Nova Scotia'] , cities: ['Missisuaga', 'Markham','Toronto','Waterloo'] },
  { name: 'USA', states: ['Newyork', 'Michigan', 'california'], cities: ['Newyork', 'Orlando'] },
];
cities: Array<any>;
states: Array<any>;
changeCountry(count) {
  this.cities = this.countryList.find(con => con.name == count).cities;
  this.states = this.countryList.find(con => con.name == count).states;
}

onSubmit() {
  this.userService.updateregistredClient(this.editForm.value)
    .pipe(first())
    .subscribe(
      data => {
        if(data.status === 200) {
          alert('Your Password Has been Set.... Please Login');
          this.router.navigate(['home']);
        }else {
          alert(data.message);
          this.router.navigate(['home']);
        }
      });
}

}

