package com.ftn.backend.service;

import com.ftn.backend.elasticsearch.dto.NewWorkUddDto;
import com.ftn.backend.elasticsearch.WorkElasticsearch;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface WorkUddService {

    WorkElasticsearch newWorkUdd(NewWorkUddDto newWorkUddDto) throws IOException;
}
