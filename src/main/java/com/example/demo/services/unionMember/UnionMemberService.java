package com.example.demo.services.unionMember;

import com.example.demo.models.UnionMember;

import java.util.List;

public interface UnionMemberService {
    UnionMember create(UnionMember unionMember);

    List<UnionMember> readAll();

}
