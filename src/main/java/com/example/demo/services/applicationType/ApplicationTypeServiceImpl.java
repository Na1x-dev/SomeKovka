package com.example.demo.services.applicationType;

import com.example.demo.models.ApplicationType;
import com.example.demo.repositories.applicationType.ApplicationTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationTypeServiceImpl implements ApplicationTypeService {

    @Autowired
    ApplicationTypeJpaRepository applicationTypeJpaRepository;

    @Override
    public ApplicationType create(ApplicationType applicationType) {
   
        return applicationTypeJpaRepository.save(applicationType);
    }

    @Override
    public List<ApplicationType> readAll() {
        return applicationTypeJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (applicationTypeJpaRepository.existsById(id)) {
            applicationTypeJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, ApplicationType applicationType) {
        if (applicationTypeJpaRepository.existsById(id)) {
            applicationType.setApplicationTypeId(id);
            applicationTypeJpaRepository.save(applicationType);
            return true;
        }
        return false;
    }

}
