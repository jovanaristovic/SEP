import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';

@Component({
  selector: 'app-add-subscription-period',
  templateUrl: './add-subscription-period.component.html',
  styleUrls: ['./add-subscription-period.component.css']
})
export class AddSubscriptionPeriodComponent implements OnInit {

  subscriptionPeriod = 1;
  id: any;
  href: any;

  constructor(private journalService: JournalService, private router: Router) { }

  ngOnInit() {
    this.href = this.router.url;
    this.id = this.href.split('/')[3];
    console.log(this.id);

  }

  cancel() {
    this.router.navigate(['/journal/all']);
  }

  subscribeToMagazine() {
    this.journalService.subscribe(this.id, this.subscriptionPeriod);

  }

}
