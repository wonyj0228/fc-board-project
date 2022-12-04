package com.fastcampus.boardproject.Controller;

import com.fastcampus.boardproject.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class)
@WebMvcTest
public class AuthControllerTest {
    private final MockMvc mvc;

    // 생성자주입
    public AuthControllerTest(@Autowired MockMvc mvc) {this.mvc = mvc;}


    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenTryingToLoggingIn_thenReturnsLogInView() throws Exception {
        //Given

        // When&Then
        mvc.perform(MockMvcRequestBuilders.get("/login")) //해당 url로 get request를 하면
                .andExpect(MockMvcResultMatchers.status().isOk()) //200 상태메세지와
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)); //html 컨텐트가 리턴되며 UTF-8 때문에 compatible로.
    }

}
