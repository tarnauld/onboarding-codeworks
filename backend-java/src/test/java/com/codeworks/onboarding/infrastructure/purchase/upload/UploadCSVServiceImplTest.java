package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.codeworks.onboarding.domain.purchase.Purchase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringRunner.class)
public class UploadCSVServiceImplTest {
    @InjectMocks
    private UploadCSVServiceImpl uploadCSVService;

    @Test
    public void should_upload_csv() throws IOException {
        List<Purchase> purchases = uploadCSVService.uploadCSV(
                new MockMultipartFile("file", "upload.csv", "text/plain", buildContent().getBytes(StandardCharsets.UTF_8)));

        Purchase purchase = purchases.get(0);

        Assert.assertEquals(7, purchases.size());
        Assert.assertEquals("pencils", purchase.getLabel());
        Assert.assertEquals("Bertrand", purchase.getName());
    }

    private String buildContent() {
        return "item              , unitp  , qty , amount , buyer\n" +
                "   pencils        ,0.50 ,20,10.00,    Bertrand\n" +
                "paper,1.50 ,25, 37.50,Alice\n" +
                "paper,1.80,50,90.00,Desmond\n" +
                "laundry detergent,2.00,10,20.00,Clara\n" +
                "trash bags,4.30,100,430.00,Clara\n" +
                "gift cards,8.00,1,8.00,Bertrand\n" +
                "lightbulbs,1.00,10,10.00,Clara";
    }
}
