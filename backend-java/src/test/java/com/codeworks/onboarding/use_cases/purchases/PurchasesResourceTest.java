package com.codeworks.onboarding.use_cases.purchases;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.exceptions.UploadServiceException;
import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.PurchaseEntity;
import com.codeworks.onboarding.infrastructure.purchase.PurchasesService;
import com.codeworks.onboarding.infrastructure.purchase.orchestrator.PurchaseOrchestrator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchasesResourceTest {
    @Mock
    private PurchasesService purchasesService;

    @Mock
    private PurchaseOrchestrator purchaseOrchestrator;

    @InjectMocks
    private PurchasesResource purchasesResource;

    private LocalDate creationDate;

    @Before
    public void setUp() {
        this.creationDate = LocalDate.now();
    }

    @Test
    public void should_get_purchases() {
        when(purchasesService.getPurchases()).thenReturn(buildPurchases());

        List<PurchaseEntity> purchaseItems = purchasesResource.getPurchases();
        PurchaseEntity purchaseItem = purchaseItems.get(0);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(1L, purchaseItem.getUserId());
        Assert.assertEquals(100, purchaseItem.getShippingFee(), 0);
        Assert.assertEquals(creationDate, purchaseItem.getCreationDate());

        Assert.assertEquals(4, purchaseItems.size());
    }

    @Test
    public void should_get_purchase_by_id() {
        when(purchasesService.findPurchaseBy(Mockito.anyLong()))
                .thenReturn(PurchaseEntity.builder().id(1L).userId(1L).shippingFee(100).creationDate(LocalDate.now()).build());

        PurchaseEntity purchaseItem = purchasesResource.findPurchaseBy(1L);

        Assert.assertEquals(Long.valueOf(1L), purchaseItem.getId());
        Assert.assertEquals(1L, purchaseItem.getUserId());
        Assert.assertEquals(100, purchaseItem.getShippingFee(), 0);
        Assert.assertEquals(creationDate, purchaseItem.getCreationDate());
    }

    @Test
    public void should_delete_purchase() {
        when(purchasesService.deletePurchase(Mockito.anyLong())).thenReturn(PurchaseEntity.builder().build());

        PurchaseEntity purchaseItem = purchasesResource.deletePurchase(1L);

        Assert.assertNull(purchaseItem.getId());
    }

    @Test
    public void should_upload_csv() {
        when(purchasesService.create(any(PurchaseEntity.class))).thenReturn(PurchaseEntity.builder().build());
        when(purchasesService.uploadCSV(any(MultipartFile.class)))
                .thenReturn(Collections.singletonList(new Purchase("", 2., 3, "Alice")));

        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("file", "upload.csv", "text/plain", buildContent().getBytes(StandardCharsets.UTF_8));

        ResponseEntity<List<ComputedBills>> response = purchasesResource.uploadCSV(mockMultipartFile, 8.f);

        Assert.assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void should_raise_an_exception_when_uploading_a_csv_file() {
        when(purchaseOrchestrator.process(any(MultipartFile.class), anyFloat())).thenThrow(
                new UploadServiceException("")
        );

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "upload.csv", "text/plain", buildContent().getBytes(StandardCharsets.UTF_8));

        ResponseEntity<List<ComputedBills>> response = purchasesResource.uploadCSV(mockMultipartFile, 8.f);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
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
