package com.example.demo.services.publicOrganization;

import com.example.demo.models.PublicOrganization;

import java.util.List;

public interface PublicOrganizationService {
    PublicOrganization create(PublicOrganization publicOrganization);

    List<PublicOrganization> readAll();


}
