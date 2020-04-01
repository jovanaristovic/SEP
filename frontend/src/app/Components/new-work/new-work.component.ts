import { Component, OnInit } from '@angular/core';
import {ScientificFieldService} from '../../Services/scientificField/scientific-field.service';
import {NewWorkDto} from '../model/NewWorkDto';
import {WorkService} from '../../Services/work/work.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-new-work',
  templateUrl: './new-work.component.html',
  styleUrls: ['./new-work.component.css']
})
export class NewWorkComponent implements OnInit {

  work: any;
  title: any;
  apstrakt: any;
  fileName: any;
  fileField: any;
  scientificField: any;
  enumValues: any;
  price: any;
  journalId: any;
  href: any;

  constructor(private scientificFieldService: ScientificFieldService, private workService: WorkService, private toastr: ToastrManager, private router: Router) {

  }

  ngOnInit() {
    this.scientificFieldService.getAll().subscribe(scientificFields => {
      this.enumValues = scientificFields;
    });
  }

  create() {
    this.href = this.router.url;

    this.journalId = this.href.split('/')[3];
    const y = new NewWorkDto(this.journalId, this.title, this.apstrakt, this.scientificField, this.price, this.fileField.toString(), this.fileName.toString());

    this.workService.newWork(y).subscribe(newJournal => {
      this.toastr.successToastr('Work created', 'Success');

      this.router.navigate(['/home'], {});
    });
  }

  fileChoserListener(files: FileList) {
    const fileToUpload = files.item(0);
    // field.fileName = files.item(0).name;
    this.fileName = files.item(0).name;

    const fileReader = new FileReader();

    fileReader.onload = (e) => {

      // field.value = fileReader.result;
      this.fileField = fileReader.result;
    };

    fileReader.readAsDataURL(files.item(0));
  }

}
