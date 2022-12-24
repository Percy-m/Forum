package com.enterprise.forum.controller.user;

import com.enterprise.forum.domain.Account;
import com.enterprise.forum.dto.UsernameChangeDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * change username and password
 *
 * @author Jiayi Zhu
 * 2022/12/19
 */
@RestController
@RequestMapping("/api/user")
public class PersonalController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {

        this.accountService = accountService;
    }

    @PutMapping("/username")
    public CommonVO changeUsername(@RequestBody UsernameChangeDTO param,
                                   @AuthenticationPrincipal Account account) {
        try {
            accountService.updateUsername(account.getId(), param.getNewUsername());
        }
        catch (BusinessException e) {
            return CommonVO.error(e.getMessage());
        }
        return CommonVO.ok();
    }
}
