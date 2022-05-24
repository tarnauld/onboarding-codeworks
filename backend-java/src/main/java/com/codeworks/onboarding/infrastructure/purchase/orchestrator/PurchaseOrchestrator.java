package com.codeworks.onboarding.infrastructure.purchase.orchestrator;

import com.codeworks.onboarding.domain.ComputedBills;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PurchaseOrchestrator {
    List<ComputedBills> process(MultipartFile multipartFile, Float shipping);
}
