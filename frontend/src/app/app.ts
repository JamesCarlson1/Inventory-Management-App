import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AccountService, Account } from './account';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  accounts: Account[] = [];
  constructor (private accountService: AccountService) {}
  ngOnInit(): void {
    this.accountService.getAccounts().subscribe(data => this.accounts = data);
  }
}
