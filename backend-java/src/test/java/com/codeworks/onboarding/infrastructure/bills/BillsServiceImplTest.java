package com.codeworks.onboarding.infrastructure.bills;

import org.junit.Assert;
import org.junit.Before;
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
public class BillsServiceImplTest {
    @Mock
    private BillsRepository billsRepository;

    @InjectMocks
    private BillsServiceImpl billsService;

    private LocalDate creationDate;

    @Before
    public void setUp() {
        this.creationDate = LocalDate.now();
    }

    @Test
    public void should_get_bills() {
        when(billsRepository.findAll()).thenReturn(buildBills());

        List<BillsEntity> bills = billsService.getBills();
        BillsEntity bill = bills.get(0);

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
        Assert.assertEquals(Long.valueOf(1L), bill.getUserId());
        Assert.assertEquals(creationDate, bill.getCreationDate());

        Assert.assertEquals(4, bills.size());
    }

    @Test
    public void should_get_bill_by_id() {
        when(billsRepository.findById(Mockito.anyLong()))
                .thenReturn(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        BillsEntity bill = billsService.findBillBy(1L);

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
        Assert.assertEquals(Long.valueOf(1L), bill.getUserId());
        Assert.assertEquals(creationDate, bill.getCreationDate());
    }

    @Test
    public void should_save_bill() {
        when(billsRepository.save(Mockito.any())).thenReturn(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        BillsEntity bill = billsService.create(BillsEntity.builder().id(1L).userId(1L).creationDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), bill.getId());
        Assert.assertEquals(Long.valueOf(1L), bill.getUserId());
        Assert.assertEquals(creationDate, bill.getCreationDate());
    }

    @Test
    public void should_delete_bill() {
        when(billsRepository.deleteById(Mockito.anyLong())).thenReturn(BillsEntity.builder().build());

        BillsEntity bill = billsService.deleteBill(1L);

        Assert.assertNull(bill.getId());
    }

    private List<BillsEntity> buildBills() {
        return Arrays.asList(
                BillsEntity.builder().id(1L).userId(1L).creationDate(creationDate).build(),
                BillsEntity.builder().id(2L).userId(1L).creationDate(creationDate).build(),
                BillsEntity.builder().id(3L).userId(1L).creationDate(creationDate).build(),
                BillsEntity.builder().id(4L).userId(1L).creationDate(creationDate).build()
        );
    }
}
