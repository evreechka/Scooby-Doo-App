package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.CaseOrder;
import com.example.scoobydoo.repos.CaseOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseOrderService {
    @Autowired
    private CaseOrderRepo caseOrderRepo;
    public CaseOrder getCaseOrderByID(long orderId) {
        return caseOrderRepo.findCaseOrderById(orderId);
    }
}
