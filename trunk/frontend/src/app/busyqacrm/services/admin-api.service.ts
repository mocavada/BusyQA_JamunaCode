import { TokenStorageService } from './../auth/token-storage.service';
import { User } from '../model/user';
import { ApiResponse } from '../model/api-response';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminApiService {

  apiUrl = environment.apiUrl;
  private adminUrl = this.apiUrl + '/admin';

constructor() { }

}
