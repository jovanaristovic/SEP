import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScientificFieldService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  getAll() {
    return this.httpClient.get('api/scientificField/all') as Observable<any>;
  }
}
