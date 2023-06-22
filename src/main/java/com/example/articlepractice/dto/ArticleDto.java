package com.example.articlepractice.dto;

import com.example.articlepractice.entity.ArticleEntity;
import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String writer;
    private String title;
    private String content;

    public static ArticleDto fromEntity(ArticleEntity entity) {
        ArticleDto dto = new ArticleDto();
        dto.setId(entity.getId());
        dto.setWriter(entity.getWriter());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }
}
