package com.HLKPfinal.dto;

import com.HLKPfinal.entity.Diary;
import com.HLKPfinal.entity.File;
import com.HLKPfinal.entity.Member;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class DiaryDto {
    private Long id;
    private String title;
    private String content;
    private VideoListDto videoListDto; // 필드 추가
    @Getter
    private MultipartFile file;

    @Value("${video.upload.location}")
    private String dir;

    public Diary toEntity(Member member) {
        File file = null;
        if (this.file != null && !this.file.isEmpty()) {
            String fileName = StringUtils.cleanPath(this.file.getOriginalFilename());
            String filePath = dir; // 파일이 저장될 경로
            file = new File(member, fileName, filePath);
        }
        return new Diary(title, content, file);
    }

    public static DiaryDto fromEntity(Diary diary) {
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setId(diary.getId());
        diaryDto.setTitle(diary.getTitle());
        diaryDto.setContent(diary.getContent());
        if (diary.getFile() != null) {
            VideoListDto videoListDto = new VideoListDto(diary.getFile().getFileName(), diary.getFile().getFilePath());
            diaryDto.setVideoListDto(videoListDto);
        }
        return diaryDto;
    }

    // setId 메서드 추가
    public void setId(Long id) {
        this.id = id;
    }

    // setTitle 메서드 추가
    public void setTitle(String title) {
        this.title = title;
    }

    // setContent 메서드 추가
    public void setContent(String content) {
        this.content = content;
    }

    // setVideoListDto 메서드 추가
    public void setVideoListDto(VideoListDto videoListDto) {
        this.videoListDto = videoListDto;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    // 기타 getters, setters, etc.
}