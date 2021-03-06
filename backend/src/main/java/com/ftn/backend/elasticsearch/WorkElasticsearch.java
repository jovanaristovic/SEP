package com.ftn.backend.elasticsearch;

import com.ftn.backend.model.WorkUDD;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "journal", type = "work")
@Data
public class WorkElasticsearch {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long workId;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String title;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String journalTitle;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String apstrakt;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String keyTerms;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String scientificField;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String pdfFileName;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian",analyzer = "serbian")
    private String text;

    @Field(type = FieldType.Text, store = true)
    private String authors;


    public WorkElasticsearch() {
    }

    public WorkElasticsearch(WorkUDD workUDD) {
        this.id = workUDD.getId();
        this.workId = workUDD.getId();
        this.title = workUDD.getTitle();
        this.journalTitle = workUDD.getJournalTitle();
        this.apstrakt = workUDD.getApstrakt();
        this.keyTerms = workUDD.getKeyTerms();
        this.scientificField = workUDD.getScientificField();
        this.pdfFileName = workUDD.getFileName();
        this.authors = workUDD.getAuthors();
    }

}
