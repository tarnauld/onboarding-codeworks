package com.codeworks.onboarding.infrastructure.bills.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bill_items")
@AllArgsConstructor
@Builder
public class BillItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private Long buyerId;

    @Getter
    private Integer amount;
}
