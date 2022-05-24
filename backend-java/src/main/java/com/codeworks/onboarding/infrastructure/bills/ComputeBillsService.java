package com.codeworks.onboarding.infrastructure.bills;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.PurchaseRecap;

import java.util.List;

public interface ComputeBillsService {
    List<ComputedBills> execute(PurchaseRecap purchaseRecap);
}
