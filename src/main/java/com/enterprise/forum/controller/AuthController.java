package com.enterprise.forum.controller;

import com.enterprise.forum.dto.AccountAuthDTO;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private AccountService accountService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {

        this.accountService = accountService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public CommonVO login(@RequestBody AccountAuthDTO param) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    param.getUsername(),
                                    param.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (AuthenticationException e) {
//            e.printStackTrace();
            return CommonVO.error("用户名或密码错误");
        }
        return CommonVO.ok();
    }

    @PostMapping("/register")
    public CommonVO registration(@RequestBody AccountAuthDTO param) {

        try {
            accountService.addAccount(param.toAccount(passwordEncoder));
        } catch (Exception e) {
//            e.printStackTrace();
            return CommonVO.error("this username has been registered");
        }

        return CommonVO.ok();
    }

}
