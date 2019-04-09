package com.module.security.units;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

@Data
@ToString
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}


