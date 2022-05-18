package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.infrastructure.bills.BillsEntity;
import com.codeworks.onboarding.infrastructure.bills.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillsResource {
    private final BillsService billsService;

    @Autowired
    public BillsResource(BillsService billsService) {
        this.billsService = billsService;
    }

    @GetMapping("/bills")
    public List<BillsEntity> getBills() {
        return billsService.getBills();
    }

    @GetMapping("/bills/{id}")
    public BillsEntity findBillBy(@PathVariable long id) {
        return billsService.findBillBy(id);
    }

    @PostMapping("/bills")
    public BillsEntity createBill(@RequestBody BillsEntity bill) {
        return billsService.create(bill);
    }

    @DeleteMapping("/bills/{id}")
    public BillsEntity deleteBill(@PathVariable long id) {
        return billsService.deleteBill(id);
    }
}
