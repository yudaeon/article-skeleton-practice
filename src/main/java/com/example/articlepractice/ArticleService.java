package com.example.articlepractice;

import com.example.articlepractice.dto.ArticleDto;
import com.example.articlepractice.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    //JPA를 통해 자동으로 DB와 연결되는 ArticleRepository이다.
    private final ArticleRepository repository;

    //Article을 만들어서 DB에 저장한 후, 해당되는 Dto를 만들어서 리턴합니다.
    public ArticleDto createArticle(ArticleDto dto) {
        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());

        return ArticleDto.fromEntity(repository.save(newArticle));
    }

    //특정 아이디를 가진 Article을 DB에서 읽어서 Dto로 만들어서 리턴합니다.
    public ArticleDto readArticle(Long id) {
        Optional<ArticleEntity> entity = repository.findById(id);
        if (entity.isPresent()){
           // DTO로 전환후 반환
            return ArticleDto.fromEntity(entity.get());
            //아니라면 404
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //모든 Article을 DB에서 읽어서 Dto로 만들어서 리턴합니다.
    public List<ArticleDto> readArticleAll() {
        List<ArticleDto> articleList = new ArrayList<>();
        for (ArticleEntity entity: repository.findAll()) {
            articleList.add(ArticleDto.fromEntity(entity));
        }
        return articleList;
    }

    //특정 Article을 dto에 적힌 내용대로 수정한 후 db에 반영합니다. 그 후, 수정된 article의 내용을 dto로 만들어서 리턴합니다.
    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> entityList = repository.findById(id);
        if (entityList.isPresent()) {
            ArticleEntity entity = entityList.get();
            entity.setTitle(dto.getTitle());
            entity.setWriter(dto.getWriter());
            entity.setContent(dto.getContent());
            repository.save(entity);
            return ArticleDto.fromEntity(entityList.get());
        }else   throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // 특정 id를 가진 Article을 DB에서 삭제합니다.
    public void deleteArticle(Long id) {
        Optional<ArticleEntity> entity = repository.findById(id);
        if (repository.existsById(id)){
            repository.deleteById(id);
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}