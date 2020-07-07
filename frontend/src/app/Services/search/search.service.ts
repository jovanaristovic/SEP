import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private httpClient: HttpClient, private router: Router) {
  }


  search(searchDto) {
    return this.httpClient.post('api/search', searchDto) as Observable<any>;
  }

  combineSearch(combineSearchDto) {
    return this.httpClient.post('api/search/combine', combineSearchDto) as Observable<any>;
  }
}
