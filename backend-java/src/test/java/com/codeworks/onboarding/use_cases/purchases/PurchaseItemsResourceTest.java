package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsEntity;
import com.codeworks.onboarding.infrastructure.purchase.items.PurchaseItemsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseItemsResourceTest {
    @Mock
    private PurchaseItemsService purchaseItemsService;

    @InjectMocks
    private PurchaseItemsResource purchaseItemsResource;

    @Test
    public void should_get_purchase_items() {
        when(purchaseItemsService.getPurchasesItems()).thenReturn(buildPurchaseItems());

        List<PurchaseItemsEntity> purchaseItems = purchaseItemsResource.getBills();
        PurchaseItemsEntity purchaseItem = purchaseItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(4, purchaseItems.size());
    }

    @Test
    public void should_get_purchase_item_by_id() {
        when(purchaseItemsService.findPurchaseItemBy(Mockito.anyLong()))
                .thenReturn(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build());

        PurchaseItemsEntity purchaseItem = purchaseItemsResource.findPurchaseItemBy(1L);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
    }

    @Test
    public void should_save_purchase_item() {
        when(purchaseItemsService.create(Mockito.any())).thenReturn(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build());

        PurchaseItemsEntity purchaseItem = purchaseItemsResource.create(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build());

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
    }

    @Test
    public void should_delete_purchase_item() {
        when(purchaseItemsService.deletePurchaseItems(Mockito.anyLong())).thenReturn(PurchaseItemsEntity.builder().build());

        PurchaseItemsEntity purchaseItem = purchaseItemsResource.deletePurchaseItem(1L);

        Assert.assertNull(purchaseItem.getId());
    }

    private List<PurchaseItemsEntity> buildPurchaseItems() {
        return Arrays.asList(
                PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build(),
                PurchaseItemsEntity.builder().id(2L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build(),
                PurchaseItemsEntity.builder().id(3L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build(),
                PurchaseItemsEntity.builder().id(4L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20).build()
        );
    }
}
