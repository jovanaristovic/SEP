package com.ftn.backend.dto;

import com.ftn.backend.model.ScientificField;
import com.ftn.backend.model.Work;
import lombok.Data;

import java.util.List;

@Data
public class JournalDto {

    private Long id;
    private String title;
    private String ISSN;
    private boolean isOpenAccess;
    private ScientificField scientificField;
    private Long price;
    private String paid;
    private List<Work> works;


    public JournalDto () {

    }

    public JournalDto(Long id ,String title, String ISSN, boolean isOpenAccess, ScientificField scientificField, Long price, String paid) {
        this.id  = id;
        this.title = title;
        this.ISSN = ISSN;
        this.isOpenAccess = isOpenAccess;
        this.scientificField = scientificField;
        this.price = price;
        this.paid= paid;
    }

    public JournalDto(Long id, String title, String ISSN, boolean isOpenAccess, ScientificField scientificField, Long price, String paid, List<Work> works) {
        this.id = id;
        this.title = title;
        this.ISSN = ISSN;
        this.isOpenAccess = isOpenAccess;
        this.scientificField = scientificField;
        this.price = price;
        this.paid = paid;
        this.works = works;
    }
}

