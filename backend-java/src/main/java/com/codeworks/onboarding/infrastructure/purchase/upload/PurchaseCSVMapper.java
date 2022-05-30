package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseCSVMapper {
    private final UserService userService;

    @Autowired
    public PurchaseCSVMapper(UserService userService) {
        this.userService = userService;
    }

    public Purchase execute(PurchaseCSV purchaseCSV) {
        return new Purchase(purchaseCSV.getLabel().trim(),
                purchaseCSV.getUnitPrice(),
                purchaseCSV.getQuantity(),
                purchaseCSV.getName().trim(),
                userService.findUserByName(purchaseCSV.getName().trim()).getBirthDate());
    }
}
