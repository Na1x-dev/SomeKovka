package com.example.demo.services.meetingMinute;

import com.example.demo.models.MeetingMinute;
import com.example.demo.repositories.meetingMinute.MeetingMinuteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingMinuteServiceImpl implements MeetingMinuteService {

    @Autowired
    MeetingMinuteJpaRepository meetingMinuteJpaRepository;

    @Override
    public MeetingMinute create(MeetingMinute meetingMinute) {
        return meetingMinuteJpaRepository.save(meetingMinute);
    }

    @Override
    public List<MeetingMinute> readAll() {
        return meetingMinuteJpaRepository.findAll();
    }

}
