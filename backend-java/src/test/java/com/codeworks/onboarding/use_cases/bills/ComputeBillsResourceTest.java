package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.Item;
import com.codeworks.onboarding.domain.ShippingFee;
import com.codeworks.onboarding.infrastructure.bills.compute.ComputeBillsServiceImpl;
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
                ComputedBills.builder().name("Alice").total(1d).shipping(.33).build(),
                ComputedBills.builder().name("Bertrand").total(1d).shipping(.34).build(),
                ComputedBills.builder().name("John").total(1d).shipping(.33).build()
                ));
        Assert.assertEquals(3, computeBillsResource.execute(buildShippingFee()).size());
    }

    private ShippingFee buildShippingFee() {
        return ShippingFee.builder()
                .shipping(1L)
                .items(Arrays.asList(
                        Item.builder().name("Alice").quantity(1).price(1.).build(),
                        Item.builder().name("Bertrand").quantity(1).price(1.).build(),
                        Item.builder().name("John").quantity(1).price(1.).build()
                ))
                .build();
    }
}
