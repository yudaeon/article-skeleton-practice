package com.example.articlepractice;

import com.example.articlepractice.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController  // @ResponseBody 생략 가능
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환함이 옳다
    public ArticleDto create(@RequestBody ArticleDto dto){
        return service.createArticle(dto);
    }

    // GET /articles
//    @GetMapping
//    public List<ArticleDto> readAll() {
//        return service.readArticleAll();
//    }
    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        return service.readArticlePaged(page, limit);
    }


    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id) {
        return service.readArticle(id);
    }

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto update(
            @PathVariable("id") Long id,  // URL의 ID
            @RequestBody ArticleDto dto   // HTTP Request Body
    ) {
        return service.updateArticle(id, dto);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Long id
    ) {
        service.deleteArticle(id);
    }

//    // GET /articles/page-test
//    @GetMapping("/page-test")
//    public Page<ArticleDto> readPageTest() {
//        return service.readArticlePaged();
//    }
}