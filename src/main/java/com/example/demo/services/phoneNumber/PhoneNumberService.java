package com.example.demo.services.phoneNumber;

import com.example.demo.models.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {
    PhoneNumber create(PhoneNumber phoneNumber);

    List<PhoneNumber> readAll();


     PhoneNumber readById(Long id);
}
