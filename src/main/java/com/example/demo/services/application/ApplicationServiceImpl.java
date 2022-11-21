package com.example.demo.services.application;

import com.example.demo.models.Application;
import com.example.demo.repositories.application.ApplicationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationJpaRepository applicationJpaRepository;

    @Override
    public Application create(Application application) {
        return applicationJpaRepository.save(application);
    }

    @Override
    public List<Application> readAll() {
        return applicationJpaRepository.findAll();
    }
    
}
