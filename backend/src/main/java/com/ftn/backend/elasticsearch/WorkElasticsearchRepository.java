package com.ftn.backend.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface WorkElasticsearchRepository extends ElasticsearchRepository<WorkElasticsearch, Long> {

    Page<WorkElasticsearch> findWorkElasticsearchByTitleLike(String title, Pageable pageable);
    Page<WorkElasticsearch> findWorkElasticsearchByJournalTitleLike(String journalTitle, Pageable pageable);
    Page<WorkElasticsearch> findWorkElasticsearchByScientificFieldLike(String scientificField, Pageable pageable);
    Page<WorkElasticsearch> findWorkElasticsearchByAuthorsLike(String author, Pageable pageable);
    Page<WorkElasticsearch> findWorkElasticsearchByKeyTermsLike(String keyTerms, Pageable pageable);
    Page<WorkElasticsearch> findWorkElasticsearchByTextLike(String text, Pageable pageable);







}
