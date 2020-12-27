package com.example.ex1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter // 선언된 모든 필드의 get 메소드 생성
@Setter // 선언된 모든 필드의 set 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성(final 없는 필드는 생성자에 포함 X)
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
