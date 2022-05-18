package com.codeworks.onboarding.infrastructure.purchase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchasesRepository extends CrudRepository<PurchaseEntity, Long> {
    PurchaseEntity findById(long id);
    List<PurchaseEntity> findAll();
    PurchaseEntity deleteById(long id);
}
