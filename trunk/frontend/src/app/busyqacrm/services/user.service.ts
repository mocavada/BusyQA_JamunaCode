import { LeadRequest } from './../model/lead-request';
import { TokenStorageService } from './../auth/token-storage.service';
import { User } from '../model/user';
import { ApiResponse } from '../model/api-response';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Subject, Observable } from 'rxjs';

import { Lead } from './../model/lead';
import { InternalFormsSharedModule } from '@angular/forms/src/directives';
import { UserRequest } from '../model/user-request';

// const httpOptions = {
//   'Content-Type': 'application/json'
// };

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = environment.apiUrl;
  private userUrl = this.apiUrl + '/user';
  private pmUrl = this.apiUrl + '/pm';
  private adminUrl = this.apiUrl + '/admin';
  info: any;

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getPMBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getUsersByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    return this.http.get<ApiResponse>(this.adminUrl + '/users/' + this.info.username);
  }

  getClientssByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    return this.http.get<ApiResponse>(this.adminUrl + '/client/' + this.info.username);
  }

  getClientsByUsername(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    return this.http.get<ApiResponse>(this.userUrl + '/client/' + this.info.username);
  }

  getPmUserByTeam(): Observable<ApiResponse> {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    return this.http.get<ApiResponse>(this.pmUrl + '/users/' + this.info.username);
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.adminUrl + '/user/' + id);
  }

  getClientById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.adminUrl + '/oneclient/' + id);
  }

  getunregisteredClient(path: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.apiUrl + path);
  }

  getregisteredClient(path: string): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.apiUrl + path);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.adminUrl + '/user/' + user.id, user);
  }

  updateClient(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.adminUrl + '/oneclient/' + user.id, user);
  }

  updateUnregistredClient(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.apiUrl + '/unregistered-client/' + user.id, user);
  }

  updateregistredClient(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.apiUrl + '/save-client/' + user.id, user);
  }

  deleteUser(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.adminUrl + '/user/' + id);
  }

  deleteClient(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.adminUrl + '/oneclient/' + id);
  }

  approveClient(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.apiUrl + '/registered-client/' + user.id, user);
  }

}
