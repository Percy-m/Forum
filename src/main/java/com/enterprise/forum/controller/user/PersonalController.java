package com.enterprise.forum.controller.user;

import com.enterprise.forum.annotation.CurrentAccount;
import com.enterprise.forum.dto.AccountUserDetails;
import com.enterprise.forum.dto.UsernameChangeDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.service.AccountService;
import com.enterprise.forum.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
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
                                   @CurrentAccount AccountUserDetails account) {
        try {
            accountService.updateUsername(account, param);
            // need to request login again
        }
        catch (BusinessException e) {
            return CommonVO.error(e.getMessage());
        }
        return CommonVO.ok();
    }
}
