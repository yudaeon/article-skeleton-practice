package com.example.articlepractice;

import com.example.articlepractice.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping("/articles")
    public ArticleDto create(@RequestBody ArticleDto dto){
        return service.createArticle(dto);
    }

    // GET /articles
    @GetMapping("/articles")
    public List<ArticleDto> realAll(){
        return service.readArticleAll();
    }

    // GET /articles/{id}
    @GetMapping("/articles/{id}")
    public ArticleDto read(@PathVariable("id") Long id){
        return service.readArticle(id);
    }

    // PUT /articles/{id}
    @PutMapping("/articles/{id}")
    public ArticleDto update(@PathVariable("id") Long id, @RequestBody ArticleDto dto){
        return service.updateArticle(id, dto);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/articles/{id}")
    public void delete(@PathVariable("id") Long id){ //삭제는 반환값이 없어도 된다.
        service.deleteArticle(id);
    }
}