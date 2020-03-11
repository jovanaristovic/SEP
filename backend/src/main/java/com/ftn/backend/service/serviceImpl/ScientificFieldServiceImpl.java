package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.model.ScientificField;
import com.ftn.backend.repository.ScienceFieldRepository;
import com.ftn.backend.service.ScientificFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScientificFieldServiceImpl implements ScientificFieldService {

    @Autowired
    private ScienceFieldRepository scienceFieldRepository;

    @Override
    public List<ScientificField> findAll() {
        return this.scienceFieldRepository.findAll();
    }

    @Override
    public ScientificField findByName(String name) {
        return this.scienceFieldRepository.findScientificFieldByName(name);
    }
}
