package com.example.jqa.administrator.dto;

import com.example.jqa.administrator.domain.Email;
import com.example.jqa.administrator.domain.Administrator;
import com.example.jqa.administrator.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class AdministratorDto {

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

        @Builder
        public SignUpReq(Email email, String id, String name, String password) {
            this.email = email;
            this.id = id;
            this.name = name;
            this.password = password;
        }

        public Administrator toEntity() {
            return Administrator.builder()
                    .email(this.email)
                    .id(this.id)
                    .name(this.name)
                    .password(Password.builder().value(this.password).build())
                    .build();
        }

    }

    @Getter
    public static class Res {
        private Long administratorNo;
        private Email email;
        private Password password;
        private String id;
        private String name;

        public Res(Administrator administrator) {
            this.administratorNo = administrator.getAdministratorNo();
            this.email = administrator.getEmail();
            this.id = administrator.getId();
            this.name = administrator.getName();
            this.password = administrator.getPassword();
        }
    }

}
