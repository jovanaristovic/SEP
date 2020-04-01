import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-journal-profile',
  templateUrl: './journal-profile.component.html',
  styleUrls: ['./journal-profile.component.css']
})
export class JournalProfileComponent implements OnInit {
  journalWorks: any;
  id: any;
  href: any;

  constructor(private journalService: JournalService, private router: Router) { }

  ngOnInit() {
    this.href = this.router.url;

    this.id = this.href.split('/')[2];

    this.journalService.getById(this.id).subscribe(journal => {
      console.log(journal);
      this.journalWorks = journal.works;
    });
  }


  buyWork(id) {
    this.journalService.buyWork(id);
  }

}
