package com.codeworks.onboarding.use_cases.bills;

import com.codeworks.onboarding.infrastructure.bills.BillsEntity;
import com.codeworks.onboarding.infrastructure.bills.BillsService;
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
public class BillsResourceTest {
    @Mock
    private BillsService billsService;;

    @InjectMocks
    private BillsResource billsResource;

    @Test
    public void should_get_bills() {
        when(billsService.getBills()).thenReturn(buildBills());

        List<BillsEntity> bills = billsResource.getBills();
        BillsEntity bill = bills.get(0);

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
        Assert.assertEquals(4, bills.size());
    }

    @Test
    public void should_get_bill_by_id() {
        when(billsService.findBillBy(Mockito.anyLong()))
                .thenReturn(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        BillsEntity bill = billsResource.findBillBy(1L);

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
    }

    @Test
    public void should_save_bill() {
        when(billsService.create(Mockito.any())).thenReturn(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        BillsEntity bill = billsResource.createBill(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
    }

    @Test
    public void should_delete_bill() {
        when(billsService.deleteBill(Mockito.anyLong())).thenReturn(BillsEntity.builder().build());

        BillsEntity bill = billsResource.deleteBill(1L);

        Assert.assertNull(bill.getId());
    }

    private List<BillsEntity> buildBills() {
        return Arrays.asList(
                BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build(),
                BillsEntity.builder().id(2L).userId(1L).creationDate(LocalDate.now()).build(),
                BillsEntity.builder().id(3L).userId(1L).creationDate(LocalDate.now()).build(),
                BillsEntity.builder().id(4L).userId(1L).creationDate(LocalDate.now()).build()
        );
    }
}
