package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "phone_numbers")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class PhoneNumber {
    @Id
    @Column(name = "phone_number_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long phoneNumberId;

    @ManyToOne
    @JoinColumn(name = "union_member_id", nullable = false, referencedColumnName = "union_member_id")
    @NonNull
    UnionMember unionMember;

    @Column(name = "phone_number")
    @NonNull
    String phoneNumber;

    public PhoneNumber() {
    }
}
