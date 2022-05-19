package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsEntity;
import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseItemsResource {
    private final PurchaseItemsService purchaseItemsService;

    @Autowired
    public PurchaseItemsResource(PurchaseItemsService purchaseItemsService) {
        this.purchaseItemsService = purchaseItemsService;
    }

    @GetMapping("/purchase/items")
    public List<PurchaseItemsEntity> getPurchaseItems() {
        return purchaseItemsService.getPurchasesItems();
    }

    @GetMapping("/purchase/items/{id}")
    public PurchaseItemsEntity findPurchaseItemBy(@PathVariable long id) {
        return purchaseItemsService.findPurchaseItemBy(id);
    }

    @PostMapping("/purchase/items")
    public PurchaseItemsEntity create(@RequestBody PurchaseItemsEntity bill) {
        return purchaseItemsService.create(bill);
    }

    @DeleteMapping("/purchase/items/{id}")
    public PurchaseItemsEntity deletePurchaseItem(@PathVariable long id) {
        return purchaseItemsService.deletePurchaseItems(id);
    }
}
