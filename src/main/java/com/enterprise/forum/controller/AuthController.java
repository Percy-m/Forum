package com.enterprise.forum.controller;

import com.enterprise.forum.security.JwtTokenProvider;
import com.enterprise.forum.dto.AccountAuthDTO;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
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
    public CommonVO login(@RequestBody AccountAuthDTO param) {

        try {
            UserDetails currentAccount = accountService.loadUserByUsername(param.getUsername());

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    currentAccount,
                                     param.getPassword(),
                                    currentAccount.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            return CommonVO.success(token);
        } catch (AuthenticationException e) {
            return CommonVO.error(e.getMessage());
        }
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

    // for authentication test
    @GetMapping("/cu")
    public CommonVO currentUser() {

        Object o =
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonVO.success(o);
    }

}
