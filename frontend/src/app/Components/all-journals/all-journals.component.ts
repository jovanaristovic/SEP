import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';

@Component({
  selector: 'app-all-journals',
  templateUrl: './all-journals.component.html',
  styleUrls: ['./all-journals.component.css']
})
export class AllJournalsComponent implements OnInit {

  journals: any;
  constructor(private journalService: JournalService) {

    this.journalService.getAll().subscribe(journals => {
      this.journals = journals;
      console.log(this.journals);
    });
  }

  ngOnInit() {
  }

  journalProfile(journal) {

  }

}
