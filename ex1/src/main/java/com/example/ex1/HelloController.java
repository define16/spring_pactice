package com.example.ex1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// JSON을 반환하는 컨트롤러로 설정함
// 예전에는 @ResponseBody를 각 메소드마다 설정했는데 이제는 한번에 가능하다.
@RestController
public class HelloController {

    // HTTP GET 요청을 받을 수 있는 API를 만들어줌
    @GetMapping("/hello")
    public String Hello(){
        return "hello!";
    }
}
