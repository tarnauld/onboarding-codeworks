package com.codeworks.onboarding.infrastructure.bills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private Long userId;

    @Getter
    private LocalDate creationDate;
}
