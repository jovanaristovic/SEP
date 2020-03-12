import { Component, OnInit } from '@angular/core';
import {ScientificFieldService} from '../../Services/scientificField/scientific-field.service';
import {JournalService} from '../../Services/journal/journal.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-journal',
  templateUrl: './new-journal.component.html',
  styleUrls: ['./new-journal.component.css']
})
export class NewJournalComponent implements OnInit {
  journal: any;
  enumValues: any;

  constructor(private scientificFieldService: ScientificFieldService, private journalService: JournalService, public toastr: ToastrManager, private router: Router) {
    this.journal = {title: '', issn: '', openAccess: '', price: '', scientificField: ''};
  }

  ngOnInit() {

    this.scientificFieldService.getAll().subscribe(scientificFields => {
      this.enumValues = scientificFields;
    });
  }

  create() {
    this.journalService.newJournal(this.journal).subscribe(newJournal => {
      this.toastr.successToastr('Journal created', 'Success');

      this.router.navigate(['/home'], {});
    });
  }

}
