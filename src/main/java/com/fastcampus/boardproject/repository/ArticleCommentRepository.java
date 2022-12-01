package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.domain.ArticleComment;
import com.fastcampus.boardproject.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>, // 이 Entity안에 있는 기본 검색기능을 추가해줌
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) { //검색에 대한 세부적인 규칙이 다시 재구성됨
        //우리는 선택적인 filed만 검색 가능하게 하고싶음.
        bindings.excludeUnlistedProperties(true); // listup하지 않은 filed들에 대한 검색을 불가능하게 만든다. 기본이 fault.
        bindings.including(root.content, root.createdAt, root.createdBy); //filter 추가.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //검색 파라미터 하나만 받음 like '%${v}%'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); //검색 파라미터 하나만 받음 like '%${v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); //검색 파라미터 하나만 받음 like '%${v}%'
    }
}
