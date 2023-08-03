package com.example.jqa.parameterstore.domain;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDef(name = "jsonb", typeClass = JsonStringType.class) // Entity class 상단에 설정
@Entity(name="parameter_store")
@Schema(description = "ParameterStore 스키마")
public class ParameterStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "파라메터 번호")
    private Long parameterStoreNo;

    @Schema(description = "파라메터 타입")
    private String parameterType;


    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private Map<String, Object> parameterValue;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at", updatable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;


    @Builder
    public ParameterStore(String parameterType, Map<String, Object> parameterValue) {
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
    }

}