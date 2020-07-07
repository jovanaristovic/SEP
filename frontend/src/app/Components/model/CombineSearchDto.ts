export class CombineSearchDto {
  constructor(
    public title: string,
    public journalTitle,
    public authors,
    public keyTerms,
    public text: string,
    public scientificField,
    public titleSearch,
    public  journalTitleSearch,
    public authorsSearch,
    public keyTermsSearch,
    public textSearch,
    public scientificFieldSearch
  ) {

  }
}
