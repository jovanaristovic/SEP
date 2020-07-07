import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrManager} from 'ng6-toastr-notifications';
import {SearchService} from '../../Services/search/search.service';
import {CombineSearchDto} from '../model/CombineSearchDto';
import {SearchDto} from '../model/SearchDto';
import {WorkService} from '../../Services/work/work.service';
import {saveAs} from 'file-saver';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  chooseBool: any;
  type: any;
  isCombine: any;
  keyTerms: any;
  title: any;
  journalTitle: any;
  authors: any;
  scientificField: any;
  text: any;
  textSearch: any;
  titleSearch: any;
  journalTitleSearch: any;
  authorsSearch: any;
  keyTermsSearch: any;
  scientificFieldSearch: any;
  param: any;
  paramName: any;
  works: any;
  searchBool: any;

  constructor(private router: Router, private toastr: ToastrManager, private searchService: SearchService, private workService: WorkService) {
    this.chooseBool = false;
    this.isCombine = false;
    this.searchBool = false;
  }

  ngOnInit() {
  }

  search() {
    this.searchBool = true;

    if (this.isCombine) {

      const combine = new CombineSearchDto(this.title, this.journalTitle, this.authors, this.keyTerms,
        this.text, this.scientificField, this.titleSearch, this.journalTitleSearch, this.authorsSearch, this.keyTermsSearch, this.textSearch, this.scientificFieldSearch);

      this.searchService.combineSearch(combine).subscribe(works => {
        this.works = works;
        console.log(works);

      });


    } else {
      const search = new SearchDto(this.paramName, this.param);
      console.log(search);
      this.searchService.search(search).subscribe(works => {
        this.works = works;
        console.log(works);

      });
    }

  }


  selected(paramName) {
    if (paramName === 'Combine') {
      this.isCombine = true;
    } else if (paramName === 'Key terms') {
      this.paramName = 'keyTerms';
    } else if (paramName === 'Title') {
      this.paramName = 'title';
    } else if (paramName === 'Journal title') {
      this.paramName = 'journalTitle';
    } else if (paramName === 'Authors') {
      this.paramName = 'authors';
    } else if (paramName === 'Text') {
      this.paramName = 'text';
    } else if (paramName === 'Scientific field') {
      this.paramName = 'scientificField';
    } else {
      this.paramName = '';
    }
  }


  download(fileName) {
    const EXT = fileName.substring(fileName.lastIndexOf('.') + 1);
    this.workService.downladWork(fileName)
      .subscribe(data => {
        saveAs(new Blob([data], {type: MimeType[EXT]}), fileName);
      });
  }

}
