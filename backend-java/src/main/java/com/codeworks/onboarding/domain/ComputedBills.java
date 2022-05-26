package com.codeworks.onboarding.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ComputedBills {
    @Getter
    private String name;

    @Getter
    private Float total;

    @Getter
    private Float shipping;
}
