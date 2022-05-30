package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

public class PurchaseCSV {
    @CsvBindByPosition(position = 0)
    @Getter
    private String label;

    @CsvBindByPosition(position = 1)
    @Getter
    private Double unitPrice;

    @CsvBindByPosition(position = 2)
    @Getter
    private Integer quantity;

    @CsvBindByPosition(position = 3)
    @Getter
    private Double amount;

    @CsvBindByPosition(position = 4)
    @Getter
    private String name;
}
