package com.codeworks.onboarding.infrastructure.bills.items;

import java.util.List;

public interface BillItemsService {
    List<BillItemsEntity> getBillItems();
    BillItemsEntity findBillItemBy(long id);
    BillItemsEntity create(BillItemsEntity billItems);
    BillItemsEntity deleteBillItem(long id);
}
