package com.fastcampus.boardproject.Controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.DomainEvents;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data Rest - API 테스트")
@Transactional //실 DB에 영향이 되지 않도록 RollBack 기능이 있는 transactional. 반드시 spring 거로.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
    private final MockMvc mvc;

    // 생성자 패턴으로 mockMvc DI
    public DataRestTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 회원 관련 API는 일체 제공하지 않는다.")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenReturnsThrowsException() throws Exception {
        //When&Then
        mvc.perform(MockMvcRequestBuilders.get("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.post("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.put("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.patch("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.delete("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.head("/api/userAccounts")).andExpect(status().isNotFound());
    }

}
