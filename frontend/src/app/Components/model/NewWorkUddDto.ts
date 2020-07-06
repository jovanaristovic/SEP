export class NewWorkUddDto {
  constructor(
    public journalTitle,
    public title: string,
    public apstrakt,
    public keyTerms,
    public scientificField,
    public authors,
    public file: string,
    public fileName: string) {
  }
}
