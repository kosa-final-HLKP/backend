package com.HLKPfinal.dto;

import com.HLKPfinal.entity.File;
import com.HLKPfinal.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoListDto {
//    private String file;
//
//    public File toEntity(Member member) {
//        // 이 메서드는 'file' 필드만 가지고 있기 때문에, 'File' 엔티티의 일부 필드만 설정할 수 있습니다.
//        File fileEntity = new File();
//        fileEntity.setFileName(file);
//        fileEntity.setMember(member);
//        return fileEntity;
//    }

    private String fileName;
    private String filePath;

    public File toEntity(Member member) {
        File fileEntity = new File();
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(filePath);
        fileEntity.setMember(member);
        return fileEntity;
    }

}