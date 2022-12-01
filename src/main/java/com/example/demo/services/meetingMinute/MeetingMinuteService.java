package com.example.demo.services.meetingMinute;

import com.example.demo.models.MeetingMinute;

import java.util.List;

public interface MeetingMinuteService {
    MeetingMinute create(MeetingMinute meetingMinute);

    List<MeetingMinute> readAll();


    boolean delete(Long id);

    boolean update(Long id, MeetingMinute meetingMinute);
}
