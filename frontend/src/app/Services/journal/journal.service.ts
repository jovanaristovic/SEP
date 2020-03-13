import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  newJournal(journal) {
    return this.httpClient.post('api/journal/new', journal) as Observable<any>;
  }

  getAll() {
    return this.httpClient.get('api/journal/all') as Observable<any>;
  }

  getById(id) {
    return this.httpClient.get('api/journal/'.concat(id)) as Observable<any>;
  }
}
