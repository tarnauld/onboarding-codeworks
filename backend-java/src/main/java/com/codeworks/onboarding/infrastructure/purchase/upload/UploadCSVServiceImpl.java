package com.codeworks.onboarding.infrastructure.purchase.upload;

import com.codeworks.onboarding.domain.purchase.Purchase;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadCSVServiceImpl implements UploadCSVService {
    @Override
    public List<Purchase> uploadCSV(MultipartFile multipartFile) throws IOException {
        Reader reader = new InputStreamReader(multipartFile.getInputStream());

        List<PurchaseCSV> purchases = new CsvToBeanBuilder<PurchaseCSV>(reader)
                .withType(PurchaseCSV.class)
                .withSkipLines(1)
                .build()
                .parse();

        return purchases.stream().map(PurchaseCSV::toPurchase).collect(Collectors.toList());
    }
}
