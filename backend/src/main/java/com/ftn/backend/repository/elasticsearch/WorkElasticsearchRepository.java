package com.ftn.backend.repository.elasticsearch;

import com.ftn.backend.model.WorkElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WorkElasticsearchRepository extends ElasticsearchRepository<WorkElasticsearch, Long> {

}
