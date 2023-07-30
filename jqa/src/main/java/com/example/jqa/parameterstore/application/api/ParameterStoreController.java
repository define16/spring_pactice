package com.example.jqa.parameterstore.application.api;

import com.example.jqa.parameterstore.application.service.ParameterStoreService;
import com.example.jqa.parameterstore.domain.ParameterStore;
import com.example.jqa.parameterstore.dto.ParameterStoreDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/parameter-stores")
@Tag(name = "ParameterStore", description = "템플릿 API Document")
public class ParameterStoreController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired  // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    ParameterStoreService parameterStoreService;

    /**
     * [API]  모든 회원 조회
     *
     * @return ApiResponseWrapper<List<ParameterStore>> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "ParameterStore 전체 불러오기", description = "ParameterStore 전체 불러오기", tags = {"View"})
    public ResponseEntity<List<ParameterStore>> getAllParameterStores() {
        List<ParameterStore> parameterStore = parameterStoreService.findAll();
        return new ResponseEntity<List<ParameterStore>>(parameterStore, HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 한명의 회원 조회
     *
     * @return ApiResponseWrapper<ParameterStore> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping(value = "/{parameterType}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "ParameterStore 타입으로 불러오기", description = "ParameterStore 넘버로 불러오기", tags = {"View"})
    public ResponseEntity<ParameterStore> getParameterStore(@PathVariable("parameterType") String parameterType) {
        Optional<ParameterStore> parameterStore = parameterStoreService.findByParameterType(parameterType);
        return new ResponseEntity<ParameterStore>(parameterStore.get(), HttpStatus.OK);
    }

    /**
     * [API]  회원번호로 회원 수정(mbrNo로 회원을 찾아 ParameterStore 객체의 id, name로 수정함)
     *
     * @return ApiResponseWrapper<ParameterStore> : 응답 결과 및 응답 코드 반환
     */
    @PutMapping(value = "/{parameterType}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "ParameterStore 수정", description = "ParameterStore 수정")
    public ResponseEntity<ParameterStore> updateParameterStore(@PathVariable("parameterType") String parameterType, ParameterStore parameterStore) {
        parameterStoreService.updateByParameterType(parameterType, parameterStore);
        return new ResponseEntity<ParameterStore>(parameterStore, HttpStatus.OK);
    }

    // 회원 입력
    /**
     * [API]  회원 입력
     *
     * @return ApiResponseWrapper<ParameterStore> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "ParameterStore 저장", description = "ParameterStore 저장")
    public ResponseEntity<ParameterStore> save(@RequestBody ParameterStoreDto.Parameter parameter) {
        ParameterStore parameterStore = new ParameterStore(parameter.getParameterType(), parameter.getParameterValue());
        return new ResponseEntity<ParameterStore>(parameterStoreService.save(parameterStore), HttpStatus.OK);
    }
}