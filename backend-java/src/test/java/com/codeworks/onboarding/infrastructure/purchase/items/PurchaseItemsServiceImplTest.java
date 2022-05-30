package com.codeworks.onboarding.infrastructure.purchase.items;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.codeworks.onboarding.infrastructure.users.UserService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseItemsServiceImplTest {
    @Mock
    private PurchaseItemsRepository purchaseItemsRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PurchaseItemsServiceImpl purchaseItemsService;

    @Test
    public void should_get_purchase_items() {
        when(purchaseItemsRepository.findAll()).thenReturn(buildPurchaseItems());

        List<PurchaseItemsEntity> purchaseItems = purchaseItemsService.getPurchasesItems();
        PurchaseItemsEntity purchaseItem = purchaseItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getPurchaseId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getBuyerId());
        Assert.assertEquals("pencil", purchaseItem.getLabel());
        Assert.assertEquals(Integer.valueOf(10), purchaseItem.getQuantity());
        Assert.assertEquals(Double.valueOf(20d), purchaseItem.getUnitPrice());
        Assert.assertEquals(4, purchaseItems.size());
    }

    @Test
    public void should_get_purchase_item_by_id() {
        when(purchaseItemsRepository.findById(Mockito.anyLong()))
                .thenReturn(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build());

        PurchaseItemsEntity purchaseItem = purchaseItemsService.findPurchaseItemBy(1L);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getPurchaseId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getBuyerId());
        Assert.assertEquals("pencil", purchaseItem.getLabel());
        Assert.assertEquals(Integer.valueOf(10), purchaseItem.getQuantity());
        Assert.assertEquals(Double.valueOf(20d), purchaseItem.getUnitPrice());
    }

    @Test
    public void should_save_purchase_item() {
        when(purchaseItemsRepository.save(Mockito.any())).thenReturn(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build());

        PurchaseItemsEntity purchaseItem = purchaseItemsService.create(PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build());

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getPurchaseId());
        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getBuyerId());
        Assert.assertEquals("pencil", purchaseItem.getLabel());
        Assert.assertEquals(Integer.valueOf(10), purchaseItem.getQuantity());
        Assert.assertEquals(Double.valueOf(20d), purchaseItem.getUnitPrice());
    }

    @Test
    public void should_delete_purchase_item() {
        when(purchaseItemsRepository.deleteById(Mockito.anyLong())).thenReturn(PurchaseItemsEntity.builder().build());

        PurchaseItemsEntity purchaseItem = purchaseItemsService.deletePurchaseItems(1L);

        Assert.assertNull(purchaseItem.getId());
    }

    @Test
    public void should_process() {
        when(userService.findUserByName(anyString())).thenReturn(UserEntity.builder().name("John").id(1L).build());
        List<PurchaseItemsEntity> purchases = purchaseItemsService.process(buildPurchases(), 1L);
        Assert.assertEquals(2, purchases.size());
    }

    private List<Purchase> buildPurchases() {
        return Arrays.asList(
            new Purchase("pencil", 2d, 10, "John", LocalDate.now()),
            new Purchase("pencil", 2d, 10, "Clara", LocalDate.now())
        );
    }

    private List<PurchaseItemsEntity> buildPurchaseItems() {
        return Arrays.asList(
                PurchaseItemsEntity.builder().id(1L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build(),
                PurchaseItemsEntity.builder().id(2L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build(),
                PurchaseItemsEntity.builder().id(3L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build(),
                PurchaseItemsEntity.builder().id(4L).purchaseId(1L).buyerId(1L).label("pencil").quantity(10).unitPrice(20d).build()
        );
    }
}
