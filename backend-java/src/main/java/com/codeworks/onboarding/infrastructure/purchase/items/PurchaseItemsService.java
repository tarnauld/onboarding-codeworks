package com.codeworks.onboarding.infrastructure.purchase.items;

import com.codeworks.onboarding.domain.purchase.Purchase;

import java.util.List;

public interface PurchaseItemsService {
    List<PurchaseItemsEntity> getPurchasesItems();
    PurchaseItemsEntity findPurchaseItemBy(long id);
    PurchaseItemsEntity create(PurchaseItemsEntity purchaseItems);
    PurchaseItemsEntity deletePurchaseItems(long id);

    List<PurchaseItemsEntity> process(List<Purchase> purchases, Long purchaseId);
}
