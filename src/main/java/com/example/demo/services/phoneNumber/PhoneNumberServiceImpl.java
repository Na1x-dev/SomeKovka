package com.example.demo.services.phoneNumber;

import com.example.demo.models.PhoneNumber;
import com.example.demo.repositories.phoneNumber.PhoneNumberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    PhoneNumberJpaRepository phoneNumberJpaRepository;

    @Override
    public PhoneNumber create(PhoneNumber phoneNumber) {
        return phoneNumberJpaRepository.save(phoneNumber);
    }

    @Override
    public List<PhoneNumber> readAll() {
        return phoneNumberJpaRepository.findAll();
    }

    @Override
    public PhoneNumber readById(Long id) {
        return phoneNumberJpaRepository.getByPhoneNumberId(id);
    }


}
