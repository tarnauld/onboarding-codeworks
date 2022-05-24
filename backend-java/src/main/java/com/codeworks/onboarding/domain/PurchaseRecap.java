package com.codeworks.onboarding.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class PurchaseRecap {
    @Getter
    private Float shipping;

    @Getter
    private List<Item> items;
}
