package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.TrapCase;
import com.example.scoobydoo.repos.TrapCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrapCaseService {
    @Autowired
    private TrapCaseRepo trapCaseRepo;

    public List<TrapCase> getAllTraps(int x, int y) {
        return trapCaseRepo.findAll(PageRequest.of(x, y)).getContent();
    }

    public List<TrapCase> findAll() {
        return trapCaseRepo.findAll();
    }
}
