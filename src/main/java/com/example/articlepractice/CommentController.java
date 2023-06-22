package com.example.articlepractice;

import com.example.articlepractice.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    // POST /articles/{articleId}/comments
    // Post 메소드로 /articles/{articleId}/comments에 요청을 보내면 해당하는 CommentDto를 받아서 comment를 저장한 후, 해당되는 dto를 응답하는 메소드입니다.
    @PostMapping("/articles/{articleId}/comments")
    public CommentDto create(@PathVariable("articleId") Long articleId, @RequestBody CommentDto dto) {
        return service.createComment(articleId, dto);
    }
}