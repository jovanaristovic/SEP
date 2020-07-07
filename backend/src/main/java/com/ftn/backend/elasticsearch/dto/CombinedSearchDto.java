package com.ftn.backend.elasticsearch.dto;

import lombok.Data;

@Data
public class CombinedSearchDto {


    private String title;
    private String journalTitle;
    private String authors;
    private String keyTerms;
    private String text;
    private String scientificField;

    private boolean titleSearch;
    private boolean journalTitleSearch;
    private boolean authorsSearch;
    private boolean keyTermsSearch;
    private boolean textSearch;
    private boolean scientificFieldSearch;
}
