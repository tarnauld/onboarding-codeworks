package com.codeworks.onboarding.infrastructure.purchase.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseItemsServiceImpl implements PurchaseItemsService {
    private final PurchaseItemsRepository purchaseItemsRepository;

    @Autowired
    public PurchaseItemsServiceImpl(PurchaseItemsRepository purchaseItemsRepository) {
        this.purchaseItemsRepository = purchaseItemsRepository;
    }

    @Override
    public List<PurchaseItemsEntity> getPurchasesItems() {
        return purchaseItemsRepository.findAll();
    }

    @Override
    public PurchaseItemsEntity findPurchaseItemBy(long id) {
        return purchaseItemsRepository.findById(id);
    }

    @Override
    public PurchaseItemsEntity create(PurchaseItemsEntity purchaseItems) {
        return purchaseItemsRepository.save(purchaseItems);
    }

    @Override
    public PurchaseItemsEntity deletePurchaseItems(long id) {
        return purchaseItemsRepository.deleteById(id);
    }
}
