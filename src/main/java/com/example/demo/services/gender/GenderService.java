package com.example.demo.services.gender;

import com.example.demo.models.Child;
import com.example.demo.models.Gender;

import java.util.List;

public interface GenderService {
    Gender create(Gender gender);

    List<Gender> readAll();


    Gender readByGenderTitle(String genderTitle);

    Gender readById(Long genderId);
}
