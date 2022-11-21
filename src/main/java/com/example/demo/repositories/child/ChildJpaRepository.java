package com.example.demo.repositories.child;

import com.example.demo.models.Child;
import com.example.demo.models.PublicOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildJpaRepository extends JpaRepository<Child, Long> {

}