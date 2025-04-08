package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final BitsightRepository bitsightRepo;
    private final VulnerabilityRepository vulnRepo;

    public ProductService(ProductRepository productRepo, BitsightRepository bitsightRepo, VulnerabilityRepository vulnRepo) {
        this.productRepo = productRepo;
        this.bitsightRepo = bitsightRepo;
        this.vulnRepo = vulnRepo;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();

        for (Product product : products) {
            int bitsight = bitsightRepo.findById(product.getId())
                                       .map(BitsightScore::getRating)
                                       .orElse(0);
            product.setVendorReputation(calculateReputation(bitsight));

            List<Vulnerability> vulns = vulnRepo.findByProductId(product.getId());
            product.setVulnerabilities(calculateVulnerabilities(vulns));
        }

        return products;
    }

    private int calculateReputation(int bitsight) {
        if (bitsight >= 740 && bitsight <= 820) return 1;
        else return 5;
    }

    private int calculateVulnerabilities(List<Vulnerability> vulns) {
        int count = vulns.size();
        if (count == 0) return 0;

        LocalDate earliest = vulns.stream()
                                  .map(Vulnerability::getReportedDate)
                                  .min(LocalDate::compareTo)
                                  .orElse(LocalDate.now());
        long years = ChronoUnit.YEARS.between(earliest, LocalDate.now());

        if (count < 5 && years >= 5) return 1;
        if (count >= 5 && years < 15) return 5;
        if (count > 15) return 10;
        return 1;
    }
}