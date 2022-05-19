package com.codeworks.onboarding.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Item {
    @Getter
    private String name;

    @Getter
    private Integer quantity;

    @Getter
    private Double price;
}
