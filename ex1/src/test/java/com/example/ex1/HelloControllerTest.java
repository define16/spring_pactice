package com.example.ex1;

import org.apache.catalina.security.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 스프링 부트 테스트와 JUnit 사이의 연결자 역할
 * 테스트 진행할 떄 JUnit 내장된 실행자외 SpringRunner이라는 스프링 실행자 사용
 */
@RunWith(SpringRunner.class)
/**
 * Web(Spring MVC)에만 집중할 수 있는 어노테이션
 * 선언시 @Controller @ControllerAdvice 사용가능
 * @Service @Component @Repository는 사용이 불가능
 */
@WebMvcTest(
    controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    }
)
public class HelloControllerTest {
    /**
     * MockMvc
     * 웹 API 테스트 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET, POST에 대한 API 테스트 가능
     */
    @Autowired // Java Bean 자동 주입
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello!";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 보냄
                .andExpect(status().isOk()) // mvc.perform의 결과 검증, HTTP Header의 Status 검증, 200이면 OK
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증, Response 본문의 내용 검증, Controller의 리턴값이 hello!면 OK
    }
}
