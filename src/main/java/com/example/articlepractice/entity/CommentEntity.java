package com.example.articlepractice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long articleId; // Article과 연결되는 상태를 표시하기 위한 Long 변수입니다.
    private String writer;
    private String content;
}