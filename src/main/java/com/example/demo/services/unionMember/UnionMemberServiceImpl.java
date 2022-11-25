package com.example.demo.services.unionMember;

import com.example.demo.models.UnionMember;
import com.example.demo.repositories.unionMember.UnionMemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnionMemberServiceImpl implements UnionMemberService {

    @Autowired
    UnionMemberJpaRepository unionMemberJpaRepository;

    @Override
    public UnionMember create(UnionMember unionMember) {
        return unionMemberJpaRepository.save(unionMember);
    }

    @Override
    public List<UnionMember> readAll() {
        return unionMemberJpaRepository.findAll();
    }

    @Override
    public UnionMember readById(Long unionMemberId) {
        return unionMemberJpaRepository.getByUnionMemberId(unionMemberId);
    }

    @Override
    public boolean update(Long id, UnionMember unionMember) {
        if (unionMemberJpaRepository.existsById(id)) {
            unionMember.setUnionMemberId(id);
            unionMemberJpaRepository.save(unionMember);
            return true;
        }
        return false;
    }
}
