package com.HLKPfinal.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class File extends BaseEntity{

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

    // setFileName()과 setMember() 메서드 추가
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    // getFileName() 메서드 추가
    public String getFileName() {
        return this.fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}