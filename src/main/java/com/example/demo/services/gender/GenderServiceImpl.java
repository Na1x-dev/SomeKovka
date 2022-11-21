package com.example.demo.services.gender;

import com.example.demo.models.Child;
import com.example.demo.models.Gender;
import com.example.demo.repositories.gender.GenderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    GenderJpaRepository genderJpaRepository;

    @Override
    public Gender create(Gender gender) {
        return genderJpaRepository.save(gender);
    }

    @Override
    public List<Gender> readAll() {
        return genderJpaRepository.findAll();
    }


}
