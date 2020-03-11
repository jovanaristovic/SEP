import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {UserService} from '../../Services/users/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: any;
  confirmPassword: any;

  constructor(private userService: UserService, private router: Router, public toastr: ToastrManager) { }

  ngOnInit() {
    this.user = {email: '', password: '', name: '', lastName: '', city: '', number: ''};
  }

  register(): void {

    this.userService.register(this.user).subscribe(newUser => {
      this.user = newUser;
      this.toastr.successToastr('You are registered', 'Success');
      // @ts-ignore
      this.router.navigate(['/home'], {});
    });
  }
}
