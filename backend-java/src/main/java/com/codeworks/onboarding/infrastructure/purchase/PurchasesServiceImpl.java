package com.codeworks.onboarding.infrastructure.purchase;

import com.codeworks.onboarding.domain.exceptions.UploadServiceException;
import com.codeworks.onboarding.domain.purchase.Purchase;
import com.codeworks.onboarding.infrastructure.purchase.upload.UploadCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PurchasesServiceImpl implements PurchasesService {
    private final PurchasesRepository purchasesRepository;
    private final UploadCSVService uploadCSVService;

    @Autowired
    public PurchasesServiceImpl(PurchasesRepository purchasesRepository,
                                UploadCSVService uploadCSVService) {
        this.purchasesRepository = purchasesRepository;
        this.uploadCSVService = uploadCSVService;
    }

    @Override
    public List<PurchaseEntity> getPurchases() {
        return purchasesRepository.findAll();
    }

    @Override
    public PurchaseEntity findPurchaseBy(long id) {
        return purchasesRepository.findById(id);
    }

    @Override
    public PurchaseEntity create(PurchaseEntity purchase) {
        return purchasesRepository.save(purchase);
    }

    @Override
    public PurchaseEntity deletePurchase(long id) {
        return purchasesRepository.deleteById(id);
    }

    @Override
    public List<Purchase> uploadCSV(MultipartFile multipartFile) {
        try {
            return uploadCSVService.uploadCSV(multipartFile);
        } catch(Exception e) {
            throw new UploadServiceException(
                    String.format("An exception occured while trying to upload file %s", multipartFile.getOriginalFilename()));
        }
    }
}
