package com.codeworks.onboarding.infrastructure.bills;

import java.util.List;

public interface BillsService {
    List<BillsEntity> getBills();
    BillsEntity findBillBy(long id);
    BillsEntity create(BillsEntity bills);
    BillsEntity deleteBill(long id);
}
