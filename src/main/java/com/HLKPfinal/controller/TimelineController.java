package com.HLKPfinal.controller;

import com.HLKPfinal.dto.TimelineDto;
import com.HLKPfinal.entity.Timeline;
import com.HLKPfinal.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

//    @GetMapping("/timelines")
//    public List<TimelineDto> getTimelinesByDate(@RequestParam String date) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsedDate = formatter.parse(date);
//        return timelineService.getTimelinesByDate(parsedDate);
//    }

    @GetMapping("/timelines")
    public List<TimelineDto> getTimelinesByDate(@RequestParam String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(dateTime, formatter).atStartOfDay();  // 해당 날짜의 시작 시간
        LocalDateTime endDateTime = LocalDate.parse(dateTime, formatter).plusDays(1).atStartOfDay();  // 해당 날짜의 다음 날 시작 시간
        return timelineService.getTimelinesByDateTimeRange(startDateTime, endDateTime);  // 해당 날짜 범위에 있는 타임라인을 가져옵니다.
    }


}