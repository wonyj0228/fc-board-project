package com.fastcampus.boardproject.Controller;

import com.fastcampus.boardproject.controller.ArticleController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class) //WebMvcTest는 모든 컨트롤러를 불러오는데 해당 컨트롤러만 테스트가능
class ArticleControllerTest {

    private final MockMvc mvc;

    // 생성자주입
    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //Given

        // When&Then
        mvc.perform(MockMvcRequestBuilders.get("/articles")) //해당 url로 get request를 하면
                .andExpect(MockMvcResultMatchers.status().isOk()) //200 상태메세지와
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) //html 컨텐트가 리턴되며 UTF-8 때문에 compatible로.
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles")); //model에 atricles 키로 데이터가 넘어온다.
    }
    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //Given

        // When&Then
        mvc.perform(MockMvcRequestBuilders.get("/articles/1")) //해당 url로 get request를 하면
                .andExpect(MockMvcResultMatchers.status().isOk()) //200 상태메세지와
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) //html 컨텐트가 리턴되며
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article")) //model에 atricle(단건) 키로 데이터가 넘어온다.
                .andExpect(model().attributeExists("articleComments"));
    }
    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        //Given

        // When&Then
        mvc.perform(MockMvcRequestBuilders.get("/articles/search")) //해당 url로 get request를 하면
                .andExpect(MockMvcResultMatchers.status().isOk()) //200 상태메세지와
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) //html 컨텐트가 리턴되며
                .andExpect(model().attributeExists("articles/search"));

    }
    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
        //Given

        // When&Then
        mvc.perform(MockMvcRequestBuilders.get("/articles/search-hashtag")) //해당 url로 get request를 하면
                .andExpect(MockMvcResultMatchers.status().isOk()) //200 상태메세지와
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) //html 컨텐트가 리턴되며
                .andExpect(model().attributeExists("articles/hash-tag"));
    }
}