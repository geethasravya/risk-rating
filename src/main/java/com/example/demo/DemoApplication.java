package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.BitsightScore;
import com.example.demo.model.Vulnerability;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.BitsightRepository;
import com.example.demo.repository.VulnerabilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepo, BitsightRepository bitsightRepo, VulnerabilityRepository vulnRepo) {
        return args -> {
            Product p1 = new Product("Firewall", 2, 5);
            productRepo.save(p1);
            bitsightRepo.save(new BitsightScore(p1.getId(), 700));
            vulnRepo.save(new Vulnerability(p1.getId(), LocalDate.of(2019, 5, 12), "CVE-2019-xyz"));
        };
    }
}