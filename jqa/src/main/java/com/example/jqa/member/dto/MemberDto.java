package com.example.jqa.member.dto;

import com.example.jqa.member.domain.Address;
import com.example.jqa.member.domain.Email;
import com.example.jqa.member.domain.Member;
import com.example.jqa.member.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {

        @Valid
        private Email email;

        @NotEmpty
        private String id;
        @NotEmpty
        private String name;

        private String password;

        @Valid
        private Address address;

        @Builder
        public SignUpReq(Email email, String id, String name, String password, Address address) {
            this.email = email;
            this.id = id;
            this.name = name;
            this.password = password;
            this.address = address;
        }

        public Member toEntity() {
            return Member.builder()
                    .email(this.email)
                    .id(this.id)
                    .name(this.name)
                    .password(Password.builder().value(this.password).build())
                    .address(this.address)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyAccountReq {
        private Address address;

        @Builder
        public MyAccountReq(final Address address) {
            this.address = address;
        }

    }

    @Getter
    public static class Res {
        private Long memberNo;
        private Email email;
        private Password password;
        private String id;
        private String name;
        private Address address;

        public Res(Member member) {
            this.memberNo = member.getMemberNo();
            this.email = member.getEmail();
            this.id = member.getId();
            this.name = member.getName();
            this.address = member.getAddress();
            this.password = member.getPassword();
        }
    }

}
