package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.infrastructure.bills.items.BillItemsEntity;
import com.codeworks.onboarding.infrastructure.bills.items.BillItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillItemsResource {
    private final BillItemsService billsService;

    @Autowired
    public BillItemsResource(BillItemsService billsService) {
        this.billsService = billsService;
    }

    @GetMapping("/bill/items")
    public List<BillItemsEntity> getBillItems() {
        return billsService.getBillItems();
    }

    @GetMapping("/bill/items/{id}")
    public BillItemsEntity findBillItemBy(@PathVariable long id) {
        return billsService.findBillItemBy(id);
    }

    @PostMapping("/bill/items")
    public BillItemsEntity createBillItem(@RequestBody BillItemsEntity bill) {
        return billsService.create(bill);
    }

    @DeleteMapping("/bill/items/{id}")
    public BillItemsEntity deleteBillItem(@PathVariable long id) {
        return billsService.deleteBillItem(id);
    }
}
