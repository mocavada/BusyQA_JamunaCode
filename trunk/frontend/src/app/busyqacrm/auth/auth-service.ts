import { ApiResponse } from '../model/api-response';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import { environment } from '../../../environments/environment';

import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl = environment.apiUrl;
  private loginUrl = this.apiUrl + '/api/auth/signin';
  private signupUrl = this.apiUrl + '/api/auth/signup';
  private resetCredentialUrl = this.apiUrl + '/api/auth/resetpassword/';
  info: any;

  constructor(private http: HttpClient,
              private token: TokenStorageService,
              public jwtHelper: JwtHelperService) {
  }

  public isAuthenticated(): boolean {
    // const token = localStorage.getItem('token');
    const token = this.token.getToken();
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  resetUserPassword(credentials: AuthLoginInfo): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()};

    return this.http.put<ApiResponse>(this.resetCredentialUrl + this.info.username, credentials, httpOptions);
  }

}
