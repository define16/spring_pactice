package com.example.jqa.member.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
@Schema(description = "Member 스키마")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "멤버 번호")
    private Long memberNo;
    @Schema(description = "아이디")
    private String id;
    @Schema(description = "멤버 이름")
    private String name;
    @Embedded
    @Schema(description = "비밀번호")
    private Password password;
    @Embedded
    @Schema(description = "멤버 주소")
    private Address address;
    @Embedded
    @Schema(description = "멤버 이메일")
    private Email email;

    @Schema(description = "출생 연도")
    private String birthYear;
    @Schema(description = "휴대전화번호")
    private String phoneNumber;
    @Schema(description = "네이버 로그인API 동일인 식별정보")
    private String naverId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;


    @Builder
    public Member(String id, String name, Password password, Address address, Email email, String birthYear, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.birthYear = birthYear;
        this.phoneNumber = phoneNumber;
    }
}
