package com.HLKPfinal.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id @GeneratedValue
    @Column(name = "diaryfile_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String fileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    @Builder
    public File(Member member, String fileName, String filePath) {
        this.member = member;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}