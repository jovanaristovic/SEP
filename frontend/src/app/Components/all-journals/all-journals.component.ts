import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';
import {Router} from '@angular/router';
import {ɵAnimationRendererFactory} from '@angular/platform-browser/animations';
import {UserService} from '../../Services/users/user.service';

@Component({
  selector: 'app-all-journals',
  templateUrl: './all-journals.component.html',
  styleUrls: ['./all-journals.component.css']
})
export class AllJournalsComponent implements OnInit {

  journals: any;
  journal: any;
  purchases: any;


  constructor(private journalService: JournalService, private router: Router, private userService: UserService) {

    const user = JSON.parse(localStorage.getItem('loggedUser'));


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
