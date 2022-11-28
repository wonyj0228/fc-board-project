package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.config.JpaConfig;
import com.fastcampus.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//@ActiveProfiles("testdb") //기본설정으로도 잘 돌아감.
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //기본설정으로도 잘 돌아가서 뺌.
@DisplayName("JPA연결 테스트")
@Import(JpaConfig.class) //JpaConfig에서 auditing 설정을 해둔 것 (@Configuration)을 import해오기 위함
@DataJpaTest //JPA관련 설정만 테스트함. 실제 DB에 반영하지않고 실행할 수 있음. 자동으로 클래스내부 메소드들에 @Transaction 걸려있음.
// test 돌릴 때 transation은 기본적으로 rollback이 설정되어있음.
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select Test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        //Given

        //When
        List<Article> articles = articleRepository.findAll();

        //Then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("Insert Test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        //Given
        long previousCount = articleRepository.count();

        //When
        Article article = Article.of("new article", "new content", "#spring");
        Article savedArticle = articleRepository.save(article);

        //Then
        assertThat(articleRepository.count())
                .isEqualTo(previousCount + 1);
    }

    @DisplayName("Update Test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        //java 기본형 int라서 long은 뒤에 L붙여줘야함
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //When
        Article savedArticle = articleRepository.saveAndFlush(article);
        //rollback 때문에 update에서 영속성 컨텍스트로부터 가져온 데이터를 그냥 save
        //이제 쿼리에서 update 볼 수 있지만 실제 데이터베이스에는 반영되지 않음. test라서.

        //Then
        assertThat(savedArticle)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
        //변경된 savedArticle entity의 filed hashtag의 value가 updatedHashtag인지 확인
        // hasFieldOrPropertyWithValue = assertJ에서 제공하는 테스트도구
    }

    @DisplayName("Delete Test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        Long previousArticleCount = articleRepository.count(); //삭제전 게시글 수
        Long previousArticleCommentCount = articleCommentRepository.count(); //삭제전 댓글 수

        int deletedCommentsSize = article.getArticleComments().size(); //삭제될 게시글의 댓글 수

        //When
        articleRepository.delete(article);

        //Then
        assertThat(articleRepository.count())
                .isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count())
                .isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }
}
