package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "positions")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long positionId;

    @Column(name = "position_title")
    @NonNull
    String positionTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<UnionMember> unionMembers;

    public Position(){
    }
}
