package com.ftn.backend.elasticsearch.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ftn.backend.elasticsearch.WorkElasticsearchService;
import com.ftn.backend.elasticsearch.dto.CombinedSearchDto;
import com.ftn.backend.elasticsearch.dto.SearchingResultDto;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public List<SearchingResultDto> combinedSearch(CombinedSearchDto combinedSearchDto) {

        HighlightBuilder highlightBuilder = new HighlightBuilder();

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("title");
        field1.highlighterType("plain");

        HighlightBuilder.Field field2 = new HighlightBuilder.Field("journalTitle");
        field2.highlighterType("plain");

        HighlightBuilder.Field field3 = new HighlightBuilder.Field("authors");
        field3.highlighterType("plain");

        HighlightBuilder.Field field4 = new HighlightBuilder.Field("keyTerms");
        field4.highlighterType("plain");

        HighlightBuilder.Field field5 = new HighlightBuilder.Field("text");
        field5.highlighterType("plain");
        HighlightBuilder.Field field6 = new HighlightBuilder.Field("scientificField");
        field6.highlighterType("plain");


        highlightBuilder.field(field1);
        highlightBuilder.field(field2);
        highlightBuilder.field(field3);
        highlightBuilder.field(field4);
        highlightBuilder.field(field5);
        highlightBuilder.field(field6);


        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if (combinedSearchDto.getTitle() != null) {
            if (combinedSearchDto.isTitleSearch() && !combinedSearchDto.getTitle().equals("")) {
                if (combinedSearchDto.getTitle().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("title", combinedSearchDto.getTitle()));
                } else {
                    boolQueryBuilder.must(matchQuery("title", combinedSearchDto.getTitle()));

                }
            } else if (!combinedSearchDto.isTitleSearch() && !combinedSearchDto.getTitle().equals("")) {
                if (combinedSearchDto.getTitle().contains(" ")) {

                    boolQueryBuilder.should(matchPhraseQuery("title", combinedSearchDto.getTitle()));
                } else {
                    boolQueryBuilder.should(matchQuery("title", combinedSearchDto.getTitle()));

                }
            }
        }

        if (combinedSearchDto.getJournalTitle() != null) {
            if (combinedSearchDto.isJournalTitleSearch() && !combinedSearchDto.getJournalTitle().equals("")) {
                if (combinedSearchDto.getJournalTitle().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("journalTitle", combinedSearchDto.getJournalTitle()));

                } else {
                    boolQueryBuilder.must(matchQuery("journalTitle", combinedSearchDto.getJournalTitle()));
                }

            } else if (!combinedSearchDto.isJournalTitleSearch() && !combinedSearchDto.getJournalTitle().equals("")) {
                if (combinedSearchDto.getJournalTitle().contains(" ")) {

                    boolQueryBuilder.should(matchPhraseQuery("journalTitle", combinedSearchDto.getJournalTitle()));
                } else {
                    boolQueryBuilder.should(matchQuery("journalTitle", combinedSearchDto.getJournalTitle()));

                }

            }
        }
        if (combinedSearchDto.getAuthors() != null) {
            if (combinedSearchDto.isAuthorsSearch() && !combinedSearchDto.getAuthors().equals("")) {
                if (combinedSearchDto.getAuthors().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("authors", combinedSearchDto.getAuthors()));

                } else {
                    boolQueryBuilder.must(matchQuery("authors", combinedSearchDto.getAuthors()));

                }

            } else if (!combinedSearchDto.isAuthorsSearch() && !combinedSearchDto.getAuthors().equals("")) {
                if (combinedSearchDto.getAuthors().contains(" ")) {
                    boolQueryBuilder.should(matchPhraseQuery("authors", combinedSearchDto.getAuthors()));

                } else {
                    boolQueryBuilder.should(matchQuery("authors", combinedSearchDto.getAuthors()));

                }

            }
        }
        if (combinedSearchDto.getKeyTerms() != null) {
            if (combinedSearchDto.isKeyTermsSearch() && !combinedSearchDto.getKeyTerms().equals("")) {
                if (combinedSearchDto.getAuthors().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("keyTerms", combinedSearchDto.getKeyTerms()));

                } else {
                    boolQueryBuilder.must(matchQuery("keyTerms", combinedSearchDto.getKeyTerms()));

                }

            } else if (!combinedSearchDto.isKeyTermsSearch() && !combinedSearchDto.getKeyTerms().equals("")) {
                if (combinedSearchDto.getAuthors().contains(" ")) {
                    boolQueryBuilder.should(matchPhraseQuery("keyTerms", combinedSearchDto.getKeyTerms()));

                } else {
                    boolQueryBuilder.should(matchQuery("keyTerms", combinedSearchDto.getKeyTerms()));

                }

            }
        }
        if (combinedSearchDto.getText() != null) {
            if (combinedSearchDto.isTextSearch() && !combinedSearchDto.getText().equals("")) {
                if (combinedSearchDto.getText().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("text", combinedSearchDto.getText()));

                } else {
                    boolQueryBuilder.must(matchQuery("text", combinedSearchDto.getText()));

                }

            } else if (!combinedSearchDto.isTextSearch() && !combinedSearchDto.getText().equals("")) {
                if (combinedSearchDto.getText().contains(" ")) {
                    boolQueryBuilder.should(matchPhraseQuery("text", combinedSearchDto.getText()));

                } else {
                    boolQueryBuilder.should(matchQuery("text", combinedSearchDto.getText()));

                }
            }

        }
        if (combinedSearchDto.getScientificField() != null) {
            if (combinedSearchDto.isScientificFieldSearch() && !combinedSearchDto.getScientificField().equals("")) {
                if (combinedSearchDto.getScientificField().contains(" ")) {
                    boolQueryBuilder.must(matchPhraseQuery("scientificField", combinedSearchDto.getScientificField()));

                } else {
                    boolQueryBuilder.must(matchQuery("scientificField", combinedSearchDto.getScientificField()));

                }


            } else if (!combinedSearchDto.isScientificFieldSearch() && !combinedSearchDto.getScientificField().equals("")) {
                if (combinedSearchDto.getScientificField().contains(" ")) {
                    boolQueryBuilder.should(matchPhraseQuery("scientificField", combinedSearchDto.getScientificField()));

                } else {
                    boolQueryBuilder.should(matchQuery("scientificField", combinedSearchDto.getScientificField()));

                }


            }
        }


        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .build();


        SearchHits hits = elasticsearchTemplate.query(searchQuery, SearchResponse::getHits);

        return getDtoList(hits);
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

            String highligt = "";
            Map<String, HighlightField> highlightMap = hit.getHighlightFields();
            List<HighlightField> highlightFieldList = new ArrayList<>();


            if (highlightMap.get("keyTerms") != null) {
                HighlightField highlightField = highlightMap.get("keyTerms");
                highlightFieldList.add(highlightField);
            }
            if (highlightMap.get("title") != null) {
                HighlightField highlightField = highlightMap.get("title");
                highlightFieldList.add(highlightField);
            }
            if (highlightMap.get("journalTitle") != null) {
                HighlightField highlightField = highlightMap.get("journalTitle");
                highlightFieldList.add(highlightField);
            }
            if (highlightMap.get("authors") != null) {
                HighlightField highlightField = highlightMap.get("authors");
                highlightFieldList.add(highlightField);
            }
            if (highlightMap.get("scientificField") != null) {
                HighlightField highlightField = highlightMap.get("scientificField");
                highlightFieldList.add(highlightField);
            }
            if (highlightMap.get("text") != null) {
                HighlightField highlightField = highlightMap.get("text");
                highlightFieldList.add(highlightField);
            }

            int brojac = 0;

            for (HighlightField highlightField : highlightFieldList) {

                Text[] text1 = highlightField.getFragments();
                String highligt1 = highlightField.getName() + ": " + text1[0].toString();

                String regex = "<[^>]+>";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(highligt1);

                if (matcher.find()) {
                    if (brojac == 0) {
                        highligt = highligt + " " + highligt1.replaceAll(regex, "");
                    } else {
                        highligt = highligt + ", " + highligt1.replaceAll(regex, "");

                    }
                }

                brojac++;
            }

            highligt = highligt + "...";
            SearchingResultDto searchingResultDto = new SearchingResultDto(Long.parseLong(id), title, journalTitle, apstrakt, keyTerms, scientificField, authors, highligt, pdfFileName);
            list.add(searchingResultDto);
        }

        return list;

    }
}
