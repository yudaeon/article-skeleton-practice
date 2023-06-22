package com.example.articlepractice.dto;

import com.example.articlepractice.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long articleId;
    private String writer;
    private String content;

    // 정적 팩토리 패턴
    public static CommentDto fromEntity(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setWriter(entity.getWriter());
        dto.setContent(entity.getContent());
        return dto;
    }
}