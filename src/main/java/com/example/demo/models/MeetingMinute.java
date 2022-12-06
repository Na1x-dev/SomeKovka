package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    @OneToMany(mappedBy = "meetingMinute")
    @ToString.Exclude
    List<Application> applications;

    public MeetingMinute() {
        meetingMinuteNumber = 0;
        meetingMinuteDate = new Date();
        meetingMinuteTheme = "";
    }

    public String formatDateForChange() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(meetingMinuteDate);
    }

    public String getDateInNormalFormat() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(getMeetingMinuteDate());
    }
}
