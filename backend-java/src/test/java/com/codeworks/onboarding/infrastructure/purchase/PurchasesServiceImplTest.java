package com.codeworks.onboarding.infrastructure.purchase;

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
public class PurchasesServiceImplTest {
    @Mock
    private PurchasesRepository purchasesRepository;

    @InjectMocks
    private PurchasesServiceImpl purchasesService;

    private LocalDate creationDate;

    @Before
    public void setUp() {
        this.creationDate = LocalDate.now();
    }

    @Test
    public void should_get_purchases() {
        when(purchasesRepository.findAll()).thenReturn(buildPurchases());

        List<PurchaseEntity> purchaseItems = purchasesService.getPurchases();
        PurchaseEntity purchaseItem = purchaseItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(1L, purchaseItem.getUserId());
        Assert.assertEquals(100, purchaseItem.getShippingFee(), 0);
        Assert.assertEquals(creationDate, purchaseItem.getCreationDate());

        Assert.assertEquals(4, purchaseItems.size());
    }

    @Test
    public void should_get_purchase_by_id() {
        when(purchasesRepository.findById(Mockito.anyLong()))
                .thenReturn(PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(LocalDate.now()).build());

        PurchaseEntity purchaseItem = purchasesService.findPurchaseBy(1L);
        Assert.assertEquals(1L, purchaseItem.getUserId());
        Assert.assertEquals(100, purchaseItem.getShippingFee(), 0);
        Assert.assertEquals(creationDate, purchaseItem.getCreationDate());

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
    }

    @Test
    public void should_save_purchase() {
        when(purchasesRepository.save(Mockito.any())).thenReturn(PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(LocalDate.now()).build());

        PurchaseEntity purchaseItem = purchasesService.create(PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(1L, purchaseItem.getUserId());
        Assert.assertEquals(100, purchaseItem.getShippingFee(), 0);
        Assert.assertEquals(creationDate, purchaseItem.getCreationDate());
    }

    @Test
    public void should_delete_purchase() {
        when(purchasesRepository.deleteById(Mockito.anyLong())).thenReturn(PurchaseEntity.builder().build());

        PurchaseEntity purchaseItem = purchasesService.deletePurchase(1L);

        Assert.assertNull(purchaseItem.getId());
    }

    private List<PurchaseEntity> buildPurchases() {
        return Arrays.asList(
                PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(2L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(3L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(4L).userId(1L).shippingFee(100).creationDate(creationDate).build()
        );
    }
}
