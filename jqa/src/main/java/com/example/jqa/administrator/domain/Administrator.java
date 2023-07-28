package com.example.jqa.administrator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="administrator")
@Schema(description = "administrator 스키마")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "관리자 번호")
    private Long administratorNo;
    @Schema(description = "아이디")
    private String id;
    @Schema(description = "관리자 이름")
    private String name;
    @Embedded
    @Schema(description = "비밀번호")
    private Password password;
    @Embedded
    @Schema(description = "멤버 이메일")
    private Email email;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;


    @Builder
    public Administrator(String id, String name, Password password, Email email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
