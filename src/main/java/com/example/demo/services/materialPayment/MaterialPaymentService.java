package com.example.demo.services.materialPayment;

import com.example.demo.models.ApplicationType;
import com.example.demo.models.MaterialPayment;

import java.util.List;

public interface MaterialPaymentService {
    MaterialPayment create(MaterialPayment materialPayment);

    List<MaterialPayment> readAll();

}
