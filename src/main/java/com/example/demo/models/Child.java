package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Data
@Table(name = "children")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Child {
    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long childId;

    @NonNull
    @Column(name = "surname")
    String surname;

    @Column(name = "name")
    @NonNull
    String name;

    @Column(name = "patronymic")
    @NonNull
    String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthdate")
    @NonNull
    Date birthdate;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false, referencedColumnName = "gender_id")
    @NonNull
    Gender gender;

//        @ManyToMany(mappedBy = "children")
//    @NonNull
//    Set<UnionMember> unionMembers;

//    @ManyToMany
//    @NonNull
//    @ToString.Exclude
//    Set<UnionMember> unionMembers;

    @ManyToMany
    @JoinTable(
            name = "union_members_children",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "union_member_id"))
    Set<UnionMember> unionMembers;

    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(birthdate);
    }

    public String formatDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(birthdate);
    }

    public Long getUnionMemberId(){
        Iterator<UnionMember> unionMemberIterator = unionMembers.iterator();
        return unionMemberIterator.next().unionMemberId;
    }

    public Child() {
        surname = "";
        name = "";
        patronymic = "";
        birthdate = new Date();
        gender = new Gender();
        unionMembers = new HashSet<>();
        unionMembers.add(new UnionMember());
        unionMembers.add(new UnionMember());
    }

    @Override
    public String toString() {
        return "Child{" +
                "childId=" + childId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", parents='" + unionMembers + '\'' +
                '}';
    }

}
