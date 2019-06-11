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
  selector: 'app-unregistered-client',
  templateUrl: './unregistered-client.component.html',
  styleUrls: ['./unregistered-client.component.css']
})
export class UnregisteredClientComponent implements OnInit {
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
      password:[''],
      name: ['', Validators.required],
      username: [''],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      address: ['', Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],
      trainingLocation: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', Validators.required],
      emergencyPhoneNumber: ['', Validators.required],
      aboutUs: ['', Validators.required],
      isRegisteredStudent:'YES',
      roles: 'ROLE_CLIENT',
      teams: 'TEAM_CLIENT',
      status:'NO',
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
    this.userService.getunregisteredClient(str)
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
    { name: 'Canada', states: ['Select A State','Ontario', 'Quebec', 'Nova Scotia'] , cities: ['Select A City', 'Missisuaga', 'Markham','Toronto','Waterloo', 'Online'] },
    { name: 'USA', states: ['Select A State', 'Newyork', 'Michigan', 'california'], cities: ['Select A City','Newyork', 'Orlando', 'Online'] },
  ];
  cities: Array<any>;
  states: Array<any>;
  changeCountry(count) {
    this.cities = this.countryList.find(con => con.name == count).cities;
    this.states = this.countryList.find(con => con.name == count).states;
  }

  onSubmit() {
    this.userService.updateUnregistredClient(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(data.status === 200) {
            alert('You have been registered succesfully....');
            this.router.navigate(['home']);
          }else {
            alert(data.message);
          }
        });
  }

}
