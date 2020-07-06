package com.ftn.backend.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ftn.backend.elasticsearch.dto.SearchDto;
import com.ftn.backend.elasticsearch.dto.SearchingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {


    @Autowired
    private WorkElasticsearchService workElasticsearchService;

    @PostMapping
    public ResponseEntity<List<SearchingResultDto>> search(@RequestBody SearchDto searchDto) throws JsonProcessingException {

        if (searchDto.getParamName().equals("title")) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByTitle(searchDto.getParam()), HttpStatus.OK);
        } else if ((searchDto.getParamName().equals("journalTitle"))) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByJournalTitle(searchDto.getParam()), HttpStatus.OK);
        } else if ((searchDto.getParamName().equals("scientificField"))) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByScientificField(searchDto.getParam()), HttpStatus.OK);
        } else if ((searchDto.getParamName().equals("authors"))) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByAuthors(searchDto.getParam()), HttpStatus.OK);
        } else if ((searchDto.getParamName().equals("keyTerms"))) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByKeyTerms(searchDto.getParam()), HttpStatus.OK);
        } else if ((searchDto.getParamName().equals("text"))) {
            return new ResponseEntity<>(this.workElasticsearchService.searchByText(searchDto.getParam()), HttpStatus.OK);
        } else {
            return null;
        }


    }

}
