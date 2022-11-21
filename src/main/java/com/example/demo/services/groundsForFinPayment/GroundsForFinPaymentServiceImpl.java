package com.example.demo.services.groundsForFinPayment;

import com.example.demo.models.GroundsForFinPayment;
import com.example.demo.repositories.groundsForFinPayment.GroundsForFinPaymentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundsForFinPaymentServiceImpl implements GroundsForFinPaymentService {

    @Autowired
    GroundsForFinPaymentJpaRepository groundsForFinPaymentJpaRepository;

    @Override
    public GroundsForFinPayment create(GroundsForFinPayment groundsForFinPayment) {
        return groundsForFinPaymentJpaRepository.save(groundsForFinPayment);
    }

    @Override
    public List<GroundsForFinPayment> readAll() {
        return groundsForFinPaymentJpaRepository.findAll();
    }


}
