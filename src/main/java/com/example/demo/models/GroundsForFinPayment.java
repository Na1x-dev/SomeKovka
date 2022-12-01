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
@Table(name = "grounds_for_fin_payments")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class GroundsForFinPayment {
    @Id
    @Column(name = "ground_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long groundId;

    @Column(name="ground_text")
    @NonNull
    String groundText;

    @JsonIgnore
    @OneToMany(mappedBy = "groundsForFinPayment")
    @ToString.Exclude
    List<MaterialPayment> materialPayments;

    public GroundsForFinPayment() {
    }
}
