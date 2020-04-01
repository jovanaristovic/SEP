package com.ftn.backend.service;

import com.ftn.backend.dto.NewWorkDto;
import com.ftn.backend.model.Work;
import org.hibernate.jdbc.WorkExecutor;

import java.io.IOException;

public interface WorkService {

    Work newWork(NewWorkDto newWorkDto) throws IOException;
    Work findById(Long id);
}
