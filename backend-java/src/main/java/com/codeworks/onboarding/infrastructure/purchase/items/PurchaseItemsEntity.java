package com.codeworks.onboarding.infrastructure.purchase.items;

import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name="purchase_id", nullable=false, insertable = false, updatable = false)
    private PurchaseEntity purchase;

    @Column(name = "purchase_id")
    @Getter
    private Long purchaseId;

    @Getter
    private String label;

    @Getter
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false, insertable = false, updatable = false)
    @Getter
    private UserEntity user;

    @Column(name = "buyer_id")
    @Getter
    private Long buyerId;

    @Getter
    private Double unitPrice;
}
