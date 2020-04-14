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
    private boolean isPaid;
    private List<Work> works;

    public JournalDto () {

    }

    public JournalDto(Long id ,String title, String ISSN, boolean isOpenAccess, ScientificField scientificField, Long price, boolean isPaid) {
        this.id  = id;
        this.title = title;
        this.ISSN = ISSN;
        this.isOpenAccess = isOpenAccess;
        this.scientificField = scientificField;
        this.price = price;
        this.isPaid = isPaid;
    }

    public JournalDto(Long id, String title, String ISSN, boolean isOpenAccess, ScientificField scientificField, Long price, boolean isPaid, List<Work> works) {
        this.id = id;
        this.title = title;
        this.ISSN = ISSN;
        this.isOpenAccess = isOpenAccess;
        this.scientificField = scientificField;
        this.price = price;
        this.isPaid = isPaid;
        this.works = works;
    }
}

