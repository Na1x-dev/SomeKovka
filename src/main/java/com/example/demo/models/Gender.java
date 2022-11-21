package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "genders")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Gender {
    @Id
    @Column(name = "gender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long genderId;

    @Column(name = "gender_title")
    @NonNull
    String genderTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Child> children;

    @JsonIgnore
    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<UnionMember> unionMembers;

    public Gender() {
    }
}
