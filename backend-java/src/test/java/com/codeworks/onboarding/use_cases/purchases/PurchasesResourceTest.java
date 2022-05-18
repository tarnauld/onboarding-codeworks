package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchasesResourceTest {
    @Mock
    private PurchasesService purchasesService;

    @InjectMocks
    private PurchasesResource purchasesResource;

    @Test
    public void should_get_purchases() {
        when(purchasesService.getPurchases()).thenReturn(buildPurchases());

        List<PurchaseEntity> purchaseItems = purchasesResource.getPurchases();
        PurchaseEntity purchaseItem = purchaseItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(4, purchaseItems.size());
    }

    @Test
    public void should_get_purchase_by_id() {
        when(purchasesService.findPurchaseBy(Mockito.anyLong()))
                .thenReturn(PurchaseEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        PurchaseEntity purchaseItem = purchasesResource.findPurchaseBy(1L);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
    }

    @Test
    public void should_save_purchase() {
        when(purchasesService.create(Mockito.any())).thenReturn(PurchaseEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        PurchaseEntity purchaseItem = purchasesResource.create(PurchaseEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
    }

    @Test
    public void should_delete_purchase() {
        when(purchasesService.deletePurchase(Mockito.anyLong())).thenReturn(PurchaseEntity.builder().build());

        PurchaseEntity purchaseItem = purchasesResource.deletePurchase(1L);

        Assert.assertNull(purchaseItem.getId());
    }

    private List<PurchaseEntity> buildPurchases() {
        return Arrays.asList(
                PurchaseEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build(),
                PurchaseEntity.builder().id(2L).userId(1L).creationDate(LocalDate.now()).build(),
                PurchaseEntity.builder().id(3L).userId(1L).creationDate(LocalDate.now()).build(),
                PurchaseEntity.builder().id(4L).userId(1L).creationDate(LocalDate.now()).build()
        );
    }
}
