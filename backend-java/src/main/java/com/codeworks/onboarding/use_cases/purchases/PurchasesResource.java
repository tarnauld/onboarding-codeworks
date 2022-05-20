package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PurchasesResource {
    private final PurchasesService purchasesService;

    @Autowired
    public PurchasesResource(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @GetMapping("/purchases")
    public List<PurchaseEntity> getPurchases() {
        return purchasesService.getPurchases();
    }

    @GetMapping("/purchases/{id}")
    public PurchaseEntity findPurchaseBy(@PathVariable long id) {
        return purchasesService.findPurchaseBy(id);
    }

    @PostMapping("/purchases")
    public PurchaseEntity create(@RequestBody PurchaseEntity bill) {
        return purchasesService.create(bill);
    }

    @DeleteMapping("/purchases/{id}")
    public PurchaseEntity deletePurchase(@PathVariable long id) {
        return purchasesService.deletePurchase(id);
    }

    @PostMapping("/purchases/upload")
    @CrossOrigin(origins = "*")
    public List<Purchase> uploadCSV(@RequestParam("file") MultipartFile multipartFile) {
        return purchasesService.uploadCSV(multipartFile);
    }
}
