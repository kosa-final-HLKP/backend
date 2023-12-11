package com.HLKPfinal.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Diary extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    public Diary(String title, String content, File file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }

}