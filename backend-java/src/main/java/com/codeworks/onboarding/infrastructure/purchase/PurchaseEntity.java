package com.codeworks.onboarding.infrastructure.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private long userId;

    @Getter
    private LocalDate creationDate;

    @Getter
    private float shippingFee;
}
