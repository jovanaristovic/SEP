export class NewWorkDto {
  constructor(
    public journalId,
    public title: string,
    public apstrakt,
    public scientificField,
    public price,
    public file: string,
    public fileName: string) {
  }
}
