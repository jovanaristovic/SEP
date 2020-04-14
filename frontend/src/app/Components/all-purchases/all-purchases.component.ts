import { Component, OnInit } from '@angular/core';
import {UserService} from '../../Services/users/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-all-purchases',
  templateUrl: './all-purchases.component.html',
  styleUrls: ['./all-purchases.component.css']
})
export class AllPurchasesComponent implements OnInit {

  user: any;
  purchases: any;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('loggedUser'));
    this.userService.getUserByUsername(user.sub).subscribe(purchases => {
      console.log(purchases);
      this.purchases = purchases;
    });
  }

}
