package com.enterprise.forum.controller;

import com.enterprise.forum.dto.RegistrationParam;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@RestController
@RequestMapping("/api")
public class BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public CommonVO home() {

        return CommonVO.ok();
    }

    @PostMapping("/register")
    public CommonVO registration(@RequestBody RegistrationParam param) {

        try {
            accountService.addAccount(param.toAccount(passwordEncoder));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonVO.error("this username has been registered");
        }

        return CommonVO.ok();
    }

}
