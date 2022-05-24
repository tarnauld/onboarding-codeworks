package com.codeworks.onboarding.infrastructure.purchase.items;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseItemsServiceImpl implements PurchaseItemsService {
    private final PurchaseItemsRepository purchaseItemsRepository;

    private final UserService userService;

    @Autowired
    public PurchaseItemsServiceImpl(PurchaseItemsRepository purchaseItemsRepository,
                                    UserService userService) {
        this.purchaseItemsRepository = purchaseItemsRepository;
        this.userService = userService;
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

    @Override
    public List<PurchaseItemsEntity> process(List<Purchase> purchases, Long purchaseId) {
        List<PurchaseItemsEntity> items = new ArrayList<>();

        purchases.forEach(purchase -> {
            PurchaseItemsEntity purchaseItem = PurchaseItemsEntity.builder()
                    .purchaseId(purchaseId)
                    .buyerId(userService.findUserByName(purchase.getName()).getId())
                    .label(purchase.getLabel())
                    .quantity(purchase.getQuantity())
                    .unitPrice(purchase.getUnitPrice())
                    .build();
            items.add(this.create(purchaseItem));
        });
        return items;
    }
}
