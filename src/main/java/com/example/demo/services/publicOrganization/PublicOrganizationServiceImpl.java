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

    @Override
    public boolean delete(Long id) {
        if (publicOrganizationJpaRepository.existsById(id)) {
            publicOrganizationJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, PublicOrganization publicOrganization) {
        if (publicOrganizationJpaRepository.existsById(id)) {
            publicOrganization.setPublicOrganizationId(id);
            publicOrganizationJpaRepository.save(publicOrganization);
            return true;
        }
        return false;
    }

    @Override
    public PublicOrganization readById(Long id) {
        return publicOrganizationJpaRepository.getByPublicOrganizationId(id);
    }
}
