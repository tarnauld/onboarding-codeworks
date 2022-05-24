package com.codeworks.onboarding.infrastructure.purchase.recap;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class Recap {
    @Getter
    private final Float shipping;

    @Getter
    private final PurchaseEntity purchase;

    @Getter
    private final List<Purchase> items;
}
