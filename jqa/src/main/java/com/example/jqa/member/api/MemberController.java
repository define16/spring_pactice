package com.example.jqa.member.api;

import com.example.jqa.member.application.responses.NaverProfileResponse;
import com.example.jqa.member.domain.Member;
import com.example.jqa.member.application.services.MemberService;
import com.example.jqa.member.domain.naver.NaverOAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member")
@Tag(name = "Member", description = "템플릿 API Document")
public class MemberController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired  // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    MemberService memberService;

    /**
     * [API]  모든 회원 조회
     *
     * @return ApiResponseWrapper<List < Member>> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Member 전체 불러오기", description = "Member 전체 불러오기", tags = {"View"})
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> member = memberService.findAll();
        return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 한명의 회원 조회
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Member 넘버로 불러오기", description = "Member 넘버로 불러오기", tags = {"View"})
    public ResponseEntity<Member> getMember(@PathVariable("mbrNo") Long mbrNo) {
        Optional<Member> member = memberService.findById(mbrNo);
        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 회원 삭제
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Member 삭제", description = "Member 삭제")
    public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
        memberService.deleteById(mbrNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * [API]  회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, name로 수정함)
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Member 수정", description = "Member 수정")
    public ResponseEntity<Member> updateMember(@PathVariable("mbrNo") Long mbrNo, Member member) {
        memberService.updateById(mbrNo, member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 회원 입력
    /**
     * [API]  회원 입력
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping
    @Operation(summary = "Member 저장", description = "Member 저장")
    public ResponseEntity<Member> save(Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 입력??
    /**
     * [API]  회원 입력??
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @RequestMapping(value="/save", method = RequestMethod.GET)
    @Operation(summary = "Member 저장???", description = "Member 저장??")
    public ResponseEntity<Member> save(HttpServletRequest req, Member member){
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }


    // 회원 입력??
    /**
     * [API] 유저로 부터 네이버 인가 코드를 전송 받는 API
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @RequestMapping("/auth/naver/login/callback")
    public ResponseEntity<Void> naverCallback(String code, String state) throws JsonProcessingException {
        final String CLIENT_ID = "";
        final String CLIENT_SECRET = "";
        final String TOKEN_REQUEST_URL = "";
        final String PROFILE_REQUEST_URL = "";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("code", code);
        params.add("state", state);
        // Parameter로 전달할 속성들 추가

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = makeTokenRequest(params);
        // Http 메시지 생성

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> tokenResponse = rt.exchange(
                TOKEN_REQUEST_URL,
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );
        // TOKEN_REQUEST_URL로 Http 요청 전송
        ObjectMapper objectMapper = new ObjectMapper();
        NaverOAuthToken naverToken = objectMapper.readValue(tokenResponse.getBody(), NaverOAuthToken.class);
        // ObjectMapper를 통해 NaverOAuthToken 객체로 매핑

        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = makeProfileRequest(naverToken);

        ResponseEntity<String> profileResponse = rt.exchange(
                PROFILE_REQUEST_URL,
                HttpMethod.POST,
                naverProfileRequest,
                String.class
        );

        NaverProfileResponse naverProfileResponse = objectMapper.readValue(profileResponse.getBody(), NaverProfileResponse.class);


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    private HttpEntity<MultiValueMap<String, String>> makeTokenRequest(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
        return naverTokenRequest;
    }

    private HttpEntity<MultiValueMap<String, String>> makeProfileRequest(NaverOAuthToken naverToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ naverToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        return naverProfileRequest;
    }

}
