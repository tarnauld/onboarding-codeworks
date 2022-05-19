package com.codeworks.onboarding.infrastructure.bills.items;

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
public class BillItemsServiceImplTest {
    @Mock
    private BillItemsRepository billItemsRepository;

    @InjectMocks
    private BillItemsServiceImpl billItemsService;

    @Test
    public void should_get_bill_items() {
        when(billItemsRepository.findAll()).thenReturn(buildBillItems());

        List<BillItemsEntity> billItems = billItemsService.getBillItems();
        BillItemsEntity billItem = billItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
        Assert.assertEquals(Long.valueOf(1L), billItem.getBuyerId());
        Assert.assertEquals(Integer.valueOf(100), billItem.getAmount());

        Assert.assertEquals(4, billItems.size());
    }

    @Test
    public void should_get_bill_item_by_id() {
        when(billItemsRepository.findById(Mockito.anyLong()))
                .thenReturn(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        BillItemsEntity billItem = billItemsService.findBillItemBy(1L);

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
        Assert.assertEquals(Long.valueOf(1L), billItem.getBuyerId());
        Assert.assertEquals(Integer.valueOf(100), billItem.getAmount());
    }

    @Test
    public void should_save_bill_item() {
        when(billItemsRepository.save(Mockito.any())).thenReturn(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        BillItemsEntity billItem = billItemsService.create(BillItemsEntity.builder().id(1L).buyerId(1L).amount(100).build());

        Assert.assertEquals(Long.valueOf(1L), billItem.getId());
        Assert.assertEquals(Long.valueOf(1L), billItem.getBuyerId());
        Assert.assertEquals(Integer.valueOf(100), billItem.getAmount());
    }

    @Test
    public void should_delete_bill_item() {
        when(billItemsRepository.deleteById(Mockito.anyLong())).thenReturn(BillItemsEntity.builder().build());

        BillItemsEntity billItem = billItemsService.deleteBillItem(1L);

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
