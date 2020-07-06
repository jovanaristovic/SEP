package com.ftn.backend.elasticsearch.dto;

import lombok.Data;

@Data
public class NewWorkUddDto {

    public String journalTitle;
    public String title;
    public String apstrakt;
    public String keyTerms;
    public String scientificField;
    public String authors;
    public String file;
    public String fileName;

}
