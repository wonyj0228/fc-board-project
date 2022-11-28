package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

//command + shift + t = new test
public interface ArticleRepository extends JpaRepository<Article, Long> {
}