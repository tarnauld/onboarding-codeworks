package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.opencsv.bean.CsvBindByPosition;

public class PurchaseCSV {
    @CsvBindByPosition(position = 0)
    private String label;

    @CsvBindByPosition(position = 1)
    private Double unitPrice;

    @CsvBindByPosition(position = 2)
    private Integer quantity;

    @CsvBindByPosition(position = 3)
    private Double amount;

    @CsvBindByPosition(position = 4)
    private String name;

    @Override
    public String toString() {
        return String.format("Label: %s; Unit price: %s; Quantity: %s; Amount: %s; Name: %s", label, unitPrice, quantity, amount, name);
    }

    public Purchase toPurchase() {
        return new Purchase(label.trim(), unitPrice, quantity, amount, name.trim());
    }
}
