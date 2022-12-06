package com.example.demo.models;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
public class ParentChild {
    UnionMember unionMember;
    Child child;

    public ParentChild(UnionMember unionMember, Child child) {
        this.unionMember = unionMember;
        this.child = child;
    }

    public UnionMember getSecondParent() {
        Set<UnionMember> parents = child.getUnionMembers();
        Iterator<UnionMember> iterator = parents.iterator();
        while (iterator.hasNext()){
            UnionMember buff = iterator.next();
            if(!buff.getUnionMemberId().equals(this.unionMember.unionMemberId)){
                return buff;
            }
        }
        return null;
    }

}
