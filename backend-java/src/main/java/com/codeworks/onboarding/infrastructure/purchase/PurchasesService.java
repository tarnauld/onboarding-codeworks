package com.codeworks.onboarding.infrastructure.purchase;

import com.codeworks.onboarding.domain.purchase.Purchase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PurchasesService {
    List<PurchaseEntity> getPurchases();
    PurchaseEntity findPurchaseBy(long id);
    PurchaseEntity create(PurchaseEntity purchaseItems);
    PurchaseEntity deletePurchase(long id);
    List<Purchase> uploadCSV(MultipartFile any);
}
