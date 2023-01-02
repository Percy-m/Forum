package com.enterprise.forum.controller;

import com.enterprise.forum.annotation.CurrentAccount;
import com.enterprise.forum.dto.TokenDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.security.JwtTokenProvider;
import com.enterprise.forum.dto.AccountAuthDTO;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private JwtTokenProvider jwtTokenProvider;

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

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public CommonVO login(@Valid @RequestBody AccountAuthDTO param) {

        try {
            UserDetails currentAccount = accountService.loadUserByUsername(param.getUsername());

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    currentAccount,
                                     param.getPassword(),
                                    currentAccount.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);
            return CommonVO.ok(tokenDTO);
        } catch (AuthenticationException e) {
            return CommonVO.unauthorized(e.getMessage());
        }
    }

    @PostMapping("/register")
    public CommonVO register(@Valid @RequestBody AccountAuthDTO param) {

        try {
            accountService.addAccount(param, passwordEncoder::encode);
        } catch (BusinessException e) {
            return CommonVO.error(e.getCode(), e.getMessage());
        }

        return CommonVO.created();
    }

    @GetMapping("/refresh-token")
    public CommonVO refreshToken(ServletRequest request) {

        return CommonVO.ok(request.getAttribute("token"));
    }

    // for authentication test
    @GetMapping("/current")
    public CommonVO currentAccount(@CurrentAccount UserDetails account) {

        return CommonVO.ok(account);
    }

}
