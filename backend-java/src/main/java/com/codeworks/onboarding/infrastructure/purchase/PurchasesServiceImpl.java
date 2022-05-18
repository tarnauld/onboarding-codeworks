package com.codeworks.onboarding.infrastructure.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasesServiceImpl implements PurchasesService {
    private final PurchasesRepository purchasesRepository;

    @Autowired
    public PurchasesServiceImpl(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    @Override
    public List<PurchaseEntity> getPurchases() {
        return purchasesRepository.findAll();
    }

    @Override
    public PurchaseEntity findPurchaseBy(long id) {
        return purchasesRepository.findById(id);
    }

    @Override
    public PurchaseEntity create(PurchaseEntity purchase) {
        return purchasesRepository.save(purchase);
    }

    @Override
    public PurchaseEntity deletePurchase(long id) {
        return purchasesRepository.deleteById(id);
    }
}
