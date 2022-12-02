package com.example.demo.repositories.publicOrganization;

import com.example.demo.models.MeetingMinute;
import com.example.demo.models.PublicOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicOrganizationJpaRepository extends JpaRepository<PublicOrganization, Long> {

    PublicOrganization getByPublicOrganizationId(Long id);
}