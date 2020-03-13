import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  newWork(work) {
    return this.httpClient.post('api/work/new', work) as Observable<any>;
  }
}
