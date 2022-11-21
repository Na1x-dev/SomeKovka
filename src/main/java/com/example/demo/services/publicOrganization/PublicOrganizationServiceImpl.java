package com.example.demo.services.publicOrganization;

import com.example.demo.models.PublicOrganization;
import com.example.demo.repositories.publicOrganization.PublicOrganizationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicOrganizationServiceImpl implements PublicOrganizationService {

    @Autowired
    PublicOrganizationJpaRepository publicOrganizationJpaRepository;

    @Override
    public PublicOrganization create(PublicOrganization publicOrganization) {
        return publicOrganizationJpaRepository.save(publicOrganization);
    }

    @Override
    public List<PublicOrganization> readAll() {
        return publicOrganizationJpaRepository.findAll();
    }

}
