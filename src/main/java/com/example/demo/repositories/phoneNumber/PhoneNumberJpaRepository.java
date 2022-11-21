package com.example.demo.repositories.phoneNumber;

import com.example.demo.models.MaterialPayment;
import com.example.demo.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberJpaRepository extends JpaRepository<PhoneNumber, Long> {

}