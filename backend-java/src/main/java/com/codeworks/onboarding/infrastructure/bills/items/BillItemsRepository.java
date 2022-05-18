package com.codeworks.onboarding.infrastructure.bills.items;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillItemsRepository extends CrudRepository<BillItemsEntity, Long> {
    BillItemsEntity findById(long id);
    List<BillItemsEntity> findAll();
    BillItemsEntity deleteById(long id);
}
