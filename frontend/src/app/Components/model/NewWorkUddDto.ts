export class NewWorkUddDto {
  constructor(
    public title: string,
    public journalTitle,
    public apstrakt,
    public keyTerms,
    public scientificField,
    public authors,
    public file: string,
    public fileName: string) {
  }
}
