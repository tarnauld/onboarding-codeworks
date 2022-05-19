package com.codeworks.onboarding.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class ShippingFee {
    @Getter
    private Long shipping;

    @Getter
    private List<Item> items;
}
