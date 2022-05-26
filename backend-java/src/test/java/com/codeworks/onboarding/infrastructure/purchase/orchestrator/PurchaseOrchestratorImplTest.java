package com.codeworks.onboarding.infrastructure.purchase.orchestrator;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.Item;
import com.codeworks.onboarding.domain.PurchaseRecap;
import com.codeworks.onboarding.infrastructure.bills.ComputeBillsService;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsService;
import com.codeworks.onboarding.infrastructure.purchase.recap.RecapToPurchaseRecap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseOrchestratorImplTest {
    @Mock
    private PurchasesService purchasesService;

    @Mock
    private PurchaseItemsService purchaseItemsService;

    @Mock
    private ComputeBillsService computeBillsService;

    @Mock
    private RecapToPurchaseRecap mapper;

    @InjectMocks
    private PurchaseOrchestratorImpl purchaseOrchestrator;

    @Test
    public void should_process_orchestrator() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "orders.csv", "text/plain", buildContent().getBytes());
        when(purchasesService.create(any(PurchaseEntity.class))).thenReturn(
                PurchaseEntity.builder()
                        .shippingFee(8f)
                        .userId(1L)
                        .creationDate(LocalDate.now())
                        .build()
        );
        when(computeBillsService.execute(any(PurchaseRecap.class))).thenReturn(Collections.emptyList());

        List<ComputedBills> process = purchaseOrchestrator.process(mockMultipartFile, 8f);
        Assert.assertEquals(0, process.size());
    }

    @Test
    public void should_process_recap_orchestrator() {
        PurchaseRecap recap = PurchaseRecap.builder()
                .shipping(100.f)
                .items(Arrays.asList(
                        Item.builder().name("John").quantity(10).price(1.).build(),
                        Item.builder().name("Clara").quantity(10).price(1.).build(),
                        Item.builder().name("Desmond").quantity(10).price(1.).build()
                ))
                .build();

        when(computeBillsService.execute(any(PurchaseRecap.class))).thenReturn(buildComputedBills());

        List<ComputedBills> process = purchaseOrchestrator.process(recap);

        Assert.assertEquals(3, process.size());
    }

    private List<ComputedBills> buildComputedBills() {
        return Arrays.asList(
                ComputedBills.builder().name("Clara").shipping(34f).total(10f).build(),
                ComputedBills.builder().name("Desmond").shipping(33f).total(10f).build(),
                ComputedBills.builder().name("John").shipping(33f).total(10f).build()
        );
    }

    private String buildContent() {
        return "pencils,0.50 ,20,10.00,Bertrand\n" +
                "paper,1.50 ,25, 37.50,Alice\n" +
                "paper,1.80,50,90.00,Desmond\n" +
                "laundry detergent,2.00,10,20.00,Clara\n" +
                "trash bags,4.30,100,430.00,Clara\n" +
                "gift cards,8.00,1,8.00,Bertrand\n" +
                "lightbulbs,1.00,10,10.00,Clara";
    }
}
