package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "applications")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Application {
    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "register_date")
    @NonNull
    Date registerDate;

    @ManyToOne
    @JoinColumn(name = "union_member_id", nullable = false, referencedColumnName = "union_member_id")
    @NonNull
    UnionMember unionMember;

    @ManyToOne
    @JoinColumn(name = "application_type_id", nullable = false, referencedColumnName = "application_type_id")
    @NonNull
    ApplicationType applicationType;

    @ManyToOne
    @JoinColumn(name = "material_payment_id", nullable = false, referencedColumnName = "material_payment_id")
    @NonNull
    MaterialPayment materialPayment;

    @ManyToOne
    @JoinColumn(name = "meeting_minute_id", nullable = false, referencedColumnName = "meeting_minute_id")
    @NonNull
    MeetingMinute meetingMinute;

    public Application() {
        registerDate = new Date();

    }

    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(registerDate);
    }
}
