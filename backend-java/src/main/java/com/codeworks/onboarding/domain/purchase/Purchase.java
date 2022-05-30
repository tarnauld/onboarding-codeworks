package com.codeworks.onboarding.domain.purchase;

import java.io.Serializable;
import java.time.LocalDate;

public class Purchase implements Serializable {
    private final String label;

    private final Double unitPrice;

    private final Integer quantity;

    private final String name;

    private final LocalDate birthday;

    public Purchase(String label, Double unitPrice, Integer quantity, String name, LocalDate birthday) {
        this.label = label;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.name = name;
        this.birthday = birthday;
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

    public LocalDate getBirthday() {
        return birthday;
    }
}
