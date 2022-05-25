package com.codeworks.onboarding.infrastructure.purchase;

import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsEntity;
import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @OneToMany(targetEntity = PurchaseItemsEntity.class, mappedBy = "purchase", cascade = CascadeType.REMOVE)
    @Getter
    private List<PurchaseItemsEntity> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @Getter
    private UserEntity user;

    @Column(name = "user_id")
    @Getter
    private Long userId;

    @Getter
    private LocalDate creationDate;

    @Getter
    private float shippingFee;
}
