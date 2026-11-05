import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Account { accountId: number; username: string; }

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor (private http: HttpClient) {
  }

  getAccounts(): Observable<Account[]> {
      return this.http.get<Account[]>
      ('http://localhost:8080/accounts');
    }
}
