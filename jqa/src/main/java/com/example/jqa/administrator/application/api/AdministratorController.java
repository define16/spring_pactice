package com.example.jqa.administrator.application.api;

import com.example.jqa.administrator.application.service.AdministratorService;
import com.example.jqa.administrator.domain.Administrator;
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
@RequestMapping(value = "/api/v1/administrator")
@Tag(name = "Administrator", description = "관리자 설정 API Document")
public class AdministratorController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired  // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    AdministratorService administratorService;

    /**
     * [API]  모든 회원 조회
     *
     * @return ApiResponseWrapper<List < administrator>> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "administrator 전체 불러오기", description = "administrator 전체 불러오기", tags = {"View"})
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        List<Administrator> administrator = administratorService.findAll();
        return new ResponseEntity<List<Administrator>>(administrator, HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 한명의 회원 조회
     *
     * @return ApiResponseWrapper< administrator> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/{adminNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "administrator 넘버로 불러오기", description = "administrator 넘버로 불러오기", tags = {"View"})
    public ResponseEntity<Administrator> getAdministrator(@PathVariable("adminNo") Long adminNo) {
        Optional<Administrator> administrator = administratorService.findById(adminNo);
        return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 회원 삭제
     *
     * @return ApiResponseWrapper< administrator> : 응답 결과 및 응답 코드 반환
     */
    @DeleteMapping(value = "/{adminNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "administrator 삭제", description = "administrator 삭제")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable("adminNo") Long adminNo) {
        administratorService.deleteById(adminNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * [API]  회원번호로 회원 수정(mbrNo로 회원을 찾아 administrator 객체의 id, name로 수정함)
     *
     * @return ApiResponseWrapper< administrator> : 응답 결과 및 응답 코드 반환
     */
    @PutMapping(value = "/{adminNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "administrator 수정", description = "administrator 수정")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable("adminNo") Long adminNo, Administrator administrator) {
        administratorService.updateById(adminNo, administrator);
        return new ResponseEntity<Administrator>(administrator, HttpStatus.OK);
    }

    // 회원 입력
    /**
     * [API]  회원 입력
     *
     * @return ApiResponseWrapper< administrator> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping
    @Operation(summary = "administrator 저장", description = "administrator 저장")
    public ResponseEntity<Administrator> save(Administrator administrator) {
        return new ResponseEntity<Administrator>(administratorService.save(administrator), HttpStatus.OK);
    }

    // 회원 입력??
    /**
     * [API]  회원 입력??
     *
     * @return ApiResponseWrapper< administrator> : 응답 결과 및 응답 코드 반환
     */
    @RequestMapping(value="/save", method = RequestMethod.GET)
    @Operation(summary = "administrator 저장???", description = "administrator 저장??")
    public ResponseEntity<Administrator> save(HttpServletRequest req, Administrator administrator){
        return new ResponseEntity<Administrator>(administratorService.save(administrator), HttpStatus.OK);
    }

}
