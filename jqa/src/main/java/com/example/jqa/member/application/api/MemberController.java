package com.example.jqa.member.application.api;

import com.example.jqa.member.application.responses.NaverProfileResponse;
import com.example.jqa.member.domain.Email;
import com.example.jqa.member.domain.Member;
import com.example.jqa.member.application.services.MemberService;
import com.example.jqa.member.domain.naver.NaverOAuthToken;
import com.example.jqa.parameterstore.application.service.ParameterStoreService;
import com.example.jqa.parameterstore.domain.ParameterStore;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member")
@Tag(name = "Member", description = "템플릿 API Document")
public class MemberController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired  // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    private MemberService memberService;
    @Autowired
    private ParameterStoreService parameterStoreService;

    private Map<String, Object> getNaverApiKey(){
        try {
            ParameterStore paramterStore = parameterStoreService.findByParameterType("naver");
            return paramterStore.getParameterValue();
        } catch (Exception e) {
            return new HashMap<String, Object>();
        }
    }


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


    /**
     * [API] 유저로 부터 네이버 인가 코드를 전송 받는 API
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    @RequestMapping("/auth/naver/login/callback")
    @Operation(summary = "유저로 부터 네이버 인가 코드를 전송 받는 API", description = "유저로 부터 네이버 인가 코드를 전송 받는 API")
    public ResponseEntity<Void> naverCallback(String code, String state) throws JsonProcessingException {
        Map<String, Object> naverApiKey = getNaverApiKey();
        if (naverApiKey.isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        String clientId = (String) naverApiKey.get("client_id");
        String clientSecret = (String) naverApiKey.get("client_secret");
        final String TOKEN_REQUEST_URL = "https://nid.naver.com/oauth2.0/token";
        final String PROFILE_REQUEST_URL = "https://openapi.naver.com/v1/nid/me";
        final String PROFILE_VERIFY_URL = "https://openapi.naver.com/v1/nid/verify";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = makeTokenRequest(params);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> tokenResponse = rt.exchange(
                TOKEN_REQUEST_URL,
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(tokenResponse.getBody());
        NaverOAuthToken naverToken = objectMapper.readValue(tokenResponse.getBody(), NaverOAuthToken.class);


        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = makeProfileRequest(naverToken);
        ResponseEntity<String> verifyResponse = rt.exchange(
                PROFILE_VERIFY_URL,
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );
        System.out.println(verifyResponse.getBody());


        ResponseEntity<String> profileResponse = rt.exchange(
                PROFILE_REQUEST_URL,
                HttpMethod.POST,
                naverProfileRequest,
                String.class
        );
        System.out.println(profileResponse.getBody());
        NaverProfileResponse naverProfileResponse = objectMapper.readValue(profileResponse.getBody(), NaverProfileResponse.class);

        String email = naverProfileResponse.getResponse().getEmail();
        Optional<Member> member = memberService.findByEmail(email);
        if (!member.isPresent()){
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
        if (member.get().getNaverId() == null) {
            member.get().setNaverId(naverProfileResponse.getResponse().getId());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * [API] 네이버 토근 만드는 API
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    private HttpEntity<MultiValueMap<String, String>> makeTokenRequest(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
        return naverTokenRequest;
    }

    /**
     * [API] 네이버 프로파일 만드는 API
     *
     * @return ApiResponseWrapper< Member> : 응답 결과 및 응답 코드 반환
     */
    private HttpEntity<MultiValueMap<String, String>> makeProfileRequest(NaverOAuthToken naverToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ naverToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        return naverProfileRequest;
    }

    private void aa() {

    }
}
