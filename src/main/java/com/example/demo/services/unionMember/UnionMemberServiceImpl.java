package com.example.demo.services.unionMember;

import com.example.demo.models.UnionMember;
import com.example.demo.repositories.unionMember.UnionMemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnionMemberServiceImpl implements UnionMemberService {

    @Autowired
    UnionMemberJpaRepository supplyRepository;

    @Override
    public UnionMember create(UnionMember unionMember) {
        return supplyRepository.save(unionMember);
    }

    @Override
    public List<UnionMember> readAll() {
        return supplyRepository.findAll();
    }
}
