package com.codeworks.onboarding.infrastructure.purchase.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "purchase_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private Long purchaseId;

    @Getter
    private String label;

    @Getter
    private Integer quantity;

    @Getter
    private Long buyerId;

    @Getter
    private Double unitPrice;
}
