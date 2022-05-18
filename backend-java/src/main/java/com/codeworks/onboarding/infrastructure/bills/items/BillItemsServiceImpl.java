package com.codeworks.onboarding.infrastructure.bills.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillItemsServiceImpl implements BillItemsService {
    private final BillItemsRepository billItemsRepository;

    @Autowired
    public BillItemsServiceImpl(BillItemsRepository billItemsRepository) {
        this.billItemsRepository = billItemsRepository;
    }

    @Override
    public List<BillItemsEntity> getBillItems() {
        return billItemsRepository.findAll();
    }

    @Override
    public BillItemsEntity findBillItemBy(long id) {
        return billItemsRepository.findById(id);
    }

    @Override
    public BillItemsEntity create(BillItemsEntity billItems) {
        return billItemsRepository.save(billItems);
    }

    @Override
    public BillItemsEntity deleteBillItem(long id) {
        return billItemsRepository.deleteById(id);
    }
}
