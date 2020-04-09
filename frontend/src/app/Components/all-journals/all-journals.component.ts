import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-all-journals',
  templateUrl: './all-journals.component.html',
  styleUrls: ['./all-journals.component.css']
})
export class AllJournalsComponent implements OnInit {

  journals: any;


  constructor(private journalService: JournalService, private router: Router) {

    this.journalService.getAll().subscribe(journals => {
      this.journals = journals;
      console.log(this.journals);
    });
  }

  ngOnInit() {
  }

  journalProfile(id) {
    this.router.navigate(['/journal', id]);

  }

  buyMagazine(journalId: any) {
    this.journalService.buyJournal(journalId);
  }


  addSubscriptionTime(id: any) {
    this.router.navigate(['/subscription/period', id]);
  }


}
