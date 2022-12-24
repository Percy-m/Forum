package com.enterprise.forum.controller.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@RestController
@RequestMapping("/api/user")
public class UserTopicController {

    private UserDetailsService userDetailsService;


}
