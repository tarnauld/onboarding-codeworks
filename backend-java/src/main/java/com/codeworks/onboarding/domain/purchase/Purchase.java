package com.codeworks.onboarding.domain.purchase;

import java.io.Serializable;

public class Purchase implements Serializable {
    private final String label;

    private final Double unitPrice;

    private final Integer quantity;

    private final Double amount;

    private final String name;

    public Purchase(String label, Double unitPrice, Integer quantity, Double amount, String name) {
        this.label = label;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Label: %s; Unit price: %s; Quantity: %s; Amount: %s; Name: %s", label, unitPrice, quantity, amount, name);
    }
}
