package com.ftn.backend.model;

import com.ftn.backend.elasticsearch.dto.NewWorkUddDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
public class WorkUDD implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String title;

    @Column
    private String journalTitle;

    @Column
    private String apstrakt;

    @Column
    private String keyTerms;

    @Column
    private String scientificField;

    @Column
    private String authors;

    @Column
    private String fileName;

    public WorkUDD() {
    }

    public WorkUDD(NewWorkUddDto newWorkUddDto) {

        this.title = newWorkUddDto.getTitle();
        this.journalTitle = newWorkUddDto.getJournalTitle();
        this.scientificField = newWorkUddDto.getScientificField();
        this.keyTerms = newWorkUddDto.getKeyTerms();
        this.authors = newWorkUddDto.getAuthors();
        this.apstrakt = newWorkUddDto.getApstrakt();
        this.fileName = newWorkUddDto.getFileName();
    }


}
