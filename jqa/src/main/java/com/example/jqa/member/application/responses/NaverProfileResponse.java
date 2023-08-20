package com.example.jqa.member.application.responses;

import com.example.jqa.member.domain.naver.NaverProfile;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NaverProfileResponse {
    private String resultcode;
    private String message;
    private NaverProfile response;

    public NaverProfileResponse() {
    }

    public String getResultcode() {
        return resultcode;
    }

    public NaverProfile getResponse() {
        return response;
    }

    public void setResponse(NaverProfile response) {
        this.response = response;
    }
}
