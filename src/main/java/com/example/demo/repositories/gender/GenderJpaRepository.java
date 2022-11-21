package com.example.demo.repositories.gender;

import com.example.demo.models.Child;
import com.example.demo.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderJpaRepository extends JpaRepository<Gender, Long> {

}