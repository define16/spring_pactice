package com.web.domain.lombok;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

@ToString
@RequiredArgsConstructor
public class Member2 {
    private final String userId;
    private final String password;
    private String userName;

//    @RequiredArgsConstructor
//    public Member2(String userId, String password) {
//        super();
//        this.userId = userId;
//        this.password = password;
//    }
}
