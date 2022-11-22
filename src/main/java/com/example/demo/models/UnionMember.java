package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "union_members")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class UnionMember {
    @Id
    @Column(name = "union_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long unionMemberId;

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

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false, referencedColumnName = "position_id")
    @NonNull
    Position position;

    @JsonIgnore
    @OneToMany(mappedBy = "unionMember", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Application> applications;

    @JsonIgnore
    @OneToMany(mappedBy = "unionMember", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<PhoneNumber> phoneNumbers;

    @ManyToMany
    @NonNull
    @ToString.Exclude
    Set<Child> children;

}
