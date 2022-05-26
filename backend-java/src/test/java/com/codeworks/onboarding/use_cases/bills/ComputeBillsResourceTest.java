package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.Item;
import com.codeworks.onboarding.domain.PurchaseRecap;
import com.codeworks.onboarding.infrastructure.bills.ComputeBillsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ComputeBillsResourceTest {
    @Mock
    private ComputeBillsServiceImpl computeBillsService;

    @InjectMocks
    private ComputeBillsResource computeBillsResource;

    @Test
    public void should_compute_bills() {
        when(computeBillsService.execute(Mockito.any())).thenReturn(Arrays.asList(
                ComputedBills.builder().name("Alice").total(1f).shipping(.33f).build(),
                ComputedBills.builder().name("Bertrand").total(1f).shipping(.34f).build(),
                ComputedBills.builder().name("John").total(1f).shipping(.33f).build()
                ));
        Assert.assertEquals(3, computeBillsResource.execute(buildShippingFee()).size());
    }

    private PurchaseRecap buildShippingFee() {
        return PurchaseRecap.builder()
                .shipping(1.f)
                .items(Arrays.asList(
                        Item.builder().name("Alice").quantity(1).price(1.).build(),
                        Item.builder().name("Bertrand").quantity(1).price(1.).build(),
                        Item.builder().name("John").quantity(1).price(1.).build()
                ))
                .build();
    }
}
