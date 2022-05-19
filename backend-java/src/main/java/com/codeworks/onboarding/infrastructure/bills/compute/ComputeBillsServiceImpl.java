package com.codeworks.onboarding.infrastructure.bills.compute;

import com.codeworks.onboarding.domain.ComputedBills;
import com.codeworks.onboarding.domain.ShippingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ComputeBillsServiceImpl implements ComputeBillsService {
    private final RestTemplate restTemplate;

    @Value("${node.url}")
    private String hostname;

    @Value("${node.port}")
    private String port;

    @Autowired
    public ComputeBillsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<ComputedBills> execute(ShippingFee shippingFee) {
        String url = String.format("%s:%s/compute-bills", hostname, port);
        HttpEntity<ShippingFee> entity = new HttpEntity<>(shippingFee);
        return restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<ComputedBills>>() {}).getBody();
    }
}
