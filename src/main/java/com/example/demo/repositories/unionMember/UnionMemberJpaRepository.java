package com.example.demo.repositories.unionMember;

import com.example.demo.models.PhoneNumber;
import com.example.demo.models.UnionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionMemberJpaRepository extends JpaRepository<UnionMember, Long> {
}