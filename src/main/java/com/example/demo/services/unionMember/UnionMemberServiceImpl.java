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

    @Override
    public UnionMember readByName(String name) {
        return unionMemberJpaRepository.getByName(name);
    }

    @Override
    public boolean delete(Long id) {
        if (unionMemberJpaRepository.existsById(id)) {
            unionMemberJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<UnionMember> readByPosition(Long positionId) {
        return unionMemberJpaRepository.getByPositionPositionId(positionId);
    }

    @Override
    public List<UnionMember> readBySurname(String surname) {
        return unionMemberJpaRepository.getBySurname(surname);
    }

    @Override
    public List<UnionMember> readPensioners() {
        return unionMemberJpaRepository.readByPositionPositionTitle("Пенсионер");
    }
}
