package com.fastcampus.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
// 검색기능이 빨라질 수 있도록 index 추가. 제목, 해시태그, 작성자, 작성시간에 따른 검색이 진행될 것이기 때문에.
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql 이기 때문에. auto increment 설정을 위함
    private Long id;

    // 사용자로 하여금 임의로 세팅할 수 없도록 setter는 setter할 필드에서 따로 세팅해줌.
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000)private String content; // 본문
    @Setter private String hashtag; // 해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    //중복을 허용하지 않고 댓글리스트를 보려함
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // 메타데이터
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자

    // 기본생성자. 평소에는 open 하지 않을 것. entity는 private은 사용할 수 없음. 코드 밖에서 new로 생성 못하게.
    protected Article() {}

    // entity 밖에서 내용을 수정할 수 없도록.
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // public static으로 article 생성시 필요한 내용 전달하기 위함.
    public static Article of (String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
//        if (o == null || getClass() != o.getClass()) return false;
//        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
