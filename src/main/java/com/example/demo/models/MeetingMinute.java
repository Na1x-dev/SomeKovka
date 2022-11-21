package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "meeting_minutes")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class MeetingMinute {
    @Id
    @Column(name = "meeting_minute_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long meetingMinuteId;

    @Column(name = "meeting_minute_number")
    @NonNull
    Integer meetingMinuteNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "meeting_minute_date")
    @NonNull
    Date meetingMinuteDate;

    @Column(name = "meeting_minute_theme")
    @NonNull
    String meetingMinuteTheme;

    @JsonIgnore
    @OneToMany(mappedBy = "meetingMinute", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Application> applications;

    public MeetingMinute() {
    }
}
