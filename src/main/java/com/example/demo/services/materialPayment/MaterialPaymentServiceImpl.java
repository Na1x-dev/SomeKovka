package com.example.demo.services.materialPayment;

import com.example.demo.models.MaterialPayment;
import com.example.demo.repositories.materialPayment.MaterialPaymentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialPaymentServiceImpl implements MaterialPaymentService {

    @Autowired
    MaterialPaymentJpaRepository materialPaymentJpaRepository;

    @Override
    public MaterialPayment create(MaterialPayment materialPayment) {
        return materialPaymentJpaRepository.save(materialPayment);
    }

    @Override
    public List<MaterialPayment> readAll() {
        return materialPaymentJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (materialPaymentJpaRepository.existsById(id)) {
            materialPaymentJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, MaterialPayment materialPayment) {
        if (materialPaymentJpaRepository.existsById(id)) {
            materialPayment.setMaterialPaymentId(id);
            materialPaymentJpaRepository.save(materialPayment);
            return true;
        }
        return false;
    }
}
