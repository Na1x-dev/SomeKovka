package com.example.demo.models;

import lombok.Data;

@Data
public class PublicOrgUnionMember {
    UnionMember unionMember;
    PublicOrganization publicOrganization;

    public PublicOrgUnionMember(PublicOrganization publicOrganization, UnionMember unionMember){
        this.publicOrganization = publicOrganization;
        this.unionMember = unionMember;
    }

    public PublicOrgUnionMember() {

    }
}
