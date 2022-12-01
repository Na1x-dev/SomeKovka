package com.example.demo.services.unionMember;

import com.example.demo.models.UnionMember;

import java.util.List;

public interface UnionMemberService {
    UnionMember create(UnionMember unionMember);

    List<UnionMember> readAll();

    UnionMember readById(Long unionMemberId);


     boolean update(Long id, UnionMember unionMember);

    UnionMember readByName(String name);

    boolean delete(Long id);


}
