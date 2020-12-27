package com.example.ex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


// 스프링 부트의 자동 설정, Bean 읽기/생성 자동 설정
// 이 어노테이션이 있는 위치로부터 설정을 읽기때문에 항상 프로젝트 최상단에 위치해야함
@SpringBootApplication
public class Ex1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex1Application.class, args);
	}

	@GetMapping
	private String helloWorld(){
		return "Hello World 실행 완료";
	}

}