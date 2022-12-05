package com.example.demo.repositories.meetingMinute;

import com.example.demo.models.GroundsForFinPayment;
import com.example.demo.models.MeetingMinute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingMinuteJpaRepository extends JpaRepository<MeetingMinute, Long> {

    MeetingMinute getByMeetingMinuteNumber(int i);
}