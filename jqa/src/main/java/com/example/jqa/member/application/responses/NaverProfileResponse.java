package com.example.jqa.member.application.responses;

import com.example.jqa.member.domain.naver.NaverProfile;

public class NaverProfileResponse {
    private String resultcode;
    private String message;
    private NaverProfile response;

    public NaverProfileResponse() {
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResponse(NaverProfile response) {
        this.response = response;
    }
}
