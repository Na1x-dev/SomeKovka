package com.example.demo.services.applicationType;

import com.example.demo.models.ApplicationType;

import java.util.List;

public interface ApplicationTypeService {
    ApplicationType create(ApplicationType applicationType);

    List<ApplicationType> readAll();


}
