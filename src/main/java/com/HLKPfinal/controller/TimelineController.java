package com.HLKPfinal.controller;

import com.HLKPfinal.dto.TimelineDto;
import com.HLKPfinal.entity.Timeline;
import com.HLKPfinal.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class TimelineController {

    private final TimelineService timelineService;

    @Autowired
    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @PostMapping("/timeline")
    public TimelineDto createTimeline(@RequestBody TimelineDto timelineDto) {
        Timeline timeline = timelineService.createTimeline(timelineDto.getMemberId(), timelineDto.getContent(), timelineDto.getCategory());
        return timeline.toDto();
    }

}