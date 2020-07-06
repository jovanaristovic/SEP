package com.ftn.backend.elasticsearch.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.backend.elasticsearch.WorkElasticsearchRepository;
import com.ftn.backend.elasticsearch.WorkElasticsearchService;
import com.ftn.backend.elasticsearch.dto.SearchingResultDto;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;


@Service
public class WorkElasticsearchServiceImpl implements WorkElasticsearchService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @Override
    public List<SearchingResultDto> searchByTitle(String title) throws JsonProcessingException {

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("title");
        field.highlighterType("plain");
        highlightBuilder.field(field);


        if (title.contains(" ")) {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("title", title).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);
            return getDtoList(hits);
        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("title", title))
                    .withHighlightBuilder(highlightBuilder)
                    .build();

            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        }


    }

    @Override
    public List<SearchingResultDto> searchByJournalTitle(String journalTitle) {

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("journalTitle");
        field.highlighterType("plain");
        highlightBuilder.field(field);

        if (journalTitle.contains(" ")) {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("journalTitle", journalTitle).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("journalTitle", journalTitle))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);
            return getDtoList(hits);
        }
    }

    @Override
    public List<SearchingResultDto> searchByScientificField(String scientificField) {

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("scientificField");
        field.highlighterType("plain");
        highlightBuilder.field(field);

        if (scientificField.contains(" ")) {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("scientificField", scientificField).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("scientificField", scientificField))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);
            return getDtoList(hits);
        }
    }

    @Override
    public List<SearchingResultDto> searchByAuthors(String authors) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("authors");
        field.highlighterType("plain");
        highlightBuilder.field(field);

        if (authors.contains(" ")) {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("authors", authors).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("authors", authors))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        }
    }

    @Override
    public List<SearchingResultDto> searchByKeyTerms(String keyTerms) {


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("keyTerms");
        field.highlighterType("plain");
        highlightBuilder.field(field);


        if (keyTerms.contains(" ")) {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("keyTerms", keyTerms).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();

            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("keyTerms", keyTerms))
                    .withHighlightBuilder(highlightBuilder)
                    .build();
            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);

        }
    }

    @Override
    public List<SearchingResultDto> searchByText(String text) {

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("text");
        field.highlighterType("plain");
        highlightBuilder.field(field);


        if (text.contains(" ")) {

            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchPhraseQuery("text", text).slop(3))
                    .withHighlightBuilder(highlightBuilder)
                    .build();


            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);
            return getDtoList(hits);


        } else {
            NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("text", text))
                    .withHighlightBuilder(highlightBuilder)

                    .build();

            SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

            return getDtoList(hits);
        }
    }


    private List<SearchingResultDto> getDtoList(SearchHits searchHits) {

        List<SearchingResultDto> list = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> map = hit.getSourceAsMap();
            String keyTerms = map.get("keyTerms").toString();
            String apstrakt = map.get("apstrakt").toString();
            String scientificField = map.get("scientificField").toString();
            String id = map.get("id").toString();
            String text = map.get("text").toString();
            String pdfFileName = map.get("pdfFileName").toString();
            String title = map.get("title").toString();
            String journalTitle = map.get("journalTitle").toString();
            String authors = map.get("authors").toString();
            String highligt = hit.getHighlightFields().toString();

            SearchingResultDto searchingResultDto = new SearchingResultDto(Long.parseLong(id), title, journalTitle, apstrakt, keyTerms, scientificField, authors, highligt);
            list.add(searchingResultDto);
        }

        return list;

    }
}
