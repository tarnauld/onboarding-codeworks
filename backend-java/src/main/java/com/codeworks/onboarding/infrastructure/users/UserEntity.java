package com.codeworks.onboarding.infrastructure.users;

import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private LocalDate birthDate;

    @OneToMany(targetEntity = PurchaseEntity.class, mappedBy = "user", cascade = CascadeType.REMOVE)
    @Getter
    @JsonIgnore
    private List<PurchaseEntity> purchases;
}
