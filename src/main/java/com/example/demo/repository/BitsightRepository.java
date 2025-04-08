package com.example.demo.repository;

import com.example.demo.model.BitsightScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitsightRepository extends JpaRepository<BitsightScore, Long> {}