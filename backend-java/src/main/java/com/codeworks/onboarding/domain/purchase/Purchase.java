package com.codeworks.onboarding.domain.purchase;

import java.io.Serializable;

public class Purchase implements Serializable {
    private final String label;

    private final Double unitPrice;

    private final Integer quantity;

    private final String name;

    public Purchase(String label, Double unitPrice, Integer quantity, String name) {
        this.label = label;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
