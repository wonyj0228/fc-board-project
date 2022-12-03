package com.fastcampus.boardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { //Spring Security 관련 설정파일

    // Spring boot Bean 등록방법
    // 1. @component, @Controller, @Repository, @Service 등 필요한 빈을 등록해 @Autowired 시켜서 사용.
    // 2. @Configuration 어노테이션을 사용한 클래스에 메서드로 @Bean을 등록

    // Bean등록은 반드시 메서드로 사용하며. return 객체가 bean으로 사용할 객체임. bean은 name을 지정할 수 있지만 default는 method 이름!
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HttpSecurity = 인증, 인가의 세부적인 기능을 설정할 수 있도록 Api를 제공해주는 클래스.
        // formLogin() = form을 이용한 로그인 방식을 설정. ex) 로그인페이지 Url설정, 로그인 후 다이렉트할 url, 아이디나 패스워드 파라미터설정..
        // logout, rememberme, session manage, authorizeRequest 등..

        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) //auth의 어떤 request든 다 permit 함.
                .formLogin().and()
                .build(); // 이렇게 설정한 http 객체를 build 해서 return 해주겠다.
    }
}
