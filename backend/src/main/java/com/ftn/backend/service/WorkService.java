package com.ftn.backend.service;

import com.ftn.backend.dto.NewWorkDto;
import com.ftn.backend.model.Work;

import java.io.IOException;

public interface WorkService {

    Work newWork(NewWorkDto newWorkDto) throws IOException;
}
