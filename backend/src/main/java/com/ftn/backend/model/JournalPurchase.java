package com.ftn.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class JournalPurchase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Journal journal;

    @Column
    private String status;

    public JournalPurchase() {}

    public JournalPurchase(Journal journal, String status ) {
        this.journal = journal;
        this.status = status;
    }
}
