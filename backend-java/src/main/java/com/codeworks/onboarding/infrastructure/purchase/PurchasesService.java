package com.codeworks.onboarding.infrastructure.purchase;

import java.util.List;

public interface PurchasesService {
    List<PurchaseEntity> getPurchases();
    PurchaseEntity findPurchaseBy(long id);
    PurchaseEntity create(PurchaseEntity purchaseItems);
    PurchaseEntity deletePurchase(long id);
}
