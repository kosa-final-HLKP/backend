package com.HLKPfinal.service;

import com.HLKPfinal.dto.TimelineDto;
import com.HLKPfinal.entity.Member;
import com.HLKPfinal.entity.Timeline;
import com.HLKPfinal.repository.MemberRepository;
import com.HLKPfinal.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimelineService {

    private final TimelineRepository timelineRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TimelineService(TimelineRepository timelineRepository, MemberRepository memberRepository) {
        this.timelineRepository = timelineRepository;
        this.memberRepository = memberRepository;
    }

    public Timeline createTimeline(Long memberId, String content, String category) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid memberId: " + memberId));
        Timeline timeline = new Timeline(member, content, category);
        timeline.setDate(new Date());
        return timelineRepository.save(timeline);
    }

    public List<TimelineDto> getTimelinesByDate(Date date) {
        List<Timeline> timelines = timelineRepository.findByDate(date);
        return timelines.stream().map(Timeline::toDto).collect(Collectors.toList());
    }

}
