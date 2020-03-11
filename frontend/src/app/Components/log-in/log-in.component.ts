import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {JwtHelperService} from '@auth0/angular-jwt';
import {UserService} from '../../Services/users/user.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  user: any;
  token: any;
  wrongUsernameOrPass: boolean;
  jwtHelper: any;


  constructor(private userService: UserService, private router: Router, public toastr: ToastrManager) {
    this.jwtHelper = new JwtHelperService();
  }


  ngOnInit() {
    this.user = {username: '', password: ''};
    this.token = {accessToken: '', expiresIn: ''};

  }


  login() {
    this.wrongUsernameOrPass = false;
    const headers = new Headers();

    this.userService.login(this.user).subscribe(value => {
      console.log(headers);
      headers.append('Authorization', value.headers.get('Authorization'));
      const userFromToken = this.jwtHelper.decodeToken(headers.get('Authorization'));
      userFromToken.token = headers.get('Authorization');
      localStorage.setItem('loggedUser', JSON.stringify(userFromToken));

      location.reload();
      this.router.navigate(['/home']);
    }, error2 => {
      this.wrongUsernameOrPass = true;
      this.toastr.errorToastr('Invalid username or password!', 'Error');
    });

  }





}

