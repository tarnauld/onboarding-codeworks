package com.codeworks.onboarding.infrastructure.purchase;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.upload.UploadCSVServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchasesServiceImplTest {
    @Mock
    private PurchasesRepository purchasesRepository;

    @Mock
    private UploadCSVServiceImpl uploadCSVService;

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

    @Test
    public void should_upload_csv() throws IOException {
        when(uploadCSVService.uploadCSV(any(MultipartFile.class)))
                .thenReturn(Collections.singletonList(new Purchase("", 2., 3, 6., "Alice")));

        List<Purchase> purchases =
                purchasesService.uploadCSV(
                        new MockMultipartFile("file", "upload.csv", "text/plain", buildContent().getBytes(StandardCharsets.UTF_8)));

        Assert.assertEquals(1, purchases.size());

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

    private List<PurchaseEntity> buildPurchases() {
        return Arrays.asList(
                PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(2L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(3L).userId(1L).shippingFee(100).creationDate(creationDate).build(),
                PurchaseEntity.builder().id(4L).userId(1L).shippingFee(100).creationDate(creationDate).build()
        );
    }
}
