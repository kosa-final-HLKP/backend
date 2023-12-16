package com.HLKPfinal.entity;

import com.HLKPfinal.dto.TimelineDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Timeline extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "timeline_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "diary_id")
//    private Diary diary; // 'Diary' 엔티티와의 관계 정의

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Timeline(Member member, String content, String category) {
        this.member = member;
        this.content = content;
        this.category = category;
        this.date = new Date();
    }

    public TimelineDto toDto() {
        TimelineDto dto = new TimelineDto();
        dto.setMemberId(member.getId());
        dto.setContent(content);
        dto.setCategory(category);
        dto.setRole(member.getAuthority().getAuthorityStatus());
        dto.setDate(date);
        return dto;
    }
}
