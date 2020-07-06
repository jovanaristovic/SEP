package com.ftn.backend.elasticsearch.dto;

import lombok.Data;

@Data
public class SearchingResultDto {

    private Long id;
    private String title;
    private String journalTitle;
    private String apstrakt;
    private String keyTerms;
    private String scientificField;
    private String authors;
    private String highlight;

    public SearchingResultDto(Long id, String title, String journalTitle,
                              String apstrakt, String keyTerms, String scientificField,
                              String authors, String highlight) {
        this.id = id;
        this.title = title;
        this.journalTitle = journalTitle;
        this.apstrakt = apstrakt;
        this.keyTerms = keyTerms;
        this.scientificField = scientificField;
        this.authors = authors;
        this.highlight = highlight;
    }
}
