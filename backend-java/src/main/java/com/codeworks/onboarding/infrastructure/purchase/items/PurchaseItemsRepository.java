package com.codeworks.onboarding.infrastructure.purchase.items;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseItemsRepository extends CrudRepository<PurchaseItemsEntity, Long> {
    PurchaseItemsEntity findById(long id);
    List<PurchaseItemsEntity> findAll();
    PurchaseItemsEntity deleteById(long id);
}

