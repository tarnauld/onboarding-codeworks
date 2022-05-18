package com.codeworks.onboarding.infrastructure.bills;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillsRepository extends CrudRepository<BillsEntity, Long> {
    BillsEntity findById(long id);
    List<BillsEntity> findAll();
    BillsEntity deleteById(long id);
}
