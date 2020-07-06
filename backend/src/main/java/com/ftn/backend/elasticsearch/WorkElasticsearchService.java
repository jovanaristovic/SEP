package com.ftn.backend.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ftn.backend.elasticsearch.dto.SearchingResultDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface WorkElasticsearchService {

    List<SearchingResultDto> searchByTitle(String title) throws JsonProcessingException;

    List<SearchingResultDto> searchByJournalTitle(String journalTitle);

    List<SearchingResultDto> searchByScientificField(String scientificField);

    List<SearchingResultDto> searchByAuthors(String authors);

    List<SearchingResultDto> searchByKeyTerms(String keyTerms);

    List<SearchingResultDto> searchByText(String text);


}
