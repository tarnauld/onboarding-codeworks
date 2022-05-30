package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.codeworks.onboarding.infrastructure.users.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseCSVMapperTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private PurchaseCSVMapper mapper;

    @Test
    public void should_map_purchasecsv() {
        PurchaseCSV purchaseCSV = mock(PurchaseCSV.class);
        when(purchaseCSV.getLabel()).thenReturn("pencils");
        when(purchaseCSV.getName()).thenReturn("John");
        when(purchaseCSV.getQuantity()).thenReturn(10);
        when(purchaseCSV.getUnitPrice()).thenReturn(2d);
        when(purchaseCSV.getAmount()).thenReturn(20d);

        when(userService.findUserByName(anyString())).thenReturn(UserEntity.builder()
                .id(1L)
                .name("John")
                .birthDate(LocalDate.now())
                .build());

        Purchase execute = mapper.execute(purchaseCSV);
        Assert.assertEquals("pencils", execute.getLabel());
        Assert.assertEquals("John", execute.getName());
        Assert.assertEquals(Integer.valueOf(10), execute.getQuantity());
        Assert.assertEquals(Double.valueOf(2d), execute.getUnitPrice());
    }
}