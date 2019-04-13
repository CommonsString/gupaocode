package com.controller.security;


import com.module.security.units.AuthorizationInfo;
import com.module.security.units.AuthorizationUser;
import com.module.security.units.JwtUser;
import com.module.security.utils.JwtTokenUtil;
import com.utils.EncryptUtils;
import com.utils.SecurityContextHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 登录授权
 */
@RestController
@RequestMapping("auth")
@Api(tags = {"登录授权"})
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    // 显示注入
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @PostMapping(value = "${jwt.auth.path}")
    @ApiOperation("用户登录")
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authUser) {
        // JwtUser对象
        final JwtUser jUser = (JwtUser) userDetailsService.loadUserByUsername(authUser.getUsername());
System.out.println("psd : " + EncryptUtils.encryptPassword(authUser.getPassword()));
        // 校验密码
        if (!jUser.getPassword().equals(EncryptUtils.encryptPassword(authUser.getPassword()))) {
            throw new AccountExpiredException("密码错误！");
        } else if (!jUser.isEnabled()) {
            throw new AccountExpiredException("账号已冻结，请联系管理员！");
        }
        // 创建token令牌
        final String token = jwtTokenUtil.generateToken(jUser);
// token test
        System.out.println("token : " + token);
        return ResponseEntity.ok(new AuthorizationInfo(token, jUser));
    }



    @GetMapping("${jwt.auth.account}")
    @ApiOperation("获取用户信息")
    public ResponseEntity geUserInformation() {
        // 获取当前用户信息
        UserDetails current = SecurityContextHandler.get();
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(current.getUsername());
        if(jwtUser == null) {
            throw new AccountExpiredException("无效用户信息！");
        }
        return ResponseEntity.ok(jwtUser);
    }

}
