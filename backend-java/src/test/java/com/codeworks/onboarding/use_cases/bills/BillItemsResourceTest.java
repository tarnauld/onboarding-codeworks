package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.infrastructure.bills.items.BillItemsEntity;
import com.codeworks.onboarding.infrastructure.bills.items.BillItemsService;
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
public class BillItemsResourceTest {
    @Mock
    private BillItemsService billItemsService;

    @InjectMocks
    private BillItemsResource billItemsResource;

    @Test
    public void should_get_bill_items() {
        when(billItemsService.getBillItems()).thenReturn(buildBillItems());

        List<BillItemsEntity> billItems = billItemsResource.getBillItems();
        BillItemsEntity billItem = billItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
        Assert.assertEquals(4, billItems.size());
    }

    @Test
    public void should_get_bill_item_by_id() {
        when(billItemsService.findBillItemBy(Mockito.anyLong()))
                .thenReturn(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        BillItemsEntity billItem = billItemsResource.findBillItemBy(1L);

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
    }

    @Test
    public void should_save_bill_item() {
        when(billItemsService.create(Mockito.any())).thenReturn(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        BillItemsEntity billItem = billItemsResource.createBillItem(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
    }

    @Test
    public void should_delete_bill_item() {
        when(billItemsService.deleteBillItem(Mockito.anyLong())).thenReturn(BillItemsEntity.builder().build());

        BillItemsEntity billItem = billItemsResource.deleteBillItem(1L);

        Assert.assertNull(billItem.getId());
    }

    private List<BillItemsEntity> buildBillItems() {
        return Arrays.asList(
                BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build(),
                BillItemsEntity.builder().id(2L).buyerId(1L).amount(100).build(),
                BillItemsEntity.builder().id(3L).buyerId(1L).amount(100).build(),
                BillItemsEntity.builder().id(4L).buyerId(1L).amount(100).build()
        );
    }
}
