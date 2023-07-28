package com.example.jqa.member.api;

import com.example.jqa.member.domain.Member;
import com.example.jqa.member.application.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
