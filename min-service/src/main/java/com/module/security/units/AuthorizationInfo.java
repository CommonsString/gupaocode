package com.module.security.units;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;


@Getter
@AllArgsConstructor
public class AuthorizationInfo implements Serializable {

    private final String token;

    private final JwtUser jUser;

}
