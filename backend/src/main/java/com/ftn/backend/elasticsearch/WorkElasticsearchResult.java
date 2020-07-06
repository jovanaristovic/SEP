package com.ftn.backend.elasticsearch;


import lombok.Data;

@Data
public class WorkElasticsearchResult {

    private Long id;

    private Long workId;

    private String title;

    private String journalTitle;

    private String apstrakt;

    private String keyTerms;

    private String scientificField;

    private String pdfFileName;

    private String text;

    private String authors;

    private String highlight;
}
