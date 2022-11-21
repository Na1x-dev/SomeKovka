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
@Table(name = "material_payments")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class MaterialPayment {
    @Id
    @Column(name="material_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long materialPaymentId;

    @NonNull
    @Column(name="payment_amount")
    Double paymentAmount;

    @NonNull
    @Column(name = "publisher_title")
    String publisherTitle;

    @ManyToOne
    @JoinColumn(name = "ground_id", nullable = false, referencedColumnName = "ground_id")
    @NonNull
    GroundsForFinPayment groundsForFinPayment;

    @JsonIgnore
    @OneToMany(mappedBy = "materialPayment", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Application> applications;

    public MaterialPayment() {
    }
}
