import { Component, OnInit } from '@angular/core';
import {JournalService} from '../../Services/journal/journal.service';
import {Router} from '@angular/router';
import {WorkService} from '../../Services/work/work.service';
import {saveAs} from 'file-saver';

const MIME_TYPE  = {
  pdf:  'application/pdf'
}
@Component({
  selector: 'app-journal-profile',
  templateUrl: './journal-profile.component.html',
  styleUrls: ['./journal-profile.component.css']
})
export class JournalProfileComponent implements OnInit {
  journalWorks: any;
  id: any;
  href: any;
  journal:any;

  constructor(private journalService: JournalService, private router: Router, private workService: WorkService) { }

  ngOnInit() {
    this.href = this.router.url;

    this.id = this.href.split('/')[2];

    this.journalService.getById(this.id).subscribe(journal => {
      console.log(journal);
      this.journal = journal;
      this.journalWorks = journal.works;
    });
  }


  buyWork(id) {
    this.journalService.buyWork(id);
  }

  download(fileName) {
    const EXT = fileName.substr(fileName.lastIndexOf('.') + 1);
    this.workService.downladWork(fileName)
      .subscribe(data => {
        saveAs(new Blob([data], {type: MIME_TYPE[EXT]}), fileName);
      });
  }


}
