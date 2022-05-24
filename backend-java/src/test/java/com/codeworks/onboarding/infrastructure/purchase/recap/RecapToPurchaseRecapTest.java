package com.codeworks.onboarding.infrastructure.purchase.recap;

import com.codeworks.onboarding.domain.PurchaseRecap;
import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class RecapToPurchaseRecapTest {
    private RecapToPurchaseRecap mapper;

    @Before
    public void setUp() {
        mapper = new RecapToPurchaseRecap();
    }

    @Test
    public void should_execute_mapper() {
        PurchaseRecap purchaseRecap = mapper.execute(buildRecap());
        Assert.assertEquals(Float.valueOf(8.f), purchaseRecap.getShipping());
        Assert.assertEquals(2, purchaseRecap.getItems().size());
    }

    private Recap buildRecap() {
        return Recap.builder()
                .shipping(8.f)
                .purchase(PurchaseEntity.builder().build())
                .items(Arrays.asList(
                        new Purchase("pencil", 2.d, 10, "John"),
                        new Purchase("pencil", 2.d, 10, "Clara")
                ))
                .build();
    }
}