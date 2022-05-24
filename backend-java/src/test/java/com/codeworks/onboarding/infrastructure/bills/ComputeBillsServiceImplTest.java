package com.codeworks.onboarding.infrastructure.bills;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.PurchaseRecap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ComputeBillsServiceImplTest {
    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private ComputeBillsServiceImpl computeBillsService;

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        computeBillsService = new ComputeBillsServiceImpl(restTemplateBuilder);
        restTemplate = mock(RestTemplate.class);
        ReflectionTestUtils.setField(computeBillsService, "restTemplate", restTemplate);
    }

    @Test
    public void should_call_api() {
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                                    ArgumentMatchers.eq(HttpMethod.POST),
                                    ArgumentMatchers.any(HttpEntity.class),
                                    ArgumentMatchers.any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(buildComputedBills(), HttpStatus.OK));

        List<ComputedBills> computedBills = computeBillsService.execute(PurchaseRecap.builder().build());

        Assert.assertEquals(3, computedBills.size());
    }

    private List<ComputedBills> buildComputedBills() {
        return Arrays.asList(
                ComputedBills.builder().build(),
                ComputedBills.builder().build(),
                ComputedBills.builder().build()
        );
    }
}
