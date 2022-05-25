package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.PurchaseRecap;
import com.codeworks.onboarding.domain.exceptions.UploadServiceException;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import com.codeworks.onboarding.infrastructure.purchase.orchestrator.PurchaseOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PurchasesResource {
    private final PurchasesService purchasesService;

    private final PurchaseOrchestrator purchaseOrchestrator;

    @Autowired
    public PurchasesResource(PurchasesService purchasesService,
                             PurchaseOrchestrator purchaseOrchestrator) {
        this.purchasesService = purchasesService;
        this.purchaseOrchestrator = purchaseOrchestrator;
    }

    @GetMapping("/purchases")
    @CrossOrigin(origins = "*")
    public List<PurchaseEntity> getPurchases() {
        return purchasesService.getPurchases();
    }

    @DeleteMapping("/purchases/{id}")
    @CrossOrigin(origins = "*")
    public PurchaseEntity deletePurchase(@PathVariable long id) {
        return purchasesService.deletePurchase(id);
    }

    @PostMapping("/purchases/upload")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ComputedBills>> uploadCSV(@RequestParam("file") MultipartFile multipartFile,
                                                         @RequestParam("shipping") Float shipping) {
        try {
            return ResponseEntity.ok(purchaseOrchestrator.process(multipartFile, shipping));
        } catch (UploadServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/purchases/compute")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ComputedBills>> compute(@RequestBody PurchaseRecap recap) {
        return ResponseEntity.ok(purchaseOrchestrator.process(recap));
    }
}
