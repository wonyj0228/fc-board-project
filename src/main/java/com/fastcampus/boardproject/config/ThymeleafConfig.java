package com.fastcampus.boardproject.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration // 설정파일. bean을 등록할 수 있고 properties를 관리할 수 있음.
public class ThymeleafConfig {
    @Bean //thymeleafTemplateResolver를 bean으로 등록.
    public SpringResourceTemplateResolver thymeleafTemplateResolver( //SpringResourceTemplateResolver = 스프링에서 제공하는 템플릿 resolver
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        // 기본 TemplateResolver에 DecoupledLogic 사용 관련 세팅만 추가해줌.
        return defaultTemplateResolver;
    }


    @RequiredArgsConstructor
    @Getter
    @ConstructorBinding // 생성자바인딩. Thymeleaf3Properties 가 생성될 대 decoupledLogic 관련 부분을 반드시 바인딩해서 가져올 수 있게.
    @ConfigurationProperties("spring.thymeleaf3") // application.yaml파일에서 설정해줄 수 있도록.
    public static class Thymeleaf3Properties {
        /**
         * Use Thymeleaf 3 Decoupled Logic
         */
        private final boolean decoupledLogic;
    }
}
