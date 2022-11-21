package com.example.demo.repositories.applicationType;

import com.example.demo.models.ApplicationType;
import com.example.demo.models.UnionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationTypeJpaRepository extends JpaRepository<ApplicationType, Long> {

}