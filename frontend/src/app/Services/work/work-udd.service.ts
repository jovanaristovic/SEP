import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkUddService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  newWorkUdd(work) {
    return this.httpClient.post('api/work/udd/new', work) as Observable<any>;
  }
}
