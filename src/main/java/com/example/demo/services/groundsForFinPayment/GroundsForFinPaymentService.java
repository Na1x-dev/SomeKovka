package com.example.demo.services.groundsForFinPayment;

import com.example.demo.models.GroundsForFinPayment;


import java.util.List;

public interface GroundsForFinPaymentService {
    GroundsForFinPayment create(GroundsForFinPayment groundsForFinPayment);

    List<GroundsForFinPayment> readAll();


    GroundsForFinPayment readById(Long groundId);
}
