import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DOCUMENT} from '@angular/common';
import {ToastrManager} from 'ng6-toastr-notifications';

@Injectable({
  providedIn: 'root'
})
export class JournalService {

  url: string;
  constructor(private httpClient: HttpClient, private router: Router, @Inject(DOCUMENT) private document: Document,public toastr: ToastrManager) { }

  newJournal(journal) {
    return this.httpClient.post('api/journal/new', journal) as Observable<any>;
  }

  getAll() {
    return this.httpClient.get('api/journal/all') as Observable<any>;
  }

  getById(id) {
    return this.httpClient.get('api/journal/'.concat(id)) as Observable<any>;
  }

  buyJournal(id) {
    return this.httpClient.get('api/journal/buy/journal/'.concat(id)).subscribe(url => {

      this.document.location.href = url['url'];
      this.httpClient.get(url['url']).subscribe(ret => {
        console.log('success');
      });
    }, error => {
      this.httpClient.get(error).subscribe(value => {
        console.log('error');
      });
    });

  }

  buyWork(id) {
    return this.httpClient.get('api/journal/buy/work/'.concat(id)).subscribe(url => {

      this.document.location.href = url['url'];
      this.httpClient.get(url['url']).subscribe(ret => {
        console.log('success');
      });
    }, error => {
      this.httpClient.get(error).subscribe(value => {
        console.log('error');
      });
    });
  }

  subscribe(magazineId, subscriptionPeriod): any {
    return this.httpClient.get('api/journal/subscribe/'.concat(magazineId).concat('/').concat(subscriptionPeriod)).subscribe(url => {
      console.log(url['url']);
      // @ts-ignore
      // this.router.navigate([url["url"]]);
      // window.open(url["url"], "_blank");
      this.toastr.successToastr('Waiting for PayPal', 'PayPal is processing your subscription');
      this.document.location.href = url['url'];
      // this.http.get(url['url']).subscribe(ret =>{
      //    console.log('success');
      // });
    }, error => {
      this.httpClient.get(error).subscribe(value => {
        console.log('error');
      });
    });
  }
}
