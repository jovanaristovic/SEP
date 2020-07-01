package com.ftn.backend.service;

import com.ftn.backend.dto.NewWorkUddDto;
import com.ftn.backend.model.WorkElasticsearch;
import com.ftn.backend.model.WorkUDD;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface WorkUddService {

    WorkElasticsearch newWorkUdd(NewWorkUddDto newWorkUddDto) throws IOException;
}
