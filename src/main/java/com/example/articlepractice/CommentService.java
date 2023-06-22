package com.example.articlepractice;

import com.example.articlepractice.dto.CommentDto;
import com.example.articlepractice.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 특정 id를 가진 Article에 CommentDto에 담긴 내용을 토대로 Comment를 생성해주는 메소드입니다.
    public CommentDto createComment(Long articleId, CommentDto dto) {
        // articleId를 ID로 가진 ArticleEntity 가 존재 하는지?
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(dto.getWriter());
        newComment.setContent(dto.getContent());
        newComment.setArticleId(articleId);
        newComment = commentRepository.save(newComment);
        return CommentDto.fromEntity(newComment);
    }
}