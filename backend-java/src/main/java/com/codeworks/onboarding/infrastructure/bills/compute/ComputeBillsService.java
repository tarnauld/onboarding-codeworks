package com.codeworks.onboarding.infrastructure.bills.compute;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.ShippingFee;

import java.util.List;

public interface ComputeBillsService {
    List<ComputedBills> execute(ShippingFee shippingFee);
}
