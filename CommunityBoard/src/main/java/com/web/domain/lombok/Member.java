package com.web.domain.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private String userId;
    private String password;
    private String userName;

    // @NoArgsConstructor
//    public Member(){
//        super();
//    }

    // @AllArgsConstructor
//    public Member(String userId, String password, String userName){
//        super();
//        this.userId = userId;
//        this.password = password;
//        this.userName = userName;
//    }
}
