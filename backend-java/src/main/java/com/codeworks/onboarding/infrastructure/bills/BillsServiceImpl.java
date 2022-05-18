package com.codeworks.onboarding.infrastructure.bills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillsServiceImpl implements BillsService {
    private final BillsRepository billsRepository;

    @Autowired
    public BillsServiceImpl(BillsRepository billsRepository) {
        this.billsRepository = billsRepository;
    }

    @Override
    public List<BillsEntity> getBills() {
        return billsRepository.findAll();
    }

    @Override
    public BillsEntity findBillBy(long id) {
        return billsRepository.findById(id);
    }

    @Override
    public BillsEntity create(BillsEntity bills) {
        return billsRepository.save(bills);
    }

    @Override
    public BillsEntity deleteBill(long id) {
        return billsRepository.deleteById(id);
    }
}
