import { Component, OnInit } from '@angular/core';
import {ScientificFieldService} from '../../Services/scientificField/scientific-field.service';
import {ToastrManager} from 'ng6-toastr-notifications';
import {Router} from '@angular/router';
import {NewWorkUddDto} from '../model/NewWorkUddDto';
import {WorkUddService} from '../../Services/work/work-udd.service';

@Component({
  selector: 'app-new-work-for-elasticsearch',
  templateUrl: './new-work-for-elasticsearch.component.html',
  styleUrls: ['./new-work-for-elasticsearch.component.css']
})
export class NewWorkForElasticsearchComponent implements OnInit {

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
  journalTitle: any;
  keyTerms: any;
  authors: any;

  constructor(private scientificFieldService: ScientificFieldService, private workService: WorkUddService, private toastr: ToastrManager, private router: Router) {

  }

  ngOnInit() {
    this.scientificFieldService.getAll().subscribe(scientificFields => {
      this.enumValues = scientificFields;
    });
  }


  create() {
    this.href = this.router.url;

    this.journalId = this.href.split('/')[3];
    const y = new NewWorkUddDto(this.journalTitle, this.title, this.apstrakt, this.keyTerms, this.scientificField, this.authors, this.fileField.toString(), this.fileName.toString());

    this.workService.newWorkUdd(y).subscribe(newWork => {
      console.log(newWork);
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
