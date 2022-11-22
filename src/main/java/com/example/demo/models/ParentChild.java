package com.example.demo.models;

import lombok.Data;

@Data
public class ParentChild {
    UnionMember unionMember;
    Child child;

public ParentChild(UnionMember unionMember, Child child){
    this.unionMember = unionMember;
    this.child = child;
}
}
