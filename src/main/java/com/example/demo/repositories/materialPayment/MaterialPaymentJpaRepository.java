package com.example.demo.repositories.materialPayment;

import com.example.demo.models.ApplicationType;
import com.example.demo.models.MaterialPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialPaymentJpaRepository extends JpaRepository<MaterialPayment, Long> {
}