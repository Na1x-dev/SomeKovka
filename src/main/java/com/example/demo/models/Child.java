package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    public Child() {
    }
}
