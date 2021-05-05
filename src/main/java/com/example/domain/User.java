package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@Validated
@Component
public class User {
    private String userName;
    private String password;
    @Email(message = "邮箱不合法！")
    private String email;
}
