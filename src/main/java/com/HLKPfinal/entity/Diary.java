package com.HLKPfinal.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 수정됨
    @Column(name="diary_id")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Lob
    @Column(name="content",nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryfile_id")
    private File file;

    public Diary(String title, String content, File file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }

}