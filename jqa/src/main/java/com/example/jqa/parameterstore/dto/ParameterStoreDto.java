package com.example.jqa.parameterstore.dto;

import com.example.jqa.administrator.domain.Administrator;
import com.example.jqa.administrator.domain.Email;
import com.example.jqa.administrator.domain.Password;
import com.example.jqa.parameterstore.domain.ParameterStore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class ParameterStoreDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Parameter {
        @NotEmpty
        private String parameterType;
        @NotEmpty
        private Map<String, Object> parameterValue;

        @Builder
        public Parameter(String parameterType, Map<String, Object> parameterValue) {
            this.parameterType = parameterType;
            this.parameterValue = parameterValue;
        }

        public ParameterStore toEntity() {
            return ParameterStore.builder()
                    .parameterType(this.parameterType)
                    .parameterValue(this.parameterValue)
                    .build();
        }

    }

}
