import { Injectable } from '@angular/core';

import { Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private router: Router) { }


    register(user) {
        return this.httpClient.post('api/user/register', user) as Observable<any>;
    }

    getToken(): string {
        const currentUser = JSON.parse(localStorage.getItem('loggedUser'));
        if (currentUser !== null) {
            currentUser.token = currentUser.token.replace(':', '');
        }
        const token = currentUser && currentUser.token;
        return token ? token : '';
    }

    getLoggedUserType() {
        const user = JSON.parse(localStorage.getItem('loggedUser'));
        let userRole;
        if (user === null) {
            userRole = '';
        }  else {
            for (const role of user.roles) {
                if (role.authority === 'ADMIN') {
                    userRole = 'ADMIN';
                } else if (role.authority === 'ROLE_USER') {
                    userRole = 'ROLE_USER';
                } else if (role.authority === 'REDACTOR') {
                    userRole = 'REDACTOR';
                } else if (role.authority === 'REVIEWER') {
                    userRole = 'REVIEWER';
                } else if (role.authority === 'MAIN_REDACTOR') {
                    userRole = 'MAIN_REDACTOR';
                } else if (role.authority === 'REDACTOR_SCIENCE_FIELD') {
                    userRole = 'REDACTOR_SCIENCE_FIELD';
                } else if (role.authority === 'AUTHOR') {
                    userRole = 'AUTHOR';
                }
            }
        }
        return userRole;
    }


    isLoggedIn() {
        const user = JSON.parse(localStorage.getItem('loggedUser'));

        if (user === null) {
            return false;
        }
        const expiredDate = new Date(new Date(parseInt(user.exp, 10) * 1000));
        const nowDate = new Date();
        if ((expiredDate.getDate() <= nowDate.getDate()) &&
            (expiredDate.getTime() <= nowDate.getTime())) {
            this.logout();
            return false;
        }
        return true;

    }

    login(user): any {
        return this.httpClient.post('api/auth/login', user, {observe: 'response'}).pipe(map(response => response));
    }


    getUserByUsername(username): any {
        return this.httpClient.get('api/user/'.concat(username));
    }

    logout() {
        localStorage.removeItem('loggedUser');
        this.router.navigate(['/login']);
    }
}
