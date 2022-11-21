package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "application_types")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class ApplicationType {
    @Id
    @Column(name = "application_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationTypeId;

    @Column(name = "application_type_title")
    @NonNull
    String applicationTypeTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "applicationType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Application> applications;

    public ApplicationType() {
    }
}
