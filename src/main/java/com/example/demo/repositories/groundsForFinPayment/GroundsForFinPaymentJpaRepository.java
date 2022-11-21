package com.example.demo.repositories.groundsForFinPayment;

import com.example.demo.models.Gender;
import com.example.demo.models.GroundsForFinPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroundsForFinPaymentJpaRepository extends JpaRepository<GroundsForFinPayment, Long> {

}