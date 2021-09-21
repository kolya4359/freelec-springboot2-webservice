package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    /* - 웹 Api를 테스트 할 때 사용한다.
    - 스프링 MVC 테스트의 시작점
    - 이 클래스를 통해 HTTP, GET, POST 등에 대한 API 테스트를 할 수 있다. */

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        /* .param
        - API테스트할 때 사용될 요청 파라미터를 설정한다.
        - 값은 String만 허용
        - 숫자/날짜 등의 데이토도 등록할 때는 문자열로 내려보내야 한다.
        jsonPath()
        .andExpected(jsonPath("$.name", is(name)))
        - JSON 응답값을 필드별로 검증할 수 있는 메소드
        - $를 기준으로 필드명을 명시한다. */

    }
}