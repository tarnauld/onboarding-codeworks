package com.codeworks.onboarding.infrastructure.purchase.recap;

import com.codeworks.onboarding.domain.Item;
import com.codeworks.onboarding.domain.PurchaseRecap;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecapToPurchaseRecap {

    public PurchaseRecap execute(Recap recap) {
        return PurchaseRecap.builder()
                .shipping(recap.getShipping())
                .items(recap.getItems().stream().map(item -> Item.builder()
                                .name(item.getName())
                                .quantity(item.getQuantity())
                                .price(item.getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
