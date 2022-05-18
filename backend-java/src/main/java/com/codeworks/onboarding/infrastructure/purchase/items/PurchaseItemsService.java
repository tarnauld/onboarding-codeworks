package com.codeworks.onboarding.infrastructure.purchase.items;

import java.util.List;

public interface PurchaseItemsService {
    List<PurchaseItemsEntity> getPurchasesItems();
    PurchaseItemsEntity findPurchaseItemBy(long id);
    PurchaseItemsEntity create(PurchaseItemsEntity purchaseItems);
    PurchaseItemsEntity deletePurchaseItems(long id);
}
