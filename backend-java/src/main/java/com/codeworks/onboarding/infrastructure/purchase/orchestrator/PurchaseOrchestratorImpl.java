package com.codeworks.onboarding.infrastructure.purchase.orchestrator;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.bills.ComputeBillsService;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import com.codeworks.onboarding.infrastructure.purchase.recap.Recap;
import com.codeworks.onboarding.infrastructure.purchase.recap.RecapToPurchaseRecap;
import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrchestratorImpl implements PurchaseOrchestrator {
    private final PurchasesService purchasesService;
    private final PurchaseItemsService purchaseItemsService;
    private final ComputeBillsService computeBillsService;
    private final RecapToPurchaseRecap mapper;

    @Autowired
    public PurchaseOrchestratorImpl(PurchasesService purchasesService,
                                    PurchaseItemsService purchaseItemsService,
                                    ComputeBillsService computeBillsService,
                                    RecapToPurchaseRecap mapper) {
        this.purchasesService = purchasesService;
        this.purchaseItemsService = purchaseItemsService;
        this.computeBillsService = computeBillsService;
        this.mapper = mapper;
    }

    @Override
    public List<ComputedBills> process(MultipartFile multipartFile, Float shipping) {
        List<Purchase> purchases = purchasesService.uploadCSV(multipartFile);
        PurchaseEntity purchaseEntity = createPurchaseEntity(shipping);

        purchaseItemsService.process(purchases, purchaseEntity.getId());
        Recap recap = buildRecap(shipping, purchases, purchaseEntity);

        return computeBillsService.execute(mapper.execute(recap));
    }

    private Recap buildRecap(float shipping, List<Purchase> purchases, PurchaseEntity purchaseEntity) {
        return Recap.builder()
                .shipping(shipping)
                .purchase(purchaseEntity)
                .items(purchases)
                .build();
    }

    private PurchaseEntity createPurchaseEntity(float shipping) {
        return purchasesService.create(PurchaseEntity.builder()
                .userId(7L)
                .shippingFee(shipping)
                .creationDate(LocalDate.now())
                .build());
    }
}
