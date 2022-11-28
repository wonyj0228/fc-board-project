package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//command + shift + t = new test
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {
}