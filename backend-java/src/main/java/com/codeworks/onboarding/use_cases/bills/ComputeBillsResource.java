package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.PurchaseRecap;
import com.codeworks.onboarding.infrastructure.bills.ComputeBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComputeBillsResource {
    private final ComputeBillsService computeBillsService;

    @Autowired
    public ComputeBillsResource(ComputeBillsService computeBillsService) {
        this.computeBillsService = computeBillsService;
    }

    @PostMapping("/compute-bills")
    public List<ComputedBills> execute(@RequestBody PurchaseRecap purchaseRecap) {
        return computeBillsService.execute(purchaseRecap);
    }
}
