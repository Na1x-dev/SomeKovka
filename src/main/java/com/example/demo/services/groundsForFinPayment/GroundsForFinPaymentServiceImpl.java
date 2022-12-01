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

    @Override
    public GroundsForFinPayment readById(Long groundId) {
        return groundsForFinPaymentJpaRepository.getByGroundId(groundId);
    }

    @Override
    public boolean delete(Long id) {
        if (groundsForFinPaymentJpaRepository.existsById(id)) {
            groundsForFinPaymentJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, GroundsForFinPayment groundsForFinPayment) {
        if (groundsForFinPaymentJpaRepository.existsById(id)) {
            groundsForFinPayment.setGroundId(id);
            groundsForFinPaymentJpaRepository.save(groundsForFinPayment);
            return true;
        }
        return false;
    }


}
