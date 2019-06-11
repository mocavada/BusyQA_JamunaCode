import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';

@Component({
  selector: 'app-adminclientignup',
  templateUrl: './adminclientignup.component.html',
  styleUrls: ['./adminclientignup.component.css']
})
export class AdminclientignupComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  
  constructor(private authService: AuthService,private router: Router) { }
  
    ngOnInit() { }
  
    onSubmit() {
      console.log(this.form);
  
      this.signupInfo = new SignUpInfo(
        this.form.name,
        this.form.username,
        this.form.email,
        this.form.password,
        this.form.clientCourse);
  
      this.authService.signUp(this.signupInfo).subscribe(
        data => {
          console.log(data);
          this.isSignedUp = true;
          this.isSignUpFailed = false;
  
        },
        error => {
          console.log(error);
          this.errorMessage = error.error.message;
          this.isSignUpFailed = true;
        }
      );
      this.router.navigate(['adminclientignup']);
  
    }
  }